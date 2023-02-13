package code

import akka.Done
import akka.actor.ActorSystem
import akka.stream.alpakka.csv.scaladsl.{CsvParsing, CsvToMap}
import akka.stream.scaladsl._
import akka.stream.{ActorMaterializer, IOResult}
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.{MongoConnection, MongoDriver}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Exercise3 extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val csvSource: Source[Map[String, String], Future[IOResult]] =
    StreamConverters
      .fromInputStream(() =>
        getClass.getResourceAsStream("/medals-expanded.csv")
      )
      .via(CsvParsing.lineScanner())
      .via(CsvToMap.toMapAsStrings())
}
