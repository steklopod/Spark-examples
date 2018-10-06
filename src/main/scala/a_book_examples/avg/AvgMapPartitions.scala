package a_book_examples.avg

import a_book_examples.StaticStorage._

object O3_3_BasicAvgMapPartitions extends App {

  val sc = getSparkContext(args, "BasicAvgMapPartitions")

  val input = sc.parallelize(List(1, 2, 3, 4))

  val result = input
    .mapPartitions(
      partition =>
        // Здесь мы хотим вернуть только один элемент для каждого раздела,
        // но mapPartitions требует, чтобы мы завернули наше возвращение в Iterator
        Iterator(AvgCount(0, 0).merge(partition))
    )
    .reduce((x, y) => x.merge(y))
  println(result)
}

case class AvgCount(var total: Int = 0, var num: Int = 0) {
  def merge(other: AvgCount): AvgCount = {
    total += other.total
    num += other.num
    this
  }
  def merge(input: Iterator[Int]): AvgCount = {
    input.foreach { elem =>
      total += elem
      num += 1
    }
    this
  }
  def avg(): Float = {
    total / num.toFloat
  }
}
