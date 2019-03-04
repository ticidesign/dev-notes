# Mongo DB

author = "57b6a79b1615281672d4c9"
post = "59199bc40924a116d41e2f23"


## Weekly Impression
Postgres		6.5s		7.8s		7.1s		6.0s		5.1s		1.3s		2.5s		2.3s
MongoDB			2.62s		1.33s		1.17s		1.2s

## Post Impressions
Postgres		5.7s		5.6s		4.7s		5.4s		1s
MongoDB			2.18s		7.59s		3.98s		0.59s		1.62s


```sh
db.getCollection('postimpressions').count({"createdAt": { $lt: ISODate("2018-01-12T00:00:000Z") }})
```

```js
var bulk = db.getCollection('postimpressions').initializeUnorderedBulkOp();
bulk.find( {"createdAt": { $lt: ISODate("2018-06-12T00:00:000Z") }}).remove();
bulk.execute();
```

```js
//WeeklyEarningsQuery
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
