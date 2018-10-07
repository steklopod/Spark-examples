package a_book_examples.word_count

import a_book_examples.Helper._
import org.apache.spark.rdd.RDD

//sbt "run local files\input.txt"
object WordCount extends App {
  private val sc                               = getSparkContext(args, "WordCount")
  private lazy val inputFile                   = getTextFile(sc)
  private lazy val words: RDD[String]          = getWords(inputFile)
  private lazy val counts: RDD[(String, Long)] = count(words)

  args.length match { //если передан 3-й аргумент - сохраняем вывод в файл по пути аргумента
    case x: Int if x > 2 => saveAsTxt(args(2), counts)
    case _               => printTo5WordsWithCounts(counts)
  }

   counts.toDebugString
   counts.cache()
   counts.count()

   saveAsTxt(output, counts)

   counts.repartition(5)
   saveAsTxt(s"$output Out-2", counts)

   var repartitioned = counts.repartition(5)
   saveAsTxt(s"$output Out-3", repartitioned)

   counts.unpersist()
}
