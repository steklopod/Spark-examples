logLevel := Level.Warn

//https://github.com/sbt/sbt-scalariform
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.2") 

resolvers ++= Seq( 
  Resolver.sonatypeRepo("snapshots"), 
  Resolver.sbtPluginRepo("snapshots") 
  )