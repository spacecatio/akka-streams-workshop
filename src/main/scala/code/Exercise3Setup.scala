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

object Exercise3Setup extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val medalSource: Source[Medal, Future[IOResult]] =
    StreamConverters
      .fromInputStream(() => getClass.getResourceAsStream("/medals-expanded.csv"))
      .via(CsvParsing.lineScanner())
      .via(CsvToMap.toMapAsStrings())
      .map { data =>
        Medal(
          games = data("Games"),
          year = data("Year").toInt,
          sport = data("Sport"),
          discipline = data("Discipline"),
          athlete = data("Athlete"),
          team = data("Team"),
          gender = data("Gender"),
          event = data("Event"),
          medal = data("Medal"),
          gold = data("Gold").toInt,
          silver = data("Silver").toInt,
          bronze = data("Bronze").toInt,
        )
      }

  def medalCollection: Future[BSONCollection] =
    for {
      uri <- Future.fromTry(MongoConnection.parseURI(s"mongodb://localhost:27017"))
      conn <- Future.fromTry(MongoDriver().connection(uri, strictUri = true))
      db <- conn.database("olympics")
    } yield db.collection[BSONCollection]("medals")

  def medalSink(coll: BSONCollection): Sink[Medal, Future[Done]] =
    Sink.foreachAsync[Medal](1) { medal =>
      println(medal)
      coll.insert.one(medal).map(_ => ())
    }

  def program: Future[Unit] =
    for {
      coll <- medalCollection
      _ <- medalSource.runWith(medalSink(coll))
      _ = system.terminate()
    } yield ()

  println(Await.result(program, 1.minute))
}
