# CLI / DXC / API

CLI: `npx create-keystone-app my-app`
CLI: `yarn create-keystone-app my-app`

Now: check if th folder exists, create a KS project and install all the dependencies. Show a get start informtion.
DISABLE_LOGIN=true when `yarn start`
Entry and Server file can be add on the script in the package.json (--entry index.js --server server.js) or in the server.js file in the preprare function ( prepare({ entryFile: 'index.js, port: PORT }) )

```sh

```

How we want this to look like in 2 months:
- config
- eject
- fetch latest
- flip logging

keytone.prepare()

Clarification: index and server are separeted files because you can start a project without a server and just dump a schema from graphql and it would be static.

Maybe create a keystone.config.js file to separe running code to the configuration.

AdminUI config pages idea: add path and link to compoment:

`{label: Label, path '/analytics', compoment: <AnalyticsStuff />}`

```js
const admin = new AdminUI(keystone, {
  adminPath: '/admin',
  authStrategy,
  pages: [
    {
      label: 'Blog',
      children: [
        { listKey: 'Post' },
        { label: 'Categories', listKey: 'PostCategory' },
        {
          label: 'Stats',
          path: '/stats',
          component: () => await import('admin/views/BlogStats.js'),
        },
      ],
    },
  ],
});
```

# GraphQL / Auth / AC

Auth (Authentication or Authorization)

- Sign In vs Session vs Auth vs Access Control
- Session (id and type, it can have many, e.g. computer, person, user, etc)
- Auth (possibility to login twitter etc)
- Access Control (complex and strong, improve documentation)

Auth Idea: Create different Apollo Server for 'master chief' user or 'plebe' user. In this case, we could display different Admin UI for each user role.

Public vs Authenticated API

Admin UI
   \/
Core lists 			=> 		GraphQL API
list + field
access control
mutation
queries
hooks

### Tease Out
Signin vs Session vs Auth


# Build


# Admin Extensions


# WYSIWYG / Content

Slate: https://github.com/keystonejs/keystone-5/issues/616

WYSIWYG: TextArea with markdown suppport storing a string.
CKEditor5 can maybe help us in the licence for open source.
TASK: Expand a custom type in a WYSIWYG using one of the lists of editor I research.
Don't forget HTML 'sanitazition'


# Demos / Getting Started
- follow up op
- create user
