name := "CatsScala"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies +=
  "org.typelevel" %% "cats-core" % "1.0.0"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-Ypartial-unification"
)