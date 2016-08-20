package io.tmio.embeddedcassandra

import org.scalatest.{FlatSpec, Matchers}

import com.datastax.driver.core.Session

/**
  * Created by lb on 8/19/16.
  */
class EmbeddedCassandraSpec extends FlatSpec with Matchers with EmbeddedCassandra {

  "An embedded Cassandra server" should "take a body" in {
    withRunningCassandra() {
      runInSession((session : Session) => {
//        val rs = session.execute("select * from system.local")
//        rs.all()
    })
    }

  }
}
