package a_book_examples.word_count
import org.apache.hadoop.mapred.InvalidInputException
import org.apache.spark.SparkContext

import scala.collection.immutable.ListMap

//sbt "run local files\input.txt spam_analize"
object O3_WordCount extends App {
  val master = args.length match {
    case x: Int if x > 0 => args(0)
    case _               => "local"
  }
  // Инициализация контекста
  val sc = new SparkContext(master, "WordCount", System.getenv("SPARK_HOME"))

  // файл, с которого считаются слова. Если не найден - то создадим новую коллекцию слов
  val defaultTextFile = try {
    sc.textFile("files\\input.txt")
  } catch {
    case _: InvalidInputException ⇒
      sc.parallelize(List("pandas", "i like pandas"))
  }
  //читаем файл, переданный во втором аргументе
  val input = args.length match {
    case x: Int if x > 1 => sc.textFile(args(1))
    case _               => defaultTextFile
  }
  //парсим все слова, разбив строки по пробелу
  val words = input.flatMap(line => line.split(" "))

  args.length match {
    //если передан 3-й аргумент - сохраняем вывод в файл по пути аргумента
    case x: Int if x > 2 =>
      val counts =
        words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
      counts.saveAsTextFile(args(2))
    //печатаем ТОП-5 пар по убыванию {слово -> n}, где n - кол-во вхождений слова
    case _ =>
      val wc =
        ListMap(words.countByValue().toSeq.sortWith(_._2 > _._2): _*).take(5)
      println(wc.mkString(",\n"))
  }

}
