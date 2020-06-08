name := "akka-streams-case-study"
version := "0.0.1"
scalaVersion := "2.12.11"

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8", // source files are in UTF-8
  "-deprecation", // warn about use of deprecated APIs
  "-unchecked", // warn about unchecked type parameters
  "-feature", // warn about misused language features
  "-language:higherKinds", // allow higher kinded types without `import scala.language.higherKinds`
  // "-Xlint", // enable handy linter warnings
  "-Ypartial-unification" // allow the compiler to unify type constructors of different arities
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.5.26",
  "com.lightbend.akka" %% "akka-stream-alpakka-csv" % "0.18",
  "org.reactivemongo" %% "reactivemongo" % "0.18.7",
  "org.reactivemongo" %% "reactivemongo-akkastream" % "0.18.7",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.typelevel" %% "cats-core" % "1.4.0"
)

ThisBuild / useSuperShell := false
