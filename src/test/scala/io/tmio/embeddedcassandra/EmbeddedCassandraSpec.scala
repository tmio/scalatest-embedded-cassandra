import org.scalatest._

class EmbeddedCassandraSpec extends FlatSpec with Matchers {
  
  "An embedded Cassandra server" should "use a default file location" in {
    assert(new EmbeddedCassandra().dataFolder == "target/embeddedcassandra")
  }
  
  it should "allow to override the data location" in {
    assert(new EmbeddedCassandra(dataFolder = "foo/bar").dataFolder == "foo/bar")
  }
  
  it should "use a default startup timeout" in {
    assert(new EmbeddedCassandra().startupTimeout == 20000)
  }
  
  it should "allow to override the startup timeout" in {
    assert(new EmbeddedCassandra(startupTimeout = 40000).startupTimeout == 40000)
  }
  
  
}