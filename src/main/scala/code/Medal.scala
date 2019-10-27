package code

import reactivemongo.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}

case class Medal(
  games: String,
  year: Int,
  sport: String,
  discipline: String,
  athlete: String,
  team: String,
  gender: String,
  event: String,
  medal: String,
  gold: Int,
  silver: Int,
  bronze: Int,
)

object Medal {
  implicit val writer: BSONDocumentWriter[Medal] =
    Macros.writer

  implicit val reader: BSONDocumentReader[Medal] =
    Macros.reader
}