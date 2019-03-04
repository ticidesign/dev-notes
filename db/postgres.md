# PostgreSQL

## Command line tool

[Psql](http://postgresguide.com/utilities/psql.html) is the interactive terminal for working with Postgres. Theres an abundance of flags available for use when working with psql, but lets focus on some of the most important ones, then how to connect:

	-h the host to connect to
	-U the user to connect with
	-p the port to connect to (default is 5432)

```sh
psql -h localhost -U username databasename
```

### Commonly used commands

Turn query timing on. (By default the timing of query results will not be available, but we can turn it on by using the following command. This will show query timing in milliseconds.)
```sh
\timing
```

Create user and alter role
```sh
CREATE ROLE vocal WITH LOGIN PASSWROD '123';
ALTER ROLE vocal CREATEDB;
```

Connect with an user
```sh
psql postgres -U vocal;
CREATE DATABASE jerrick_media_magazine;
GRANT ALL PRIVILEGES ON DATABASE jerrick_media_magazine TO vocal;
```

List all databases
```sh
\l
```
List all databases with additional information
```sh
\l+
```
List tables in database
```sh
\d
```
List all schemas
```sh
\dn
```
List all functions
```sh
\df
```
List all functions with additional information
```sh
\df+
```
Connect
```sh
\c dbname
```


Show the list of users when connected without a user.
```sh
psql postgres
\du
```

Show the list of table when connected to a Database
```sh
psql postgres -U ticianadeandrade
\connect vocal_next
\dt
```

Quit from postgres shell
```sh
\q
```

Grant acess to all tables and sequesces in a Database
```sh
\connect vocal_next;
GRANT ALL PRIVILEGES ON DATABASE vocal_next TO ticianadeandrade;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO ticianadeandrade;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO ticianadeandrade;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO ticianadeandrade;
```

Drop user from Database```sh
psql postgres
REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA public FROM vocal;
REVOKE ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public FROM vocal;
REVOKE ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public FROM vocal;
DROP USER vocal;
```

[=> Postgres docs](https://www.postgresql.org/docs/11/index.html)

## MoSQL Yaml Generator for Keystone
https://www.npmjs.com/package/@thinkmill/keystone-mosql-yaml-gen

Uses [mosql](https://github.com/stripe/mosql) MongoDB → PostgreSQL streaming replication

Command line to import Jerrick Media Magazine to Vocal Next
```sh
mosql -c 20190225-jerrick-media-magazine-MoSQL.yaml --sql postgres://localhost/vocal_next --mongo mongodb://localhost/jerrick-media-magazine --only-db jerrick-media-magazine
```

## Postgres Client

- [Postico]
- [SQLPro for Postgres](https://macpostgresclient.com)

## Queries Examples

Total impression per post

```sql
EXPLAIN ANALYSE
SELECT
 SUM (impressionvalue) AS totalEarnings
FROM
 postimpressions
WHERE
 post = '59199bc40924a116d41e2f23';
```

Total number of posts

```sql
SELECT
	post,
	count(post)
FROM postimpressions
GROUP BY post
ORDER BY count(post) desc
LIMIT 10;
```

Total impression per author

```sql
SELECT
  author,
  SUM (impressionvalue) AS totalEarnings
FROM postimpressions
GROUP BY author
ORDER BY totalEarnings desc
LIMIT 10;
```

Author Monthly Earnings

```sql
SELECT
	author,
	TO_CHAR(createdat,'Mon') as mon,
	EXTRACT(year from createdat) as yyyy,
	SUM (impressionvalue) AS totalEarnings
FROM postimpressions
WHERE author = '57b6a79b1615281672d4c9fa'
GROUP BY 1,2,3;
```
