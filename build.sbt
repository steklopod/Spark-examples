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

scalacOptions += "-target:jvm-1.8"