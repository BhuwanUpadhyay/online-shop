#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

mvn_deploy() {
  curl https://gist.githubusercontent.com/BhuwanUpadhyay/b626684dcbd710bc27756be72b7128a6/raw/5dfced7406ea9b51f78391e6faf49746c44db152/cloudsmith-settings.xml \
    --output cloudsmith-settings.xml

  sed -i "s|</project>|<distributionManagement><snapshotRepository><id>cloudsmith</id><url>https://maven.cloudsmith.io/bhuwan-prasad-upadhyay/maven/</url></snapshotRepository><repository><id>cloudsmith</id><url>https://maven.cloudsmith.io/bhuwan-prasad-upadhyay/maven/</url></repository></distributionManagement></project>|" pom.xml

  mvn source:jar-no-fork \
      deploy
}

npm_publish() {
  echo "//npm.cloudsmith.io/bhuwan-prasad-upadhyay/maven/:_authToken=${NPM_AUTH_TOKEN}" > $HOME/.npmrc
  npm config set '@rtms:registry' https://npm.cloudsmith.io/bhuwan-prasad-upadhyay/maven/
  npm i && npm run build && cd dist/ && npm pack && npm publish
}

mvn_install() {
  mvn \
      source:jar-no-fork \
      install
}

npm_link() {
  npm i && npm run build && cd dist/ && npm link
}

echo "------------BEGIN: PACKAGING------------"
echo "Current script directory: $SCRIPT_DIR"
echo "New semantic version: $NEW_VERSION"

for module in \
  "cart" \
  "delivery" \
  "inventory" \
  "history" \
  "order" \
  "product" \
  "shipping" \
  "user" \
  ; do
    echo "PACKAGING MODULE: ${module}"
    for feature in \
        "spring-boot" \
        ; do

        echo "PACKAGING FEATURE: ${feature}"

        cd "$SCRIPT_DIR"/dist/${module}/${feature}

        case $feature in
          spring-boot)
            mvn_install
            ;;
          java-client)
#            mvn_install
            ;;
          typescript-client)
#            npm_link
            ;;
        esac

        cd "$SCRIPT_DIR"
    done
done

echo "------------SUCCESS: PACKAGING------------"
