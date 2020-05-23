name := "CatsScala"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.1.1",
  "dev.zio" %% "zio" % "1.0.0-RC18",
  "org.typelevel" %% "cats-core" % "1.0.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.typelevel" %% "cats-effect" % "2.1.3",
  "org.typelevel" %% "cats-mtl-core" % "0.7.0"
)

scalacOptions ++= Seq(
  "-Xfatal-warnings",
)