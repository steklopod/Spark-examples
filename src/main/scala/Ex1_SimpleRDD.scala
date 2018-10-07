import org.apache.spark.{SparkContext, SparkConf}

object Ex1_SimpleRDD {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Ex1_SimpleRDD").setMaster("local[4]")
    val sc   = new SparkContext(conf)

    // помещаем некоторые данные в RDD
    val numbers    = 1 to 10
    val numbersRDD = sc.parallelize(numbers, 4)
    println("Распечатаем каждый элемент оригинального RDD")
    numbersRDD.foreach(println)

    // тривиально операция над числами
    val stillAnRDD = numbersRDD.map(n => n.toDouble / 10)

    // вернуть данные
    val nowAnArray = stillAnRDD.collect()
    // интересно, как массив сортируется, но RDD нет
    println("Теперь напечатайте каждый элемент преобразованного массива")
    nowAnArray.foreach(println)

    // изучить свойства RDD
    val partitions = stillAnRDD.glom()
    println("У нас должно быть 4 раздела (раздела)")
    println(partitions.count())
    partitions.foreach(a => {
      println(
        "Содержание раздела:" +
          a.foldLeft("")((s, e) => s + " " + e))
    })
  }
}
