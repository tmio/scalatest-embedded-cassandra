require 'buildr/scala'
require 'buildr/gpg'
require 'buildr/custom_pom'

repositories.remote << 'http://repo1.maven.org/maven2'

repositories.release_to[:url] = 'https://oss.sonatype.org/service/local/staging/deploy/maven2/'
repositories.release_to[:username] = ENV['USERNAME']
repositories.release_to[:password] = ENV['PASSWORD']

VERSION_NUMBER="1.0.1-SNAPSHOT"

DEPENDENCIES = [
    transitive('org.apache.cassandra:cassandra-all:jar:3.7'),
    'com.typesafe.scala-logging:scala-logging_2.11:jar:3.4.0',
    'commons-io:commons-io:jar:2.5',
    'org.cassandraunit:cassandra-unit:jar:3.0.0.1',
    transitive('com.datastax.cassandra:cassandra-driver-core:jar:3.1.0'),
    transitive('org.scalatest:scalatest_2.11:jar:2.2.6'),
    'org.slf4j:slf4j-api:jar:1.7.21'
]

define('scalatest-embedded-cassandra', :group => 'io.tmio', :version => VERSION_NUMBER) do
  compile.with DEPENDENCIES
  package(:jar)
  package(:sources)
  package(:javadoc)

  test.using(:scalatest)

  pom.add_apache_v2_license
  pom.add_github_project('tmio/scalatest-embedded-cassandra')
  pom.add_developer('atoulme', 'Antoine Toulme')
end
