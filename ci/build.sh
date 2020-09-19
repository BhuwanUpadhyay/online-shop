#!/bin/bash
set -e

option="${1}"
default_version='1.0-SNAPSHOT'
next_version="${2:-$default_version}"

case ${option} in
   --build)
      ./mvnw \
        -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn -V \
        clean install -Drevision="$next_version"
      ;;
   --publish)
      # Publish Docker Images
      for i in "product" ; do
        echo "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
        docker push docker.io/bhuwanupadhyay/$i:"$next_version"
        echo "----------------------------------------------"
      done

      # Publish Helm Charts
      for i in "product" ; do
        echo "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
        HELM_CHART="$(sed 's/.*\///' <<< $i)-$next_version.tgz"
        HELM_CHART_FILE_PATH="$i/target/helm/repo/$HELM_CHART"
        curl --data-binary "@$HELM_CHART_FILE_PATH" http://localhost:18080/api/charts
        echo "Published: $HELM_CHART from $HELM_CHART_FILE_PATH"
        echo "----------------------------------------------"
      done
      ;;
   *)
      echo "`basename ${0}`:usage: [--build]"
      exit 1 # Command to come out of the program with status 1
      ;;
esac
