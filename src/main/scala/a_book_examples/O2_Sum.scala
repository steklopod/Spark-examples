package a_book_examples
import a_book_examples.StaticStorage.getSparkContext

object O2_Sum extends App {
  val sc = getSparkContext(args, "BasicMap")

  val input  = sc.parallelize(List(1, 2, 3, 4))
  val result = input.fold(0)((x, y) => x + y)
  println(result)
}
