package io.tmio.embeddedcassandra

import org.scalatest.{FlatSpec, Matchers}

import com.datastax.driver.core.Session

class EmbeddedCassandraSpec extends FlatSpec with Matchers with EmbeddedCassandra {
  "An embedded Cassandra server" should "take a body" in {
    val r = withRunningCassandra {
      runInSession((session : Session) => {
        val rs = session.execute("select * from system.local")
        rs.all()
      })
    }
    r should not be null
    r.getClass shouldBe classOf[java.util.ArrayList[_]]
  }
}