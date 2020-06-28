#!/bin/bash

if ! hash git 2>/dev/null; then
  echo "Must have git installed to continue"
  exit 1
fi

if ! hash docker 2>/dev/null; then
  echo "Must have Docker installed to continue"
  exit 1
fi

REL_SCRIPT_DIR=`dirname "${BASH_SOURCE[0]}"`
WORKING_SCRIPT_DIR=`cd "$REL_SCRIPT_DIR" && pwd`
WORKING_DIR=`pwd`

if [[ "$WORKING_SCRIPT_DIR" != "$WORKING_DIR" ]]; then
  echo "Please run launch-verge-daemon.sh from bootstrap directory"
  exit 1
fi

echo "Valid programs installed! Cloning verge-docker repository..."

VERGE_DOCKER_DIR="verge-docker"

rm -rf "./$VERGE_DOCKER_DIR"
mkdir "$VERGE_DOCKER_DIR"
cd "$VERGE_DOCKER_DIR"

#git init
#git remote add origin https://github.com/vergecurrency/Docker-Verge-Daemon
#git pull origin master
