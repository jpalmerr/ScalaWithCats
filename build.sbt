name := "CatsScala"

version := "0.1"

scalaVersion := "2.13.1"

val Http4sVersion = "0.21.3"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.1.1",
  "dev.zio" %% "zio" % "1.0.0-RC18",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.typelevel" %% "cats-effect" % "2.1.3",
  "org.typelevel" %% "cats-mtl-core" % "0.7.0",
  "io.circe"      %% "circe-refined"   % "0.13.0",
  "org.typelevel" %% "cats-tagless-macros" % "0.14.0",
  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
  "org.http4s" %% "http4s-circe" % Http4sVersion,
  "org.http4s" %% "http4s-dsl" % Http4sVersion,
)

scalacOptions ++= Seq(
  "-Xfatal-warnings",
)