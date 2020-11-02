#!/bin/bash

if [ -z $WORKSPACE ]
then
  echo "WORKSPACE variable not set => abort script"
  exit -1
fi

# the base eclipse platform
PLATFORM="2020-06"

# the type of the build for publishing
BUILD_TYPE="releases"

# the currently build release version
JAR_PREFIX="org.eclipse.smartmdsd_"
FEATURE_JAR_PATH=`find $WORKSPACE -name ${JAR_PREFIX}*.jar`
if [ -z $FEATURE_JAR_PATH ]
then
  echo "${JAR_PREFIX}feature jar not found => abort script"
  exit -1
fi
FEATURE_JAR=`basename $FEATURE_JAR_PATH`
# offset 32 chars and version length 4 chars (e.g. 3.15)
RELEASE_VERSION=${FEATURE_JAR:${#JAR_PREFIX}:4}

# the smartmdsd SSH account
SSH_ACCOUNT="genie.smartmdsd@projects-storage.eclipse.org"

# the output directory
SMARTMDSD_HTTP_PATH="/home/data/httpd/download.eclipse.org/smartmdsd/updates"
SMARTMDSD_REPO_PATH="$SMARTMDSD_HTTP_PATH/$BUILD_TYPE/v$RELEASE_VERSION/$PLATFORM"

echo "publish release v$RELEASE_VERSION to SMARTMDSD_REPO_PATH: $SMARTMDSD_REPO_PATH"

# clean-up previously published files
ssh $SSH_ACCOUNT rm -rf $SMARTMDSD_REPO_PATH
# make sure the repo path exists
ssh $SSH_ACCOUNT mkdir -p $SMARTMDSD_REPO_PATH

# publish smartmdsd repo files
scp -r "$WORKSPACE/releng/org.eclipse.smartmdsd.repository/target/repository" $SSH_ACCOUNT:$SMARTMDSD_REPO_PATH
