# Akka Streams

## Exercise 1

A few minimal exercises to get you started:

- Print the numbers 1 to 100 (generate them with a Source, print them with a Sink)
- Print the squares of the numbers 1 to 100 (1, 4, 9, 16, etc…)
- Print the total of the squares of the numbers 1 to 100

## Exercise 2

Let’s do something a bit more practical. Here are four exercises involving reading the contents of a file.

Read at the contents of `/usr/share/dict/words` and do the following things (tackle each problem separately in a different code sample):

- Print the contents of the file to stdout. Use the following to get the file:

  `FileIO.fromPath(Paths.get("/usr/share/dict/words"))`

- Count the words in the file (`235886` on my machine) and return the count after running the stream.
- Count the number of vowels in the file (`889151` on my machine) and return the count after running the stream.
- Print adjacent pairs of words that have the same length (tip: use `source.sliding()`).
- Write the pairs of words from the last exercise out into a new file (tip: use `FileIO.sink()`)

## Exercise 3

We often need to *fold* over streams to accumulate results while only having access to a single item at a time. Here are a few exercises along these lines (see `src/main/resources/medals-expanded.csv` and `Medal.scala`):

- Write a method `athletesForTeam` that accepts a three-letter team code and returns the list of athletes who competed for that country.
- Write a method `medalsForTeam` that accepts a three-letter team code and returns the number of bronze, silver, and gold medals .
- Write a method `leaderboard` that returns a leaderboard of athletes by total medal count.

## Exercise 4

Let’s practice interfacing with a database. We’ll use MongoDB:

1. Print all the medals from the database
2. Print the number of gold, silver, and bronze medals won by each country
3. Create a second collection, `totals`, containing:
   1. the name of each country;
   2. the total number of gold, silver, and bronze medals.
