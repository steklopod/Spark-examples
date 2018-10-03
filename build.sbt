name := "LearningSpark"

version := "1.0"

val sparkVersion = "2.3.0"

scalaVersion := "2.11.12"

scalacOptions ++= Seq("-unchecked", "-deprecation")

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-streaming" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-hive" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-graphx" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-mllib" % sparkVersion

//libraryDependencies += "com.twitter.elephantbird" % "elephant-bird-core" % "4.17"
//libraryDependencies += "org.eclipse.jetty" % "jetty-client" % "9.4.12.v20180830"
//libraryDependencies += "org.eclipse.jetty" % "jetty-server" % "9.4.12.v20180830"
//libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "3.1.1"
//libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-jobclient" % "3.1.1" % "provided"
//libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-common" % "3.1.1"

scalacOptions += "-target:jvm-1.8"