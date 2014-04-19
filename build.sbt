name := "userpath"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.novus" %% "salat" % "1.9.5"
)     

play.Project.playScalaSettings
