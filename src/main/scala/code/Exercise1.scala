package code

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._

import scala.concurrent.Future

object Exercise1 extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher
<<<<<<< HEAD

  val numbers: Source[Int, NotUsed] =
    Source(1 to 100)

  val printValues: Sink[Any, Future[Done]] =
    Sink.foreach(println)

  val graph: RunnableGraph[Future[Done]] =
    numbers.toMat(printValues)(Keep.right)

  graph.run().onComplete(_ => system.terminate())
=======
>>>>>>> 90de059... Created exercise descriptions
}
