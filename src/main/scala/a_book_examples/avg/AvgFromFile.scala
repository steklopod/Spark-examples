package a_book_examples.avg
import a_book_examples.StaticStorage._

object AvgFromFile extends App {
  val sc = getSparkContext(args, "BasicAvg")

  val input =  sc.parallelize(List(1, 2, 3, 4))

  val result = input
    .map(_.toInt)
    .aggregate((0, 0))(
      (acc, value) => (acc._1 + value, acc._2 + 1),
      (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2)
    )
  val avg = result._1 / result._2.toFloat
  println(avg)
  println(result)
}
