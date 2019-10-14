name := "THUNDER_Of_KAFKA"

version := "0.1"

scalaVersion := "2.12.6"


libraryDependencies += "com.typesafe" % "config" % "1.3.2"
// https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
libraryDependencies ++= Seq("org.apache.kafka" % "kafka-clients" % "2.3.0" exclude("org.slf4j", "slf4j-simple"))

// https://mvnrepository.com/artifact/org.apache.kafka/kafka
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.3.0"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-streams
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "2.3.0"
