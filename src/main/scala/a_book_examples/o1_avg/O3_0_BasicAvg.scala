package a_book_examples.o1_avg
import org.apache.spark.rdd.RDD
import a_book_examples.StaticStorage._

object O3_0_BasicAvg extends App {
  val sc     = getSparkContext(args, "BasicAvg")
  val input  = sc.parallelize(List(1, 2, 3, 4))
  val result = computeAvg(input)
  val avg    = result._1 / result._2.toFloat
  println(result)

  def computeAvg(input: RDD[Int]) = {
    input.aggregate((0, 0))(
      (x, y) => (x._1 + y, x._2 + 1),
      (x, y) => (x._1 + y._1, x._2 + y._2)
    )
  }
}
