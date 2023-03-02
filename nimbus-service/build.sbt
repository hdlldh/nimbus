
val scala213Version = "2.13.6"
val scala212Version = "2.12.12"
val slickVersion = "3.4.1"

scalaVersion := "2.13.10"

val playScalaTest           = "org.scalatestplus.play"  %% "scalatestplus-play"           % "5.1.+"     % Test
val postgressql             = "org.postgresql"          %  "postgresql"                   % "42.5.4"
val slickArtifact           = "com.typesafe.slick"      %% "slick"                        % slickVersion
val slickHikaricp           = "com.typesafe.slick"      %% "slick-hikaricp"               % slickVersion
val playSlickEvolutions     = "com.typesafe.play"       %% "play-slick-evolutions"        % "5.1.0"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
)
lazy val supportedScalaVersions = List(scala212Version, scala213Version)

lazy val commonSettings = Seq(
  scalaVersion := scala213Version,
  scalacOptions := scalacOptions.value.diff(Seq("-Xfatal-warnings")),
  organization := "com.goku"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  aggregate(db, web).
  settings(
    name := "nimbus-service",
    run := (Compile / (web / run)).evaluated
  )

lazy val db = (project in file("nimbus-db")).
  settings(commonSettings: _*).
  settings(
    name := "nimbus-db",
    crossScalaVersions := supportedScalaVersions,
    libraryDependencies ++= Seq(
      postgressql,
      slickArtifact
    )
  )

lazy val web = (project in file("nimbus-web")).
  enablePlugins(PlayScala, BuildInfoPlugin).
  settings(commonSettings: _*).
  settings(
    name := "nimbus-web",
    libraryDependencies ++= Seq(
      guice,
      playScalaTest,
      playSlickEvolutions
    )
  ).
  dependsOn(db)


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.goku.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.goku.binders._"
