# Akka Streams

## Exercise 1: Numbers

Here are a few small exercises to get you started:

- Print the numbers 1 to 100 (generate them with a Source, print them with a Sink)
- Print the squares of the numbers 1 to 100 (1, 4, 9, 16, etc…)
- Print the total of the squares of the numbers 1 to 100

## Exercise 2: Dictionary

Let’s do something a bit more practical. 
Here are four exercises involving reading the contents of a file.

Read at the contents of `/usr/share/dict/words` 
and do the following things 
(tackle each problem separately in a different code sample):

- Print the contents of the file to stdout. Use the following to get the file:

  `FileIO.fromPath(Paths.get("/usr/share/dict/words"))`

- Count the words in the file (`235886` on my machine) and return the count after running the stream.

- Count the number of vowels in the file (`889151` on my machine) and return the count after running the stream.

- Print adjacent pairs of words that have the same length (tip: use `source.sliding()`).

## Exercise 3: Medals

We often need to ****fold**** over streams to accumulate results 
while only having access to a single item at a time. 
Here are a few exercises along these lines 
(see `src/main/resources/medals-expanded.csv` and `Medal.scala`):

- Write a method `athletesForCountry` that accepts a three-letter country code 
  and returns the list of athletes who competed for that country.
  
- Write a method `medalsForCountry` that accepts a three-letter country code 
  and returns the number of bronze, silver, and gold medals.
  
- Write a method `mostMedals` that calculates the athlete with the most medals.
  
- Write a method `leagueTable` that returns a leaderboard of countries by total medal count.

## Exercise 4: MongoDB

1. Write the contents of `medals-expanded.csv`
   to a collection called `medals`
   in a MongoDB database called `olympics`
   (see `Exercise4Setup.scala`)

2. Read the medals back in and print them.

3. Traverse the collection and collect
   the total number of gold, silver, and bronze medals
   won by each team

4. Traverse the collection and _while traversing_
   print the following for each record (use `source.scan()`) :

   - the number of records read so far;
   - the name of the athlete in the current row;
   - the number of gold medals they have won so far.

5. Traverse the collection and create
   a second collection `athletes` containing:

   1. The name of each athlete
   2. A list of their events
   3. The number of gold, silver, and
      bronze medals they've won

## Cheat Sheet

Here are the most common methods you'll be using.
Use your IDE to find out more about their types.

Where I've used an uppercase first letter,
I mean a singleton object from the Akka Streams library.
Where I've used a lowercase first letter, 
I mean a value of the relevant type.

### Creating Sources

```scala
import akka.stream.scaladsl.{Source, FileIO}

// Create a source from a sequence:
Source.apply(iterable)

// Create a source with a single value in it:
Source.single(any)

// Create a source from a file:
FileIO.fromPath(Paths.get(string))
```

### Transforming Sources

```scala
source.map(function)
source.mapConcat(function)
source.mapAsync(int)(function)
source.mapAsyncUnordered(int)(function)
source.flatMapConcat(function)
```

### Creating Sinks

```scala
import akka.stream.scaladsl.Sink

// Create a sink that runs a side-effect for each item:
Sink.foreach(function)

// Create a sink that combines items into a single value:
Sink.fold(any)(function)
```

### Running Sources

```scala
// Run a source with a sink:
source.runWith(sink)

// Run a source with a Sink.foreach sink (shorthand):
source.runForeach(function)

// Run a source with a Sink.fold sink (shorthand):
source.runFold(any)(function)
```
