# Introduction

This is an example application used in [Wildfly Guide: Use MicroProfile Reactive Messaging with AMQP Connector with SSL Connection to AMQ 7 on OpenShift](https://www.wildfly.org/guides/use-microprofile-amqp-connector-with-ssl-openshift)

This example runs on the trimmed WildFly application server and is provisioned using Wildfly Glow. It uses secured SSL/TLS to 
connect to remote AMQ 7/ActiveMQ Artemis broker deployed on Openshift. 
The example is configured to use the correct Maven dependencies and ensure that you test and compile the example against the correct runtime environment.

# Building and running
## Prerequisites
* JDK 11+ installed with JAVA_HOME configured appropriately
* Apache Maven 3.9+

## Building
Application can be built by:
```shell
mvn clean package
```

## Running
Start application by:
```shell
AMQ_HOST=<amq-broker-route> java -jar ./target/server-bootable.jar
```

Expected output:
```shell
$ AMQ_HOST=<amq-broker-route> java -jar ./target/server-bootable.jar
14:40:32,046 INFO  [org.wildfly.jar] (main) WFLYJAR0007: Installed server and application in /tmp/wildfly-bootable-server1445463548439698182, took 526ms
14:40:32,265 INFO  [org.wildfly.jar] (main) WFLYJAR0008: Server options: [--read-only-server-config=standalone.xml]
14:40:32,307 INFO  [org.jboss.msc] (main) JBoss MSC version 1.5.2.Final
14:40:32,312 INFO  [org.jboss.threads] (main) JBoss Threads version 2.4.0.Final
14:40:32,388 INFO  [org.jboss.as] (MSC service thread 1-2) WFLYSRV0049: WildFly Full 31.0.1.Final (WildFly Core 23.0.3.Final) starting
...
14:40:35,529 INFO  [io.smallrye.reactive.messaging.amqp] (vert.x-eventloop-thread-0) SRMSG16213: Connection with AMQP broker established
14:40:35,541 INFO  [io.smallrye.reactive.messaging.amqp] (vert.x-eventloop-thread-0) SRMSG16213: Connection with AMQP broker established
14:40:35,550 INFO  [stdout] (vert.x-eventloop-thread-0) ----> Calling generate!!!!
14:40:35,551 INFO  [stdout] (vert.x-eventloop-thread-0) =====> Creating Next with 1
14:40:35,553 INFO  [io.smallrye.reactive.messaging.amqp] (vert.x-eventloop-thread-0) SRMSG16203: AMQP Receiver listening address source
14:40:35,652 INFO  [stdout] (pool-13-thread-1) ---> Sending 1
14:40:35,660 INFO  [stdout] (pool-13-thread-1) ----> Calling generate!!!!
14:40:35,661 INFO  [stdout] (pool-13-thread-1) =====> Creating Next with 2
14:40:35,674 INFO  [stdout] (vert.x-eventloop-thread-0) ---> Received 1
14:40:35,761 INFO  [stdout] (pool-13-thread-1) ---> Sending 2
14:40:35,761 INFO  [stdout] (pool-13-thread-1) ----> Calling generate!!!!
14:40:35,761 INFO  [stdout] (pool-13-thread-1) =====> Creating Next with 4
14:40:35,765 INFO  [stdout] (vert.x-eventloop-thread-0) ---> Received 2
...
```



 
