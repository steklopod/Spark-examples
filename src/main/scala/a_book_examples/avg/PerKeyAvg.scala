package a_book_examples.avg
import a_book_examples.StaticStorage.getSparkContext

//стр. 77
object PerKeyAvg extends App {
  val sc    = getSparkContext(args, "PerKeyAvg")
  val input = sc.parallelize(List(("coffee", 1), ("coffee", 2), ("panda", 4)))

  val result = input
    .combineByKey(
      v => (v, 1),
      (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1),
      (acc1: (Int, Int), acc2: (Int, Int)) =>
        (acc1._1 + acc2._1, acc1._2 + acc2._2)
    )
    .map { case (key, value) => (key, value._1 / value._2.toFloat) }

  result.collectAsMap().foreach(println)
}
