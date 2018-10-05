package a_book_examples
import org.apache.hadoop.mapred.InvalidInputException
import org.apache.spark.SparkContext

object StaticStorage {

  def getMaster(args: Array[String]): String = {
    val master = args.length match {
      case x: Int if x > 0 => args(0)
      case _               => "local"
    }
    master
  }

  def getSparkContext(args: Array[String], name: String): SparkContext = {
    new SparkContext(getMaster(args), name, System.getenv("SPARK_HOME"))
  }

  def defaultTextFile(sc: SparkContext) =
    try {
      sc.textFile("files\\spam.txt")
    } catch {
      case _: InvalidInputException ⇒
        sc.parallelize(List("pandas", "i like pandas"))
    }

  //читаем файл, переданный во втором аргументе
  def getInput(args: Array[String], sc: SparkContext) = args.length match {
    case x: Int if x > 1 => sc.textFile(args(1))
    case _               => defaultTextFile(sc)
  }

}
