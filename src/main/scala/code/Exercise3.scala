package code

import akka.Done
import akka.actor.ActorSystem
import akka.stream.alpakka.csv.scaladsl.{CsvParsing, CsvToMap}
import akka.stream.scaladsl._
import akka.stream.{ActorMaterializer, IOResult}
import code.Exercise4Setup.getClass
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.{MongoConnection, MongoDriver}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Exercise3 extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val medals: Source[Medal, Future[IOResult]] =
    StreamConverters
      .fromInputStream(() => getClass.getResourceAsStream("/medals-expanded.csv"))
      .via(CsvParsing.lineScanner())
      .via(CsvToMap.toMapAsStrings())
      .map(Medal.unsafeParseCsv)

  def athletesForTeam(team: String): Future[Set[String]] =
    medals
      .filter(_.team == team)
      .map(_.athlete)
      .fold(Set.empty[String])(_ + _)
      .runWith(Sink.head)

  def medalsForTeam(team: String): Future[(Int, Int, Int)] =
    medals
      .filter(_.team == team)
      .map(medal => (medal.bronze, medal.silver, medal.gold))
      // If you're just  using the standard library:
      .fold((0, 0, 0)) { (x, y) =>
        val (bx, sx, gx) = x
        val (by, sy, gy) = y
        (bx + by, sx + sy, gx + gy)
      }
      // Or, if you're using Cats:
      // import cats.implicits._
      // .fold((0, 0, 0))(_ |+| _)
      .runWith(Sink.head)

  def leaderboard: Future[List[(String, Int)]] =
    medals
      .map(medal => medal.athlete -> (medal.bronze + medal.silver + medal.gold))
      .fold(Map.empty[String, Int]) { (accum, pair) =>
        val (athlete, total) = pair
        accum + ((athlete, accum.getOrElse(athlete, 0) + total))
      }
      // Or, if you're using Cats:
      // .map(medal => (medal.athlete -> (medal.bronze + medal.silver + medal.gold))
      // .fold[Map[String, Int]](Map.empty)(_ |+| _)
      .map(_.toList.sortBy { case (_, total) => -total })
      .runWith(Sink.head)
}
