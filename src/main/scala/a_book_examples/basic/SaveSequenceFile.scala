package a_book_examples.basic
import a_book_examples.Helper.getSparkContext

object SaveSequenceFile extends App {
  val outputFile = args(0)
  val sc         = getSparkContext(args, "BasicSaveSequenceFile")
  val data       = sc.parallelize(List(("Holden", 3), ("Kay", 6), ("Snail", 2)))
  data.saveAsSequenceFile(outputFile)
}
