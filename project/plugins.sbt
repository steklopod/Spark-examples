logLevel := Level.Warn

//Форматирование кода: https://scalameta.org/scalafmt/docs/installation.html
addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.5.1")
//sbt-команды
//scalafmt: все файлы
//scalafmtSbt: все *.sbt-файлы
//scalafmtOnly: только один файл

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sbtPluginRepo("snapshots")
)
