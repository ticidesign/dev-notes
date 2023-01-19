# Team Talk - Ticiana de Andrade - Mocking GraphQL - 10th July 2019

https://www.youtube.com/watch?v=qOiJuZ_B5W8

#### What is mocking?

In short, mocking is creating objects that simulate the behavior of real objects.

Mocking is the practice of creating a fake version of a component, so that you can develop and test other parts of your application independently. Mocking your backend is a great way to quickly build a prototype of your frontend, and it lets you test your frontend without starting up any servers.

Mocking is primarily used in unit testing. An object under test may have dependencies on other (complex) objects. To isolate the behavior of the object you want to replace the other objects by mocks that simulate the behavior of the real objects. This is useful if the real objects are impractical to incorporate into the unit test.

#### Why mock?

Mocking the data a backend would return is very useful for two main reasons:

It lets you start developing a frontend feature when you don’t have a working backend for it yet. This is critical for projects where the frontend and backend components are often developed in parallel.
It lets you run tests locally without connecting to a real backend, which is much faster and safer. As your codebase grows and your app becomes more complex, starting up all of the server infrastructure just to run some tests isn’t feasible.

### Why is mocking backend hard?

Let’s say your backend is some REST API that is called over http from the browser. You have someone working on the backend, and someone else working on the frontend. The backend code actually determines the shape of the data returned for each REST endpoint, but mocking has to be done in the frontend code. That means the mocking code will break every time the backend changes, unless both are changed at the same time. What’s worse, if you’re doing your frontend testing against a mock backend that is not up to date with your backend, your tests may pass, but your code won’t work. Rather than having to keep more dependencies up to date, the easy option is to just not mock the REST API, or have the backend be in charge of mocking itself, just so it’s all in one place. That may be easier, but it will also slow you down.

The other reason I often hear for why people don’t mock the backend in their project is because it takes time to set up: first you have to include extra logic in your data fetching layer that lets you turn mocking on and off, and second you have to actually describe what that mock data should like. For any non-trivial API that requires a lot of tedious work.

Both of these reasons for why mocking backends is hard are actually due to the same underlying reason: there is no standard REST API description in machine-consumable format and contains all the information necessary for mocking and can be used by both the backend and the frontend. There are some API description standards, like Swagger, but they don’t contain all of the information you need, are cumbersome to write and maintain. Unless you want to pay a lot of money for a service or a product — and maybe even then — mocking is a lot of work.

Actually, I should say mocking was a lot of work, because something is about to change the way with think of APIs. That something is called GraphQL.

GraphQL makes mocking easy, because it makes you define a type system for your backend. That type system can be shared between your backend and your frontend, and it contains all the information necessary to make mocking incredibly fast and convenient.

https://twitter.com/JessTelford/status/1116309923141304321

Here’s how easy it is to start mocking your backend for any valid GraphQL query with one of the GraphQL-tools built for Apollo:

`addMockFunctionsToSchema({ schema, mocks });`

thanks to 'graphql-tools'

Every GraphQL server needs a schema, so it’s not extra code you need to write just for mocking. And the query is the one your component uses for fetching data, so that’s also not code you write just for mocking. Not counting the import statement, it only took us one line of code to mock the entire backend!

```js
import { ApolloClient, InMemoryCache } from "apollo-boost";
import { SchemaLink } from "apollo-link-schema";
import { makeExecutableSchema, addMockFunctionsToSchema } from "graphql-tools";

// Fill this in with the schema string
const typeDefs = `...your schema...`;

// Make a GraphQL schema with no resolvers
const schema = makeExecutableSchema({ typeDefs });

const mocks = {
  User: () => ({
    avatar: "https:source.unsplash.com/100X100?face",
    age: 22,
  }),
};

// By default, creates random mocks for all fields. Use `mocks` to define
// a set of resolvers which overwrite the default random mocks.
addMockFunctionsToSchema({ schema, mocks });

const client = new ApolloClient({
  cache: new InMemoryCache(),
  link: new SchemaLink({ schema }),
});
```

Put that in contrast to most REST APIs out there, where mocking means parsing a URL and returning data in a custom shape for each endpoint. It takes dozens of lines to mock a single endpoint that returns some realistic-looking data. With GraphQL, the shape is encoded in the query, and together with the schema we have enough information to mock the server with a single line of code.

```js
query {
  User {
    Avatar
    Age
  }
}

// Output:
// {
//   User: {
//     avatar: 'https:source.unsplash.com/100X100?face',
//     age: 22
//   }
// }
```

### Customizing mock data

`mockServer` is just a convenience wrapper on top of `addMockFunctionsToSchema`. It adds your mock resolvers to your schema and returns a client that will correctly execute your query with variables.

```js
// > npm install graphql-tools
import { mockServer } from "graphql-tools";
import schema from "./mySchema.graphql";

const myMockServer = mockServer(schema);
myMockServer.query(`{
  allUsers: {
    id
    name
  }
}`);

// returns
// {
//   data: {
//     allUsers:[
//       { id: 'ee5ae76d-9b91-4270-a007-fad2054e2e75', name: 'lorem ipsum' },
//       { id: 'ca5c182b-99a8-4391-b4b4-4a20bd7cb13a', name: 'quis ut' }
//     ]
//   }
// }
```

In the example before, the mock server will return completely random IDs and strings every time you query it. When you’ve just started building your app and only want to see if your UI code actually displays something, that’s probably good enough, but as you start to fine-tune your layout, or want to use the mock server to test your component’s logic, you’ll probably need more realistic data.

Luckily, it's really easy to customize mock data with Apollo mocking tools. You can customize virtually everything about the mock data that it returns. It lets you do all of the following and more.

For each type and each field you can provide a function that will be called on to generate mock data. Mock functions on fields have precedence over mock functions on types, but they work together really nicely: The field mock functions only need to describe the properties of the objects that matter to them, type mock functions will fill in the rest.

```js

// customize mocking per type (i.e. Integer, Float, String)
mockServer(schema, {
  Int: () => 6,
  Float: () => 22.1,
  String: () => 'Hello',
});

// customize mocking per field in the schema (i.e. for Person.name and Person.age)
mockServer(schema, {
  Person: () => ({
    name: casual.name,
    age: () => casual.integer(0,120),
  })
});

// mock lists of specific or random length( and lists of lists of lists …)
mockServer(schema, {
  Person: () => {
    // a list of length between 2 and 6
    friends: () => new MockList([2,6]),
    // a list of three lists of two items: [[1, 1], [2, 2], [3, 3]]
    listOfLists: () => new MockList(3, () => new MockList(2)),
  },
});

// customize mocking of a field or type based on the query arguments
mockServer(schema, {
  Person: () => {
    // the number of friends in the list now depends on numPages
    paginatedFriends: (o, { numPages }) => new MockList(numPages * PAGE_SIZE),
  },
});

// You can also disable mocking for specific fields, pass through to the backend, etc.
```

### Graphql Mock Example

But enough talking, here’s a complete example

The mock functions are actually just GraphQL resolve functions in disguise. What that means is that your mocking can do anything that you could do inside a GraphQL resolve function. If you wanted, you could write your entire backend with it.

Code Sandbox: https://codesandbox.io/s/apolloclientmockinggraphql-lgx60

`Mocking your backend doesn’t have to be rocket science!`

I think the real power of this tool is that while it allows almost arbitrarily complex customization, you can get started really quickly and increase the sophistication of your mocks in tiny steps whenever you need it.

Slides https://slides.com/ticiandrade/mocking-graphql-for-docz-and-testing
