import java.lang.System.Logger
import java.util.Properties
import java.util.logging.Logger
import com.sun.tools.sjavac.Log.Level
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

object KafaProducerEx extends App {

  import com.sun.org.slf4j.internal.Logger
  import com.sun.org.slf4j.internal.LoggerFactory

  val logger = LoggerFactory.getLogger(getClass)
 // logger.getLogger("").setLevel(Level.ERROR)
  var exitCode:Int= 0

  override def main(args: Array[String]): Unit = {

  // for reading Application properties file
    val conf = ConfigFactory.load()
  // for setting env. like dev or prod
    val envProps = conf.getConfig(args(0))
    //bootstrap.server localhost:9092
    val ConnectionProperties = new Properties()

    ConnectionProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,envProps.getString("bootstrap.server"))
    ConnectionProperties.put(ProducerConfig.CLIENT_ID_CONFIG,"Producer")
    ConnectionProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
    ConnectionProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String,String](ConnectionProperties)

val data = new ProducerRecord[String,String]("kafka-testing","Key","value")

  producer.send(data)
  producer.close()

  }

}
