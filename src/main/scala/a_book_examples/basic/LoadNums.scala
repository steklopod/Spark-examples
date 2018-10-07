package a_book_examples.basic
import a_book_examples.Helper.getSparkContext

object LoadNums extends App {
  val inputFile  = args(0)
  val sc         = getSparkContext(args, "BasicLoadNums")
  val file       = sc.textFile(inputFile)
  val errorLines = sc.accumulator(0) // Create an Accumulator[Int] initialized to 0
  val dataLines  = sc.accumulator(0) // Create a second Accumulator[Int] initialized to 0
  val counts = file
    .flatMap(line => {
      try {
        val input = line.split(" ")
        val data  = Some((input(0), input(1).toInt))
        dataLines += 1
        data
      } catch {
        case e: java.lang.NumberFormatException => {
          errorLines += 1
          None
        }
        case e: java.lang.ArrayIndexOutOfBoundsException => {
          errorLines += 1
          None
        }
      }
    })
    .reduceByKey(_ + _)
  if (errorLines.value < 0.1 * dataLines.value) {
    counts.saveAsTextFile("output.txt")
  } else {
    println(s"Too many errors ${errorLines.value} for ${dataLines.value}")
  }

}
