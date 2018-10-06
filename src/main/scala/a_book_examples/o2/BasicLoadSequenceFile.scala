package a_book_examples.o2
import a_book_examples.StaticStorage.getSparkContext
import org.apache.hadoop.io.{IntWritable, Text}

object BasicLoadSequenceFile extends App {
  val inFile = args(0)
  val sc     = getSparkContext(args, "BasicLoadSequenceFile")
  val data =
    sc.sequenceFile(inFile, classOf[Text], classOf[IntWritable]).map {
      case (x, y) =>
        (x.toString, y.get())
    }
  println(data.collect().toList)
}
