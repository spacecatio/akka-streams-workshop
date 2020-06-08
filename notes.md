# Akka Streams

## Overview

- Why Streaming Algorithms?
- Streaming Architectures

## Why Streaming Algorithms?

## Streaming Architectures

- Push
- Pull
- Hybrid / backpressure

## Overview of Reactive Streams

- [Reactive Streams Specification](https://www.reactive-streams.org/)
- Implementations

## Working with Reactive Streams

### Programming model (define-then-materialize)

### Sources, Sinks, and Flows

The data types:

- `Source[Out, Mat]`
- `Sink[In, Mat]`
- `Flow[In, Out, Mat]`
- `Graph[Mat]`
- `RunnableGraph[Mat]`

Methods for creating sources:

- `Source.apply`
- `Source.fromIterable`
- `Source.unfold`
- etc...

Methods for creating sinks:

- `Sink.foreach`
- `Sink.fold`
- etc...

### Composition

[Useful diagram](https://doc.akka.io/docs/akka/current/stream/stream-composition.html#basics-of-composition-and-modularity)

Methods for transforming sources:

- `source.map()`
- `source.filter()`
- `source.mapAsync()`
- `source.wireTap()`
- `source.flatMap()` ???
- `source.drop{While}()`
- `source.take{While}()`
- etc...

Methods for transforming sinks:

- `sink.contramap()`
- `sink.wireTap()`
- etc...

Methods for connecting sources to sinks:

- `source.to(sink)`
- `source.toMat(sink)(keep)`

### Runnning/materialization

What is materialization?

How to materialize a graph:

- `graph.run()`
- `source.runWith(sink)`

## Relevant Libraries

- Play
- Reactivemongo
- Slick
- [Alpakka](https://doc.akka.io/docs/alpakka/current/file.html)
