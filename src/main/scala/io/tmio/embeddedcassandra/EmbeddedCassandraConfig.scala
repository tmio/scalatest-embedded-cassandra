package io.tmio.embeddedcassandra

import com.typesafe.scalalogging._

import java.io.File

import org.apache.cassandra.config.DatabaseDescriptor

case class EmbeddedCassandraConfig(
  dataFolder:String = "target/embeddedcassandra",
  startupTimeout:Long = 20000,
  yamlResource:String = null
)

object EmbeddedCassandraConfig {
  implicit val defaultEmbeddedCassandraConfig = EmbeddedCassandraConfig()
}