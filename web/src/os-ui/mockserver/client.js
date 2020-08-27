const mockServerClient = require('mockserver-client').mockServerClient;
const fs = require('fs');

function ok() {
  return function () {
    console.log("expectation created");
  };
}

function bad() {
  return function (error) {
    console.log(error);
  };
}

function client() {
  return mockServerClient("localhost", 18080);
}

function by(file) {
  const data = fs.readFileSync(file, {encoding: 'utf8', flag: 'r'});
  return JSON.parse(data)
}

client().mockAnyResponse(by('products/top-100s.json')).then(ok(), bad());
