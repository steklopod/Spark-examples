package a_book_examples.basic
import a_book_examples.Helper.getSparkContext

object Sum extends App {
  val sc = getSparkContext(args, "BasicMap")

  val input  = sc.parallelize(List(1, 2, 3, 4))
  val result = input.fold(0)((x, y) => x + y)
  println(result)
}
