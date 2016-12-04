// -------
var path = "./inputs";
var nameFile = "LIST-android.txt"; // Arquivo de entrada.
// -------

var file = path + "/" + nameFile;

// Dados para conexão com o MongoDB
var host = 'mongodb://127.0.0.1:27017/';
var dbName = 'JAVALI';
var dbUrl = host + dbName;
var db = null;

var collection = null;
var collection_saida = null;
var nameCollection = "javaliApi";
var nameCollection_saida = "clientes_android";

var lineReader = require('line-reader');
var MongoClient = require('mongodb').MongoClient;

var fs = require('fs');
 
//Encontra clientes da interface e insere no arquivo "output.txt"
var findType = function(registry, last){
	collection.find({"Import" : registry }).forEach(function(val){ 
		collection_saida.insert(val, {w: 1}, function(err, records){
		  console.log("Record added as "+records[0]._id);
		  console.log(registry);
		});
	})
}

var parserFile = function(){
	lineReader.eachLine(file, function(line, last) {
		var interface = line.replace(" ", "");
		//console.log(interface);
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
			  	collection_saida = dbMongo.collection(nameCollection_saida);
			  	
			  	parserFile();
		  }

	});
}

initParser();