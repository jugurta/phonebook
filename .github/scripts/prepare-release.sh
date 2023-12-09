#!/bin/bash

echo "version = $1"

# Get version number from version tag
VERSION=`echo $1 | cut -d'v' -f2`



cd back
mvn versions:set-property -Dproperty=revision -DnewVersion=$VERSION
cd ../

cd front
npm version --allow-same-version --no-git-tag-version $VERSION
cd ../