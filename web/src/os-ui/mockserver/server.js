const mockserver = require('mockserver-node');
const mockServerClient = require('mockserver-client').mockServerClient;
const fs = require('fs');

mockserver
  .start_mockserver({serverPort: 18080, verbose: true})
  .then(
    function () {
      console.log("started MockServer");
    },
    function (error) {
      console.log(JSON.stringify(error, null, "  "));
    }
  );

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
