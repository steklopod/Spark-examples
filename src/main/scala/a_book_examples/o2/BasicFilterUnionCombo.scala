package a_book_examples.o2
import org.apache.spark.{SparkConf, SparkContext}

object BasicFilterUnionCombo extends App {
  val conf = new SparkConf
  conf.setMaster(args(0))
  val sc          = new SparkContext(conf)
  val inputRDD    = sc.textFile(args(1))
  val errorsRDD   = inputRDD.filter(_.contains("error"))
  val warningsRDD = inputRDD.filter(_.contains("warn"))
  val badLinesRDD = errorsRDD.union(warningsRDD)
  println(badLinesRDD.collect().mkString("\n"))
}
