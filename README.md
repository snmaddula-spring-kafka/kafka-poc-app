# Kafka POC App

This POC demonstrates basic use-cases like managing topics, publishing and consuming strategies from Kafka using `spring-kafka`

### Step 1: Get Kafka
Download the latest Kafka release and extract it:

    $ tar -xzf kafka_2.13-3.2.0.tgz
    $ cd kafka_2.13-3.2.0

### Step 2: Start the Kafka environment
Run the following commands in order to start all services in the correct order: 

    # Start the ZooKeeper service
    # Note: Soon, ZooKeeper will no longer be required by Apache Kafka.
    $ bin/zookeeper-server-start.sh config/zookeeper.properties

Open another terminal session and run:

    # Start the Kafka broker service
    $ bin/kafka-server-start.sh config/server.properties

Once all services have successfully launched, you will have a basic Kafka environment running and ready to use. 

