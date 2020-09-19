.PHONY: help
.DEFAULT_GOAL := help
help:
	@echo "---------------------------------------------------------------------------------------"
	@echo ""
	@echo "				CLI"
	@echo ""
	@echo "---------------------------------------------------------------------------------------"
	@echo ""
	@awk 'BEGIN {FS = ":.*##"; printf "Usage: make \033[36m<target>\033[0m\n"} /^[a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-25s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

##@ Development

build: ## Build
	ci/build.sh --build

publish: ## Publish
	ci/build.sh --publish

html: ## Documentation
	ci/build.sh --docs && http-server -p 8080 -c-1 docs/target/html

##@ Helm

infra: ## Infrastructure
	docker-compose rm -f -v && docker-compose up -d

deploy: ## Deploy
	ci/helm.sh --deploy

delete: ## Delete
	ci/helm.sh --delete

update: ## Update
	ci/helm.sh --update-deps

helm: ## Helm setup
	ci/setup.sh
