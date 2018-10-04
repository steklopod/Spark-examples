/**
  * Illustrates flatMap + countByValue for wordcount.
  */
package a_book_examples

import org.apache.hadoop.mapred.InvalidInputException
import org.apache.spark._

import scala.collection.immutable.ListMap

object O1_WordCount extends App {
  val master = args.length match {
    case x: Int if x > 0 => args(0)
    case _               => "local"
  }

  val sc = new SparkContext(master, "WordCount", System.getenv("SPARK_HOME"))

  val defaultTextFile = try { sc.textFile("files\\spam.txt") } catch {
    case InvalidInputException ⇒ sc.parallelize(List("pandas", "i like pandas"))
  }

  val input = args.length match {
    case x: Int if x > 1 => sc.textFile(args(1))
    case _               => defaultTextFile
  }

  val words = input.flatMap(line => line.split(" "))

  args.length match {
    case x: Int if x > 2 => {
      val counts =
        words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
      counts.saveAsTextFile(args(2))
    }
    case _ => {
      val wc = ListMap(words.countByValue().toSeq.sortWith(_._2 > _._2): _*)
      println(wc.mkString(","))
    }
  }

}
