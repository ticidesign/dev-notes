# Vocal Notes

Author with the most  post impressions in Vocal:

1. ossiana.tepfenhart@jerrick.media (3396038.0) ObjectId("58bf784c5694e7107b79a729")
2. info@filthygorgeousmedia.com (3060829.0)
3. caesar_finkle@jerrickmedia.com (2961140.0)
4. bea_norton@jerrickmedia.com (2080864.0)
5. danielle@jerrickmedia.com (2067648.0)
6. mackenzie_kennedy@jerrick.media (1628612.0)
7. glennkenny@mac.com (997909.0)
8. errwill@icloud.co (975201.0)
9. walter_cox@jerrickmedia.com (681083.0)
10. elisabethacaraballo@gmail.com (637692.0)

```js
db.getCollection('postimpressions').aggregate([
    { $group : { _id : '$author', totalReads : { $sum : 1 }, totalEarnings: { $sum: '$impressionValue' } } },
    { $sort: { count: -1 } }
])
```
Insert many

```js
for (var i = 0; i < 1000; i++) {
	db.getCollection('postimpressions').insertMany([
		{
			"createdAt": new Date(),
			"post" : ObjectId("5c47b50492e6d11a7a6c183d"),
			"author" : ObjectId("5ae909f267c71b12a6692d70"),
			"impressionValue" : 0.6,
		}
	])
}
```
Delete many

```js
db.getCollection('postimpressions').deleteMany({ post: ObjectId('5c47b50492e6d11a7a6c183d')})
```

```js
UserModel.aggregate([
		{
			$lookup: {
				from: 'postimpressions',

				// Using a pipeline here so that we can limit the data to just the _ids
				let: { authorId: '$_id' }, // the user id
				pipeline: [
					{ $match: {
						$expr: {
							$and: [
								{ $eq: ['$author', '$$authorId'] },
								{ $gte: ['$createdAt', { $dateFromString: { dateString: '2019-02-20 00:00:00.000Z' } } ] },
							]
						}
					} },
					{ $sort: { sortOrder: 1 } },
					{ $project: { _id: 1 } },
				],
				as: 'temp_postimpressions',
			},
		},
		// Add array of post id's to the user object.
		{
			$addFields: {
				postimpressions: {
					$map: {
						input: '$temp_postimpressions',
						as: 'postimpression',
						in: '$$postimpression._id',
					},
				},
			},
		},

		// Remove the temporary field.
		{
			$project: {
				temp_postimpressions: 0,
			},
		},

		{
			// Output overwrites the users collection.
			$out: 'test_users',
		},
	]);
```
