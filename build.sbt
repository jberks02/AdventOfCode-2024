import sbt.Keys.libraryDependencies

val scala3Version = "3.6.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "AdventOfCode-2024",
    version := "1.0.0",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test,
    libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"
)
