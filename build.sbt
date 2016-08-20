import sbtrelease.Version


lazy val commonSettings = Seq(
  name := "scalatest-embedded-cassandra",
  organization := "io.tmio",
  scalaVersion := "2.11.8",
  crossScalaVersions := Seq("2.11.8"),
  homepage := Some(url("https://github.com/tmio/scalatest-embedded-cassandra")),
  parallelExecution in Test := false,
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.5",
    "org.apache.cassandra" % "cassandra-all" % "3.7",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0",
    "commons-io" % "commons-io" % "2.5",
    "org.cassandraunit" % "cassandra-unit" % "3.0.0.1",
    "com.datastax.cassandra" % "cassandra-driver-core" % "3.1.0"


)
)

lazy val publishSettings = Seq(
  licenses +=("ASL v2", url("http://www.apache.org/licenses/LICENSE-2.0")),
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  pomExtra :=
    <scm>
      <url>https://github.com/tmio/scalatest-embedded-cassandra</url>
      <connection>scm:git:git@github.com:tmio/scalatest-embedded-cassandra.git</connection>
    </scm>
      <developers>
        <developer>
          <id>atoulme</id>
          <name>Antoine Toulme</name>
          <url>http://twitter.com/_tmio</url>
        </developer>
      </developers>
)

lazy val releaseSettings = Seq(
  releaseVersionBump := Version.Bump.Minor,
  releaseCrossBuild := true
)

lazy val root = (project in file("."))
  .settings(publishSettings: _*)
  .settings(commonSettings: _*)
  .settings(releaseSettings: _*)
