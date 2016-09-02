package io.tmio.embeddedcassandra

import org.scalatest._

class EmbeddedCassandraConfigSpec extends FlatSpec with Matchers {
  "An embedded Cassandra server config" should "use a default file location" in {
    def doNotStartCassandra(implicit config: EmbeddedCassandraConfig) {
      config.dataFolder shouldBe "target/embeddedcassandra"
    }
    
    doNotStartCassandra
  }
  
  it should "allow to override the data location" in {
    def doNotStartCassandra(implicit config: EmbeddedCassandraConfig) {
      config.dataFolder shouldBe "foo/bar"
    }

    implicit val config = EmbeddedCassandraConfig(dataFolder = "foo/bar")
    doNotStartCassandra
  }
  
  it should "use a default startup timeout" in {
    def doNotStartCassandra(implicit config: EmbeddedCassandraConfig) {
      config.startupTimeout shouldBe 20000
    }

    implicit val config = EmbeddedCassandraConfig(startupTimeout = 20000)
    doNotStartCassandra
  }
  
  it should "allow to override the startup timeout" in {
    def doNotStartCassandra(implicit config: EmbeddedCassandraConfig) {
      config.startupTimeout shouldBe 40000
    }

    implicit val config = EmbeddedCassandraConfig(startupTimeout = 40000)
    doNotStartCassandra
  }
}