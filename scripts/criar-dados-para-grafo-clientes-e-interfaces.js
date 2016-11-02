// -------
var path = "./inputs";
var nameFile = "junit.maior.26.txt"; // Arquivo de entrada.
var nameFileOutput = "./outputs/output.junit.maior.26.txt"; // Arquivo de entrada.
// -------

var file = path + "/" + nameFile;

// Dados para conexão com o MongoDB
var host = 'mongodb://127.0.0.1:27017/';
var dbName = 'JAVALI';
var dbUrl = host + dbName;
var db = null;

var collection = null;
var nameCollection = "javaliApi";

var lineReader = require('line-reader');
var MongoClient = require('mongodb').MongoClient;

var fs = require('fs');
 
//Encontra clientes da interface e insere no arquivo "output.txt"
var findType = function(registry, last){
	collection.find({"Import" : registry }).forEach(function(val){ 
		console.log(registry + " -- " + val.Project);
		fs.writeFile(nameFileOutput, registry + ";" + val.Project +'\n',  {flag: "a"},  function (err) {
		  if (err){
		  	return console.log(err);
		  }
		  if(last){
		  	console.log("FIM!")
		  }
		});
	})
}

var parserFile = function(){
	lineReader.eachLine(file, function(line, last) {
		var interface = line.replace(" ", "");
		console.log(interface);
		findType(interface, last);
	});
}

var initParser = function(){
	console.log("\nConectando em " + dbUrl);
	MongoClient.connect(dbUrl, function(err, dbMongo) {
		  if(err) {
		  	console.log("\nError connecting in " + dbUrl);
		  }
		  else{
		  		db = dbMongo;
			  	collection = dbMongo.collection(nameCollection);
			  	parserFile();
		  }

	});
}

initParser();