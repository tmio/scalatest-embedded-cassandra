import com.typesafe.scalalogging._

import java.io.File

import org.apache.cassandra.config.DatabaseDescriptor
import org.apache.commons.io.FileUtils

import scala.io.Source._

case class EmbeddedCassandra(dataFolder:String = "target/embeddedcassandra", startupTimeout:Long = 20000, yamlResource:String = null) extends LazyLogging {
  
  def start() = {
    logger.debug("Starting Embedded Cassandra")
    configure()
    System.setProperty("cassandra.config", "file:" + configFile().getAbsolutePath());
    System.setProperty("cassandra-foreground", "true");
    System.setProperty("cassandra.native.epoll.enabled", "false");
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
  
  private def configFile() : File = {
    new File(dataFolder, "cassandra.yml")
  }
  
  private def configFileContents() = {
    Option(yamlResource) match {
      case Some(resource) => fromFile(resource).mkString
      case None => fromInputStream(getClass.getResourceAsStream("cassandra.yml")).mkString
    }
  }
  
  private def configure() = {
    val cassandraDirectory = new File(dataFolder)
    FileUtils.deleteDirectory(cassandraDirectory)
    
  }
}