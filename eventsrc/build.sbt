val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "eventsrc",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "com.eventstore" % "db-client-java" % "0.6",

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
