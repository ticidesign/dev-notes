# Aggregation

Mongo count occurrences of each value for a set of documents and sort by result:

```sh
{ $group: { _id: <expression>, count: { $sum: 1 } } },
{ $sort: { count: -1 } }
```
Example:

```sh
db.getCollection('postimpressions').aggregate([
	{ $group : { _id : '$author', count : { $sum : 1 } } },
	{ $sort: { count: -1 } }
])
```

```sh
db.getCollection('postimpressions').aggregate([
	{
		$match: {
			author: ObjectId("5ae909f267c71b12a6692d70"),
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
```

# Aggregation Pipeline

## Stages

`$lookup`

Performs a left outer join to another collection in the same database to filter in documents from the “joined” collection for processing.

To perform uncorrelated subqueries between two collections as well as allow other join conditions besides a single equality match, the `$lookup` stage has the following syntax:

```
{
	$lookup: {
		from: <collection to join>,
		let: { <var_1>: <expression>, …, <var_n>: <expression> },
		pipeline: [ <pipeline to execute on the collection to join> ],
		as: <output array field>
	},
},
```

`$out`

Takes the documents returned by the aggregation pipeline and writes them to a specified collection. The `$out` operator must be the last stage in the pipeline.
