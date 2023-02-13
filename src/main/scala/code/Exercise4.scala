package code

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl.Source
import reactivemongo.akkastream.{State, cursorProducer}
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.{MongoConnection, MongoDriver}
import reactivemongo.bson.BSONDocument

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object Exercise4 extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  def medalCollection: Future[BSONCollection] =
    for {
      uri <- Future.fromTry(
        MongoConnection.parseURI(s"mongodb://localhost:27017")
      )
      conn <- Future.fromTry(MongoDriver().connection(uri, strictUri = true))
      db <- conn.database("olympics")
    } yield db.collection[BSONCollection]("medals")

  def medalSource: Future[Source[Medal, Future[State]]] =
    medalCollection.map { coll =>
      coll
        .find(BSONDocument(), None)
        .cursor[Medal]()
        .documentSource()
    }
}
