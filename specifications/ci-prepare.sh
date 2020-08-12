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
  "shopping-cart" \
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
