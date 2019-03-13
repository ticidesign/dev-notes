# Mongo Basics

`mongod` will start the DB locally. It is the primary daemon process for the MongoDB system. It handles data requests, manages data access, and performs background management operations.

## Mongo Shell

`mongo` will open an interactive shell
`show dbs` show all databases
`use <DB>` create a database called DB and switch o it
`show collections` show all tables / documents / collections
`db.help()` show list of function that you can use to interact to mongo.

## Mongo GUI

[Mongo Compass Community](https://www.mongodb.com/products/compass)
[Robo 3T](https://robomongo.org/)

# Creating Schemas & Models with moongose

Intructions or shape to create a new collection.

```js
const mongoose = require('mongoose);
const connect = () => {
	return mongoose.connect('mongodb://localhost:27017/whatever');
}
const students = new mongoose.Schema({
	firtName: String //we can use the JS built-in primitives
});

const Student = mongoose.model('student', students); //keep it singular and lowercase
```

# Creating a Mongo Document

```js
connect()
	.then(await connection =>{
		const student = await Student.create({firtName: 'Ticiana'})
		console.log(student);
	})
	.catch(e => console.error(e))
```
