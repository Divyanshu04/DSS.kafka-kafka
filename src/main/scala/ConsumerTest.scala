package com.test.groups

import kafka.consumer.ConsumerConfig
import kafka.consumer.KafkaStream
import kafka.javaapi.consumer.ConsumerConnector
import java.util
import java.util.Properties
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


object ConsumerGroupExample {
  private def createConsumerConfig(a_zookeeper: String, a_groupId: String) = {
    val props = new Properties
    props.put("zookeeper.connect", a_zookeeper)
    props.put("group.id", a_groupId)
    props.put("zookeeper.session.timeout.ms", "400")
    props.put("zookeeper.sync.time.ms", "200")
    props.put("auto.commit.interval.ms", "1000")
    new Nothing(props)
  }

  def main(args: Array[String]): Unit = {
    val zooKeeper = args(0)
    val groupId = args(1)
    val topic = args(2)
    val threads = args(3).toInt
    val example = new ConsumerGroupExample(zooKeeper, groupId, topic)
    example.run(threads)
    try
      Thread.sleep(10000)
    catch {
      case ie: InterruptedException =>

    }
    example.shutdown()
  }
}

class ConsumerGroupExample(val a_zookeeper: String, val a_groupId: String, val topic: String) {
  consumer = kafka.consumer.Consumer.createJavaConsumerConnector(ConsumerGroupExample.createConsumerConfig(a_zookeeper, a_groupId))
  final private var consumer = null
  private var executor = null

  def shutdown(): Unit = {
    if (consumer != null) consumer.shutdown
    if (executor != null) executor.shutdown()
    try
        if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) System.out.println("Timed out waiting for consumer threads to shut down, exiting uncleanly")
    catch {
      case e: InterruptedException =>
        System.out.println("Interrupted during shutdown, exiting uncleanly")
    }
  }

  def run(a_numThreads: Int): Unit = {
    val topicCountMap = new util.HashMap[String, Integer]
    topicCountMap.put(topic, new Integer(a_numThreads))
    val consumerMap = consumer.createMessageStreams(topicCountMap)
    val streams = consumerMap.get(topic)
    // now launch all the threads
    //
    executor = Executors.newFixedThreadPool(a_numThreads)
    // now create an object to consume the messages
    var threadNumber = 0
    import scala.collection.JavaConversions._
    for (stream <- streams) {
      executor.submit(new Nothing(stream, threadNumber))
      threadNumber += 1
    }
  }
}