package a_book_examples.word_count

import a_book_examples.StaticStorage._
import org.apache.spark.rdd.RDD

object O1_WordCount extends App {
  private val sc                               = getSparkContext(args, "WordCounts_01")
  private lazy val inputFile                   = getTextFile(sc)
  private lazy val words: RDD[String]          = getWords(inputFile)
  private lazy val counts: RDD[(String, Long)] = count(words)

  args.length match { //если передан 3-й аргумент - сохраняем вывод в файл по пути аргумента
    case x: Int if x > 2 => counts.saveAsTextFile(args(2))
    case _               => printTo5WordsWithCounts(counts)
  }

  def count(wordz: RDD[String]): RDD[(String, Long)] = {
    val counts: RDD[(String, Long)] =
      wordz.map(word => (word, 1L)).reduceByKey {
        case (x, y) => x + y
      }
    counts
  }

  def countAsSeqOfTuples(words: RDD[String]) = {
    words.countByValue()
  }
}
