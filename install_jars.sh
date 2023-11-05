#!/bin/bash

mvn install:install-file \
  -Dfile="jars/bdo-error-codes-3.0.96.jar" \
  -DgroupId=com.bdo.error \
  -DartifactId=bdo-error-codes \
  -Dversion=3.0.96 \
  -Dpackaging=jar \
  -DgeneratePom=true


