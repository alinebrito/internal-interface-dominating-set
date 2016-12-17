use APIs-internas-PAA
db.getCollection('clientes_maven').find({}).forEach(function(val){
    print(val.Project + ";" + val.Import)
})