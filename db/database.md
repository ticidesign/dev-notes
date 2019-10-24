# Database

A structured set of data held in a computer, especially one that is accessible in various ways. A database in SQL Server is made up of a collection of tables that stores a specific set of structured data.

## SQL

Structured Query Language lets you access and manipulate databases.

SQL can:
- execute queries against a database
- retrieve data from a database
- insert records in a database
- update records in a database
- delete records from a database
- create new databases
- create new tables in a database
- create stored procedures in a database
- create views in a database
- set permissions on tables, procedures, and views

### Keys

A **primary key** is a set of attributes/a candidate key that distinctly identifies a record in a relation. However, a **foreign key** in a table refers to the primary key of another table. No primary key attributes can contain NULL values whereas, a foreign key attribute can accept NULL value.

### Commands

    DDL - Data Definition Language
    CREATE, DROP, ALTER, TRUNCATE

    DQL – Data Query Language
    SELECT 

    DML - Data Maniputation Language
    INSERT, UPDATE, DELETE

    DCL - Data Control Language
    GRANT, REVOKE

    TCL - Transaction Control Language
    COMMIT, ROLLBAKC, SAVE POINT

### RDBMS – Relational Database Management Systems

A database management system (DBMS) is a software package designed to define, manipulate, retrieve and manage data in a database.

Oracle, IBM DB2, MySQL, teradata, PostGres

#### Data Modelling

##### Normalization

Normalization is a technique for organizing data in a database. 

It is important to normalize your data to minimize redundancy, ensure only related data is stored in each table and keep your data integrity.

**First Normal Form (1NF):**

- Data is stored in tables with rows uniquely identified by a primary key
- Data within each table is stored in individual columns in its most reduced form
- There are no repeating groups

**Second Normal Form (2NF):**
- Everything from 1NF
- Only data that relates to a table’s primary key is stored in each table

**Third Normal Form (3NF):**
- Everything from 2NF
- There are no in-table dependencies between the columns in each table

There are more 3 rules but usually these ones are enough to build a database.

### Data Warehouse and Data Mart

DW is an analytics platform used to report on and store data. Data that usually resides or originates in multiple, disparate systems is moved into a data warehouse for analysis and longer-term storage.

Data mart is a subset of the data warehouse and is usually oriented to a specific business line or team.

### Database vs Data Warehouse vs Data Lake

- DB is a normalised schema to storage data from a software/ an application.
- DW stores data from different data sources (ERP, CRM, Text Files, Spreadsheet, etc.) in a predefined schemas;
- DL is a centralised repository for structured (csv, systems, etc.) and unstructured data (images, pdf, media files, etc.) from multiple sources.

### Data Warehouse – Data modelling

- The star schema is the simplest type of Data Warehouse schema. It is known as star schema as its structure seems a star.
- Snowflake schema resembles from star schema, but the dimensions of a star schema are normalized.
- In both models, we will have Fact Tables and Dimension Tables.
- It has been created by Ross Kimball and these models are also called Kimball.
- Dimensions provide the “who, what, where, when, why, and how” context surrounding a business process event.
- Facts are the measurements that result from a business process event and are almost always numeric.

### ETL – Extract, Transform and Load

In computing, extract, transform, load is the general procedure of copying data from one or more sources into a destination system which represents the data differently from the source or in a different context than the source.

Other names ELT (data lakes) and data pipelines.

## Resource 

[W3Schools SQL Tutorial](https://www.w3schools.com/sql/default.asp)