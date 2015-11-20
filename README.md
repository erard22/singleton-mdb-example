singleton-mdb-example: Shows how to run a mdb as a 'singleton'
========================
Author: Michel Erard

What is it?
-----------

A little demo that show how you can run a message driven bean as a kind of a singleton.

System requirements
-------------------

All you need to build this project is Java 7.0 (Java SDK 1.7) or better, Maven 3.1 or better.

The application this project produces is designed to be run on JBoss WildFly.


Start JBoss WildFly
-------------------------

For this example we start the JBoss with the full profile to have the messaging subsystem up and running.

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh -c standalone-full.xml
        For Windows: JBOSS_HOME\bin\standalone.bat -c standalone-full.xml

 
Build and Deploy
-------------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this project.
3. Type this command to build and deploy the archive:

        mvn clean package wildfly:execute-commands wildfly:deploy

4. This will add a single-instance bean pool to the standalone-full.xml and deploy `target/singleton-mdb-example.war` to the running instance of the server.
 

Send Messages to the queue
---------------------

The application will be running at the following URL: <http://localhost:8080/singleton-mdb-example/>.

To send a message: http://localhost:8080/singleton-mdb-example/rest/message/put_your_message_here


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this project.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy
