#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

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
  "inventory" \
  ; do
    echo "PACKAGING MODULE: ${module}"
    for feature in \
        "spring-boot" \
        "java-client" \
        ; do
        echo "PACKAGING FEATURE: ${feature}"
        cd "$SCRIPT_DIR"/dist/${module}/${feature}
        mvn_install
        cd "$SCRIPT_DIR"
    done
    for feature in \
        "typescript-client" \
        ; do
        echo "PACKAGING FEATURE: ${feature}"
        cd "$SCRIPT_DIR"/dist/${module}/${feature}
        npm_link
        cd "$SCRIPT_DIR"
    done
done

echo "------------SUCCESS: PACKAGING------------"
