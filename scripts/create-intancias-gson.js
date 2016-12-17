
use APIs-internas-PAA
db.getCollection('interfaces_googleinject_usadas').find({}).sort({"clients": -1}).limit(30).forEach(function(val){    
    db.getCollection('clientes_googleinject').find({"Import" : val.name}).forEach(function(val){
        print(val.Project + ";" + val.Import)
    })
})
