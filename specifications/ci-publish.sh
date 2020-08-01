#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

mvn_deploy() {
  curl https://gist.githubusercontent.com/BhuwanUpadhyay/b626684dcbd710bc27756be72b7128a6/raw/5dfced7406ea9b51f78391e6faf49746c44db152/cloudsmith-settings.xml \
    --output cloudsmith-settings.xml

  sed -i "s|</project>|<distributionManagement><snapshotRepository><id>cloudsmith</id><url>https://maven.cloudsmith.io/bhuwan-prasad-upadhyay/maven/</url></snapshotRepository><repository><id>cloudsmith</id><url>https://maven.cloudsmith.io/bhuwan-prasad-upadhyay/maven/</url></repository></distributionManagement></project>|" pom.xml

  mvn com.coveo:fmt-maven-plugin:format \
      source:jar-no-fork \
      deploy \
      -s cloudsmith-settings.xml
}

npm_publish() {
  echo "//npm.cloudsmith.io/bhuwan-prasad-upadhyay/maven/:_authToken=${NPM_AUTH_TOKEN}" > $HOME/.npmrc
  npm config set '@rtms:registry' https://npm.cloudsmith.io/bhuwan-prasad-upadhyay/maven/
  npm i && npm run build && cd dist/ && npm pack && npm publish
}

echo "------------BEGIN: PUBLISHING------------"
echo "Current script directory: $SCRIPT_DIR"
echo "New semantic version: $NEW_VERSION"

for module in \
  "inventory" \
  ; do
    echo "PUBLISHING MODULE: ${module}"
    for feature in \
        "spring-boot" \
        "java-client" \
        ; do
        echo "PUBLISHING FEATURE: ${feature}"
        cd "$SCRIPT_DIR"/dist/${module}/${feature}
        mvn_deploy
        cd "$SCRIPT_DIR"
    done
    for feature in \
        "typescript-client" \
        ; do
        echo "PUBLISHING FEATURE: ${feature}"
        cd "$SCRIPT_DIR"/dist/${module}/${feature}
        npm_publish
        cd "$SCRIPT_DIR"
    done
done

echo "------------SUCCESS: PUBLISHING------------"
