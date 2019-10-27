package code

import java.nio.charset.Charset
import java.nio.file.Paths

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.Future

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
}
