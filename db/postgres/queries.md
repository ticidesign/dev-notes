# Postgress Queries Examples

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

Create view with most recent 3 posts!

```sql
-- drop view most_recent_posts;
create view most_recent_posts as (
	select
		p.id, p.name, p.author as author_id, p.reviewer as reviewer_id,
		a.name_first as author_name,
		r.name_first as reviewer_name,
		publishedat as published_at,
		(now() - publishedat) as published_ago
	from posts as p
		inner join users as a on (p.author = a.id)
		inner join users as r on (p.reviewer = r.id)
	where publishedat is not null
	order by publishedat desc
	limit 3
);

select * from most_recent_posts;

create index posts_publishedat on posts (publishedat);
```

Create materialized view with post impressions per month per author

```sql
CREATE MATERIALIZED VIEW pi_aggregates as
SELECT
	author as "author_id",
	TO_CHAR(createdat, 'Mon') as "month",
	EXTRACT(year from createdat) as "year",
	SUM (impressionvalue) AS "total_earnings"
FROM postimpressions
GROUP BY 1,2,3;

--Update / refresh materialized view

REFRESH MATERIALIZED VIEW pi_aggregates;

-- Create index
create index pi_aggregates_author_id on pi_aggregates (author_id);

-- Query pi_aggregates table to get impressions per month
SELECT pia.*, u.name_first
	from pi_aggregates as pia
	inner join users as u
	on (pia.author_id = u.id)
WHERE pia.author_id = '57b6a79b1615281672d4c9fa';


-- Posts without an author
select count(*)
from posts
where author is null;

-- Posts count where author id is not found in the user table
select author, status, count(*) as post_count
from posts
where not exists (select 1 from users where posts.author = id)
group by author, status;

-- Posts where author id is not found in the user table
select *
from posts
where not exists (select 1 from users where posts.author = id);


select * from users where id = '597e473aff818670d5a6dde4';
```
