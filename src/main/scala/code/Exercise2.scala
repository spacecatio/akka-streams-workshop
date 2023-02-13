package code

import java.nio.charset.Charset
import java.nio.file.Paths
import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent._
import scala.concurrent.duration._

object Exercise2 extends App {
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val words: Source[ByteString, Future[IOResult]] =
    FileIO
      .fromPath(Paths.get("/usr/share/dict/words"))

  val extractLines: Flow[ByteString, ByteString, NotUsed] =
    Framing.delimiter(ByteString("\n"), maximumFrameLength = 256, allowTruncation = true)

  val utf8Decode: Flow[ByteString, String, NotUsed] =
    Flow[ByteString].map(_.decodeString(Charset.defaultCharset))

  val results =
    words
      .via(extractLines)
      .via(utf8Decode)
      .sliding(2)
      .filter {
        case Seq(a, b) => a.length == b.length
        case _ => false
      }
      .map(_.mkString(" "))
      .map(str => ByteString.fromString(str + "\n"))
      .runWith(FileIO.toPath(Paths.get("pairs.txt")))

  println(Await.result(results, 2.seconds))

  system.terminate()
}