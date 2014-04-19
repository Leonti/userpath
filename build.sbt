name := "userpath"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "se.radley" %% "play-plugins-salat" % "1.4.0"
)

// salat implicit conversions
routesImport += "se.radley.plugin.salat.Binders._"

templatesImport += "org.bson.types.ObjectId"

play.Project.playScalaSettings
