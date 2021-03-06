package a_book_examples
import java.io.File

import org.apache.commons.io.FileUtils
import org.apache.hadoop.mapred.InvalidInputException
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.collection.immutable.ListMap

object Helper {
  var args: Array[String] = Array.empty[String]
  val defaultFile: String = "files\\input.txt"
  var outputPath: String = "files\\output\\"

  def getSparkContext(args: Array[String], name: String): SparkContext = {
    this.args = args
    if (args.length > 2) outputPath = args(2) //если передан 3-й аргумент - сохраняем вывод в файл по пути аргумента
    new SparkContext(getMaster(), name, System.getenv("SPARK_HOME"))
  }

  def getMaster(): String = {
    val master = this.args.length match {
      case x: Int if x > 0 => args(0)
      case _               => "local"
    }
    master
  }

  def defaultTextFile(sc: SparkContext): RDD[String] =
    try { sc.textFile(defaultFile) } catch {
      case _: InvalidInputException ⇒
        println(s"Ooops. Default file $defaultFile doesn't exists.")
        sc.parallelize(List("fake", "pandas", "i like pandas"))
    }

  //читаем файл, переданный во втором аргументе
    def getTextFile(sc: SparkContext): RDD[String] = {
      this.args.length match {
        case x: Int if x > 1 => sc.textFile(this.args(1))
        case _               => defaultTextFile(sc)
      }
    }

  //парсим все слова, разбив строки по пробелу
    def getWords(inputFile: RDD[String]): RDD[String] = {
      inputFile.flatMap(line => line.split(" "))
    }

  def count(words: RDD[String]): RDD[(String, Long)] = {
    words.map(word => (word, 1L)).reduceByKey(_ + _)
  }

  def countAsSeqOfTuples(words: RDD[String]) = {
    words.countByValue()
  }

  //печатаем ТОП-5 пар по убыванию {слово -> n}, где n - кол-во вхождений слова
  def printTo5WordsWithCounts(pairs: RDD[(String, Long)] ) = {
    //    val tuples = countAsSeqOfTuples(words).toArray.sortWith(_._2 > _._2)
    val tuples = pairs.collect().sortWith(_._2 > _._2)
    val wc     = ListMap(tuples: _*)
    val top5   = wc.take(5)
    println(top5.mkString(",\n"))
  }

  //TODO - обобщить 2-й аргумент
  def saveAsTxt(outputDir: String, counts: RDD[(String, Long)]): Unit = {
    FileUtils.deleteQuietly(new File(outputDir))
    counts.saveAsTextFile(outputDir)
  }

}