#!/bin/bash

if [[ -z "$PLATFORM" ]]
then
  PLATFORM=2020-09
  echo "Using default Eclipse platform: $PLATFORM"
else
  echo "Using Eclipse platform: $PLATFORM"
fi


if [[ -z "$WORKSPACE" ]]
then
  WORKSPACE=$PWD
  echo "Using the current directory as WORKSPACE: $WORKSPACE"
else
  echo "Using the WORKSPACE: $WORKSPACE"
fi

# parse the currently build release version from main feature jar
JAR_PREFIX="org.eclipse.smartmdsd.toolchain_"
FEATURE_JAR_PATH=`find $WORKSPACE -name ${JAR_PREFIX}*.jar`
if [ -z $FEATURE_JAR_PATH ]
then
  echo "${JAR_PREFIX}feature jar not found => abort script"
  exit -1
fi
FEATURE_JAR=`basename $FEATURE_JAR_PATH`
# version length is expected tobe 4 chars long (e.g. 3.15)
RELEASE_VERSION=${FEATURE_JAR:${#JAR_PREFIX}:4}

# this is the output directory name
OUTPUT_DIRECTORY="eclipse-smartmdsd-v${RELEASE_VERSION}-${PLATFORM}"

# the type of the build for publishing
BUILD_TYPE="releases"

# this is the base download URI for all the SmartMDSD donwload files
SMARTMDSD_BASE_URI="download.eclipse.org/smartmdsd/updates"

# this is the constructed URI for the specific release
SMARTMDSD_RELEASE_URI="${SMARTMDSD_BASE_URI}/${BUILD_TYPE}/v${RELEASE_VERSION}/${PLATFORM}"

# the output directory
SMARTMDSD_UPLOAD_PATH="/home/data/httpd/${SMARTMDSD_RELEASE_URI}"

# this is the SmartMDSD P2 repository URL where the SmartMDSD plugins are searched for installation
SMARTMDSD_REPO_URL="https://${SMARTMDSD_RELEASE_URI}"

# the smartmdsd SSH account
SMARTMDSD_SSH_ACCOUNT="genie.smartmdsd@projects-storage.eclipse.org"

# this is the main SmartMDSD feature that will be installed
SMARTMDSD_FEATURE="org.eclipse.smartmdsd.toolchain.feature.group"

# the name of the Eclipse Modeling Tools archive
ECLIPSE_MODELING_TOOLS_ARCHIVE="eclipse-modeling-${PLATFORM}-R-linux-gtk-x86_64.tar.gz"

# this is the full download URL for downloading the Eclipse Modeling Tools archive
ECLIPSE_DOWNLOAD_URL="http://ftp-stud.fht-esslingen.de/pub/Mirrors/eclipse/technology/epp/downloads/release/${PLATFORM}/R/${ECLIPSE_MODELING_TOOLS_ARCHIVE}"

# this is the base Eclipse repository URL used to find all additionally required dependencies for installation
ECLIPSE_REPO_URL="https://download.eclipse.org/releases/${PLATFORM}"

# these are the default eclipse arguments used for installing plugins
ECLIPSE_ARGUMENST="-noSplash -application org.eclipse.equinox.p2.director -repository $ECLIPSE_REPO_URL"

CMAKE_EDITOR_REPO_URL="https://raw.githubusercontent.com/15knots/cmakeed/master/cmakeed-update/"
CMAKE_EDITOR_FEATURE="com.cthing.cmakeed.feature.feature.group"

echo "downloading Eclipse Modeling Tools ${PLATFORM}"
if [[ -z "$JENKINS_HOME" ]]
then
  # non-jenkins download (only if file newer than local)
  wget -N $ECLIPSE_DOWNLOAD_URL
else
  wget -nv $ECLIPSE_DOWNLOAD_URL
fi

echo "extracting eclipse modeling tools archive..."
tar -xzf eclipse-modeling-${PLATFORM}-R-linux-gtk-x86_64.tar.gz

echo "cleaning up previous build..."
rm -rf eclipse-smartmdsd-*

echo "creating the output directory: ${OUTPUT_DIRECTORY}"
mv eclipse ${OUTPUT_DIRECTORY}

cd ${OUTPUT_DIRECTORY}

echo "installing SmartMDSD Toolchain feature..."
./eclipse $ECLIPSE_ARGUMENST -repository $SMARTMDSD_REPO_URL -installIU $SMARTMDSD_FEATURE

echo "installing CMake editor extension..."
./eclipse $ECLIPSE_ARGUMENST -repository $CMAKE_EDITOR_REPO_URL -installIU $CMAKE_EDITOR_FEATURE

cd ..

echo "creating a tar.gz archive for the installed Eclipse instance..."
tar -czf ${OUTPUT_DIRECTORY}.tar.gz ${OUTPUT_DIRECTORY}

if [[ -z "$JENKINS_HOME" ]]
then
  echo "non-jenkins build, skip uploading archive"
else
  echo "uploading the ${OUTPUT_DIRECTORY}.tar.gz archive to ${SMARTMDSD_REPO_URL}..."
  ssh ${SMARTMDSD_SSH_ACCOUNT} mkdir -p ${SMARTMDSD_UPLOAD_PATH}
  scp ${OUTPUT_DIRECTORY}.tar.gz ${SMARTMDSD_SSH_ACCOUNT}:${SMARTMDSD_UPLOAD_PATH}
fi

echo "ALL FINISHED! SmartMDSD archive has been uploaded to ${SMARTMDSD_REPO_URL}"
