# Difference between using cache.updateFragment or cache.modify

There’s no perf difference b/w `writeFragment` and `modify`. I always used `writeFragment` because of the type safety it provides but recently prefer to use `modify` for lists especially after we introduced paginated fields as it’s a bit more predictable to work with.

The only difference is the one highlighted in their docs:

> Unlike `writeQuery` and `writeFragment`: `modify` circumvents any merge functions you’ve defined, which means that fields are always overwritten with exactly the values you specify. `modify` cannot write fields that do not already exist in the cache.

We use custom merge functions (`src/apollo/config`) to merge lists, especially complex merge functions for paginated lists. So if you want to just merge an entity it doesn’t matter and you can use either. If you wanna merge lists `modify` is your friend. Just remember `modify` doesn’t invoke the merge function which might not matter most of the time but sometimes it might depending on the use case.

TLDR: use `modify` for everything it’s easy to work with. It’s okay if you use `writeFragment` as long as you’re aware of its behaviour while merging lists.
