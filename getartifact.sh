#!/usr/bin/env bash

NEXUS_SERVICE=https://nexus-cicd.apps.ocp.datr.eu
NEXUS_PORT=8081
GRP=org.jnd.microservices
ARTEFACT=data-model
VERSION=0.0.1-SNAPSHOT
PACKAGING=jar

mvn org.apache.maven.plugins:maven-dependency-plugin:2.1:get \
    -DrepoUrl=$NEXUS_SERVICE/repository/maven-public/ \
    -Dartifact=$GRP:$ARTEFACT:$VERSION \
    -Dpackaging=$PACKAGING

GRPDIR=$(echo $GRP | sed 's/\./\//')
