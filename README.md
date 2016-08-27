[![Build Status](https://travis-ci.org/tmio/scalatest-embedded-cassandra.svg?branch=master)](https://travis-ci.org/tmio/scalatest-embedded-cassandra)

#Embedded Cassandra for Scalatest

This project allows you to test your code depending on Cassandra with scalatest in style.

```
withRunningCassandra() {
  runInSession((session : Session) => {
    val rs = session.execute("select * from system.local")
    rs.all()
  })
}

```


#Credits

The concept and execution of this project is modeled after the excellent https://github.com/manub/scalatest-embedded-kafka:"ScalaTest embedded Kafka project".

#License

Copyright 2016 Antoine Toulme

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.