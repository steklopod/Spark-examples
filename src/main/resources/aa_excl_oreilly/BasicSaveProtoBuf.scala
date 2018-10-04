/**
  * Saves a sequence file of people and how many pandas they have seen.
  */
package oreilly

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.io.Text
import org.apache.spark._

object BasicSaveProtoBuf {
  def main(args: Array[String]) {
    val master = args(0)
    val outputFile = args(1)
    val sc =
      new SparkContext(master, "BasicSaveProtoBuf", System.getenv("SPARK_HOME"))
    val conf = new Configuration()
    LzoProtobufBlockOutputFormat.setClassConf(classOf[Places.Venue], conf);
    val dnaLounge = Places.Venue.newBuilder()
    dnaLounge.setId(1);
    dnaLounge.setName("DNA Lounge")
    dnaLounge.setType(Places.Venue.VenueType.CLUB)
    val data = sc.parallelize(List(dnaLounge.build()))
    val outputData = data.map { pb =>
      val protoWritable = ProtobufWritable.newInstance(classOf[Places.Venue]);
      protoWritable.set(pb)
      (null, protoWritable)
    }
    outputData.saveAsNewAPIHadoopFile(
      outputFile,
      classOf[Text],
      classOf[ProtobufWritable[Places.Venue]],
      classOf[LzoProtobufBlockOutputFormat[ProtobufWritable[Places.Venue]]],
      conf
    )
  }
}
