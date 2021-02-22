# Akka Streams

## Numbers Exercise

Implement and test the following:

1. Create a stream of the numbers X to Y (where X and Y are Ints)
2. Sum the numbers X to Y
3. Create a stream of the factorials of X and Y

## Dictionary Exercise

Implement and test the following:

1. Read at the contents of a text file

2. Use this to read the file `/usr/share/dict/words`
   for the following:

3. Count the words in the file.

4. Count the vowels in the file.

5. Count the words containing
   a user-supplied substring.

6. Print adjacent pairs of words
   with the same length (use `source.sliding()`).

## Olympic Medals Redux

1. Write the contents of `medals-expanded.csv`
   to a collection called `medals`
   in a MongoDB database called `olympics`
   (see `Exercise3Setup.scala`)

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
