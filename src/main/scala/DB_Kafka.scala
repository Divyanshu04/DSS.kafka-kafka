package dbtokafka

import org.apache.kafka.clients.producer.kafkaProducer
import org.apache.kafka.clients.producer.producer
import org.apache.log4j.Logger
import org.dbtokafka.jdbc.ApplicationConfig
import org.dbtokafka.jdbc.HiveShellConnection
import java.sql.Timestamp
import java.util
import java.util.{List, Map, Properties}
import java.util.logging.Logger


object DBIngestionKafka {
  private[dbtokafka] val logger = Logger.getLogger(classOf[DBIngestionKafka])
  private[dbtokafka] val applicationContext = new Nothing(classOf[Nothing])
  private[dbtokafka] val properties = applicationContext.getBean("kafkaProperties").asInstanceOf[Nothing]

  def main(args: Array[String]): Unit = {
    val dbIngestionKafka = new DBIngestionKafka
    val hiveShellConnection = new Nothing
    val jdbcOperation = applicationContext.getBean("jdbcOperation").asInstanceOf[Nothing]
    val kafkaInsert = applicationContext.getBean("kafkaInsert").asInstanceOf[Nothing]
    logger.info("topic name:" + kafkaInsert.getTopic)
    logger.info("properties.getProperty(\"db.batch.size\")" + properties.getProperty("db.batch.size"))
    val batchSize = jdbcOPeration.getBatchSize
    val starSeqNum = jdbcOperation.getEndSeqNum
  }
}

class DBIngestionKafka {
  private val jsonObject = null
  private val list = null
  private val current = 0
  private val start = 0
  private val count = 0
  private[dbtokafka] val srcbacklog = 0
  private[dbtokafka] val startTime = 0L
  private[dbtokafka] val jobEndTime = null
  private[dbtokafka] val jobStatus = "in progress"
}
