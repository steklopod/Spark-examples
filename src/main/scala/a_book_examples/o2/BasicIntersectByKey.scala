package a_book_examples.o2
import a_book_examples.StaticStorage.getSparkContext
import org.apache.spark.rdd.RDD

import scala.reflect.ClassTag

object BasicIntersectByKey extends App {
  val sc = getSparkContext(args,"BasicIntersectByKey")

  val rdd1                       = sc.parallelize(List((1, "panda"), (2, "happy")))
  val rdd2                       = sc.parallelize(List((2, "pandas")))
  val iRdd                       = intersectByKey(rdd1, rdd2)
  val panda: List[(Int, String)] = iRdd.collect().toList
  panda.foreach(println)
  sc.stop()

  def intersectByKey[K: ClassTag, V: ClassTag](
      rdd1: RDD[(K, V)],
      rdd2: RDD[(K, V)]): RDD[(K, V)] = {
    rdd1.cogroup(rdd2).flatMapValues {
      case (Nil, _) => None
      case (_, Nil) => None
      case (x, y)   => x ++ y
    }
  }
}
