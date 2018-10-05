package a_book_examples.o1_avg
import a_book_examples.StaticStorage._

//сделать файл по умолчанию
object O3_2_BasicAvgFromFiles extends App {
  if (args.length < 2) {
    println("Usage: [sparkmaster] [inputdirectory] [outputdirectory]")
    System.exit(1)
  }
  val inputFile  = args(0)
  val outputFile = args(1)

  val sc    = getSparkContext(args, "BasicAvgFromFiles")
  val input = sc.wholeTextFiles(inputFile)
  val result = input.mapValues { y =>
    val nums = y.split(" ").map(_.toDouble)
    nums.sum / nums.length.toDouble
  }
  result.saveAsTextFile(outputFile)
}
