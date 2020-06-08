package code

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._

import scala.concurrent._
import scala.concurrent.duration._

object Exercise0 extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  // Recipe:
  //  1. Create source
  //  2. Create sink
  //  3. Connect source to sink
  //  4. Run (and maybe collect final value)
}
