package a_book_examples.word_count
import a_book_examples.StaticStorage._
import org.apache.spark.rdd.RDD

//sbt "run local files\input.txt"
object O2_WordCount extends App {
  val sc        = getSparkContext(args, "WordCounts_02")
  val inputFile = getTextFile(sc)

  val counts: RDD[(String, Long)] = getWords(inputFile)
    .map(word => (word, 1L))
    .reduceByKey(_ + _)

  counts.toDebugString
  counts.cache()
  counts.count()

  counts.saveAsTextFile(output)

  counts.repartition(5)

  counts.saveAsTextFile(s"$output-2")

  var repartitioned = counts.repartition(5)

  repartitioned.saveAsTextFile(s"$output-3")

  counts.unpersist()
}
