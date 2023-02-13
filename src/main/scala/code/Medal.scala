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

  def unsafeParseCsv(data: Map[String, String]): Medal =
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