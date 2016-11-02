use APIs-internas-Qualidade-Software

db.getCollection('clients_junit').distinct("name", {"clients": {$gt: 26}}).forEach(function(val){
    interface = db.getCollection('clients_junit').find({"name" : val})[0]
    print(interface.name)
});
