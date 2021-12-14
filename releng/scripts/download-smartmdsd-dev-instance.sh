#!/bin/bash

# this is the name of the Toolchain folder where the toolchain is downloaded and installed into
OUTPUT_DIR=smartmdsd-dev

if [ "$#" -gt 0 ]
then
  OUTPUT_DIR=$1
fi

# this is the used Eclipse base version
ECLIPSE_BASE_VERSION=2021-12

# the name of the Eclipse Modeling Tools archive
ECLIPSE_MODELING_TOOLS_ARCHIVE="eclipse-modeling-${ECLIPSE_BASE_VERSION}-R-linux-gtk-x86_64.tar.gz"

# this is the full download URL for downloading the Eclipse Modeling Tools archive
ECLIPSE_DOWNLOAD_URL="http://ftp-stud.fht-esslingen.de/pub/Mirrors/eclipse/technology/epp/downloads/release/${ECLIPSE_BASE_VERSION}/R/${ECLIPSE_MODELING_TOOLS_ARCHIVE}"

# this is the base Eclipse repository URL used to find all additionally required dependencies for installation
ECLIPSE_REPO_URL="https://download.eclipse.org/releases/${ECLIPSE_BASE_VERSION}"

# this is an additional repository providing Maven/Tycho connectors
SONATYPE_TYCHO_REPO_URL="https://repo1.maven.org/maven2/.m2e/connectors/m2eclipse-tycho/0.9.0/N/0.9.0.201811261502/"

# these dependencies will be installed in addition to the SmartMDSD base plugins
ADDITIONAL_DEPENDENCIES="
org.eclipse.cdt.sdk.feature.group
org.eclipse.m2e.feature.feature.group
org.eclipse.xtext.sdk.feature.group
org.eclipse.sirius.specifier.feature.group
org.eclipse.sirius.specifier.properties.feature.feature.group
"

# these are the default eclipse arguments used for installing plugins
ECLIPSE_ARGUMENST="-noSplash -application org.eclipse.equinox.p2.director -repository $ECLIPSE_REPO_URL"

echo "creating output directory $OUTPUT_DIR"
mkdir -p $OUTPUT_DIR
cd $OUTPUT_DIR

# this folder is just for convenience and is meant to be later used as a workspace directory
mkdir -p ws

echo "downloading Eclipse Modeling Tools ${ECLIPSE_BASE_VERSION}"
wget -N $ECLIPSE_DOWNLOAD_URL

echo "extracting eclipse modeling tools archive..."
tar -xzf ${ECLIPSE_MODELING_TOOLS_ARCHIVE}
cd eclipse


echo "installing additional dependencies..."
# combine the dependencies into a single list of installation arguments so we can install all dependencies at once
for DEP in $ADDITIONAL_DEPENDENCIES
do
  INSTALLATION_ARGUMENTS="$INSTALLATION_ARGUMENTS -installIU $DEP"
done
# install the additional dependencies
./eclipse $ECLIPSE_ARGUMENST $INSTALLATION_ARGUMENTS

echo "installing Maven/Tycho extensions..."
./eclipse $ECLIPSE_ARGUMENST -repository $SONATYPE_TYCHO_REPO_URL -installIU org.sonatype.tycho.m2e.feature.feature.group

echo "ALL FINISHED! SmartMDSD Base Toolchain is installed into the folder $OUTPUT_DIR/eclipse (you can further use $OUTPUT_DIR/ws as workspace folder)"
