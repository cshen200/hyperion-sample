hyperion-sample
===============

Hyperion sample project.

`mvn clean package` to build.

``./t.sh` to start up

Pull up the included swagger page at: http://localhost:8080/client

This example uses an embedded h2 database by default. To use MySQL uncomment the reference to the mysql.properties
file in spring-main.xml and fill in the connection details in the mysql.properties file.
