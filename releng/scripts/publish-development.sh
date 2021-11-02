#!/bin/bash

if [[ -z "$PLATFORM" || -z $WORKSPACE ]] 
then
  echo "PLATFORM or WORKSPACE not defined => abort script."
  exit 1
else
  echo "Using Eclipse platform: $PLATFORM"
fi

# the type of the build for publishing
BUILD_TYPE="development"

# the nightly release is cleared with every build
RELEASE_VERSION="3.x"

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

# publish composite repo files
scp $WORKSPACE/releng/org.eclipse.smartmdsd.composite.repository/* $SSH_ACCOUNT:$SMARTMDSD_REPO_PATH
# publish base repo files
scp -r $WORKSPACE/releng/org.eclipse.smartmdsd.base.repository/target/repository $SSH_ACCOUNT:$SMARTMDSD_REPO_PATH/base
# publish toolchain repo files
scp -r $WORKSPACE/releng/org.eclipse.smartmdsd.toolchain.repository/target/repository $SSH_ACCOUNT:$SMARTMDSD_REPO_PATH/toolchain
