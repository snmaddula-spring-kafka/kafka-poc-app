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

### Step 3: Publish a Message
After starting the poc app and once the app is up and running, use the below curl to publish a message.

    curl -X POST localhost:8080/publish \
    -H 'Content-Type: application/json' \
    -d '{"id": 1, "title": "ipod", "description": "ipod blue color 2gb", "price": 23.1 }'

Observe the app logs to see if the message got published successfully.