name := "LearningSpark"

version := "1.0"

val sparkVersion  = "2.3.2"
val junitJupiter  = "5.2.0"
val junitPlatform = "1.2.0"
val akkaVersion   = "2.5.16"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.1",
  "org.scalatest"          %% "scalatest"                % "3.2.0-SNAP10" % Test,
  "org.scalacheck"         %% "scalacheck"               % "1.14.0" % "test",
  "junit"                  % "junit"                     % "4.12" % Test,
  "org.junit.jupiter"      % "junit-jupiter-api"         % junitJupiter % Test,
  "org.junit.jupiter"      % "junit-jupiter-engine"      % junitJupiter % Test,
  "org.junit.jupiter"      % "junit-jupiter-params"      % junitJupiter % Test,
  "org.junit.platform"     % "junit-platform-launcher"   % junitPlatform % Test,
  "org.junit.platform"     % "junit-platform-engine"     % junitPlatform % Test,
  "org.junit.platform"     % "junit-platform-runner"     % junitPlatform % Test,
  "com.typesafe.akka"      %% "akka-actor"               % akkaVersion,
  "com.typesafe.akka"      %% "akka-testkit"             % akkaVersion,
  "mysql"                  % "mysql-connector-java"      % "5.1.6",
//  https://github.com/scopt/scopt
  "com.github.scopt" % "scopt_2.11" % "3.7.0",
  "org.apache.spark"       %% "spark-core"               % sparkVersion,
  "org.apache.spark"       %% "spark-streaming"          % sparkVersion,
  "org.apache.spark"       %% "spark-sql"                % sparkVersion,
  "org.apache.spark"       %% "spark-hive"               % sparkVersion,
  "org.apache.spark"       %% "spark-graphx"             % sparkVersion,
  "org.apache.spark"       %% "spark-mllib"              % sparkVersion
)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("public"),
  Classpaths.typesafeReleases,
  DefaultMavenRepository
)
