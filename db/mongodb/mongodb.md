# Mongo DB

## CLI
Restore a collection

```sh
mongorestore -d vocal-media-platform --nsInclude 'vocal-media-platform.users' ./dump/vocal-media-platform/users.bson --drop
```

Restore whole DB

```sh
mongorestore -d vocal-media-platform ./dump/vocal-media-platform --drop
```


```sh
db.getCollection('postimpressions').count({"createdAt": { $lt: ISODate("2018-01-12T00:00:000Z") }})
```

Bulk remove documents

```js
var bulk = db.getCollection('postimpressions').initializeUnorderedBulkOp();
bulk.find( {"createdAt": { $lt: ISODate("2018-06-12T00:00:000Z") }}).remove();
bulk.execute();
```

```js
//Monthly Earnings Query
// db.setProfilingLevel(0)
// db.system.profile.drop()
// db.setProfilingLevel(2)
db.getCollection('postimpressions').aggregate([
{
    $match: {
       author: ObjectId("57b6a79b1615281672d4c9fa"),
    },
},
{
    $project: {
        _id: '$_id',
        year: { $year: '$createdAt' },
        month: { $month: '$createdAt' },
        impressionValue: '$impressionValue',
    },
},
{
    $group: {
        _id: { year: '$year', month: '$month' },
        totalEarnings: { $sum: '$impressionValue' },
    },
}
])
// db.system.profile.find()
```

```js
//Impression Value Query
db.setProfilingLevel(0)
db.system.profile.drop()
db.setProfilingLevel(2)
db.getCollection('postimpressions').aggregate(
{
	$match: { post: ObjectId("59199bc40924a116d41e2f23") },
},
{
	$group: {
		_id: '',
		totalEarnings: { $sum: '$impressionValue' },
	}
})
db.system.profile.find()
```
