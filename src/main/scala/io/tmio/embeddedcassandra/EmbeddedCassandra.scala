package io.tmio.embeddedcassandra

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.Suite
import java.io.File

import org.apache.cassandra.config.DatabaseDescriptor
import org.apache.commons.io.FileUtils

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session

import org.cassandraunit.utils.EmbeddedCassandraServerHelper

import scala.io.Source._


trait EmbeddedCassandra extends EmbeddedCassandraSupport {
  this: Suite =>
}

object EmbeddedCassandra extends EmbeddedCassandraSupport {

}

sealed trait EmbeddedCassandraSupport extends LazyLogging {

  /**
    * Starts a Cassandra instance, then executes the body passed as a parameter.
    *
    * @param body   the function to execute
    * @param config an implicit [[EmbeddedCassandraConfig]]
    */
  def withRunningCassandra[R](body: => R)(implicit config: EmbeddedCassandraConfig) = {

    start(config)

    try {
      body
    } finally {
      stop()
    }
  }
  
  

  def start(implicit config: EmbeddedCassandraConfig) = {
    logger.debug("Starting Embedded Cassandra")
    configure(config)
    EmbeddedCassandraServerHelper.startEmbeddedCassandra(configFile(config), config.dataFolder, config.startupTimeout)
    logger.debug("Started Embedded Cassandra")
  }
  
  def stop() = {
    logger.debug("Stopping Embedded Cassandra")

    logger.debug("Stopped Embedded Cassandra")
  }

  def clusterName = DatabaseDescriptor.getClusterName()

  def host = DatabaseDescriptor.getRpcAddress().getHostName()

  def rpcPort = DatabaseDescriptor.getRpcPort()

  def nativeTransportPort = DatabaseDescriptor.getNativeTransportPort()

  def runInSession[R](body: (Session) => R) = {
    val cluster = Cluster.builder().addContactPoint(host).withPort(nativeTransportPort).build()
    val session = cluster.connect()
    try {
      body(session)
    } finally {
      cluster.close()
    }
  }

  private def configFileContents(implicit config: EmbeddedCassandraConfig) : String = {
    Option(config.yamlResource) match {
      case Some(resource) => fromFile(resource).mkString
      case None => fromInputStream(getClass.getResourceAsStream("/" + EmbeddedCassandraServerHelper.DEFAULT_CASSANDRA_YML_FILE)).mkString
    }
  }
  
  private def configure(implicit config: EmbeddedCassandraConfig) = {
    val cassandraDirectory = new File(config.dataFolder)
    FileUtils.deleteDirectory(cassandraDirectory)
    cassandraDirectory.mkdirs()
    val ymlFile = configFile(config)
    FileUtils.writeStringToFile(ymlFile, configFileContents(config), "UTF-8")
  }

  private def configFile(implicit config: EmbeddedCassandraConfig) = {
    val cassandraDirectory = new File(config.dataFolder)
    new File(cassandraDirectory, "cassandra.yml")
  }
}