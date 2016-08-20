package io.tmio.embeddedcassandra

import org.scalatest._

class EmbeddedCassandraConfigSpec extends FlatSpec with Matchers {
  
  "An embedded Cassandra server config" should "use a default file location" in {
    assert(new EmbeddedCassandraConfig().dataFolder == "target/embeddedcassandra")
  }
  
  it should "allow to override the data location" in {
    assert(new EmbeddedCassandraConfig(dataFolder = "foo/bar").dataFolder == "foo/bar")
  }
  
  it should "use a default startup timeout" in {
    assert(new EmbeddedCassandraConfig().startupTimeout == 20000)
  }
  
  it should "allow to override the startup timeout" in {
    assert(new EmbeddedCassandraConfig(startupTimeout = 40000).startupTimeout == 40000)
  }
  
  
}