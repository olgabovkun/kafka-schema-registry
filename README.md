# Integrate the Schema Registry with Avro serialization into a Kafka cluster

This project consists of two modules ms-consumer and ms-producer which can be started on port 8082 and 8083. (Previous part [how to create kafka cluster using docker compose](https://github.com/orb48/springkafka) 

There are several schema registry implementations available for Kafka:
* Confluent Schema Registry
* Apicurio Registry
* AWS Glue Schema Registry
* Schema Registry by Lenses.io
* Schema Registry by Red Hat
  
In this application, we'll use the Confluent Schema Registry.

## Why Schema Registry and how it works
Kafka only transfers data in byte format. There is no data verification being performed at the Kafka cluster level. In fact, Kafka doesn't even know the type of data it is sending or receiving, whether it is a string or an integer. Schema Registry resides outside of Kafka cluster and handles the distribution of schemas to the producer and consumer by storing a copy of schema in the local cache.

### Producer
Before sending the data to Kafka, the application communicates with the schema registry to check if the schema is available. If the schema is not found, it is registered and cached. Then, the producer serializes the data using the schema and sends it to Kafka in binary format, prefixed with a unique schema ID.

### Consumer
Before processing a message, the consumer communicates with the schema registry using the schema ID obtained from the producer and deserializes the message using the corresponding schema. If there is a schema mismatch, an error occurs.

## Data serialization formats

In Kafka Schema Registry, the supported data serialization formats include Avro, JSON, and Protobuf. Avro is commonly used due to its compactness, self-descriptive nature, and schema evolution capabilities. 
**Keep in mind** that with JSON and Protobuf serialization, you will need to manage the schema versioning and compatibility manually, as there is no centralized registry like the Confluent Schema Registry to handle schema evolution and validation for you.

In this application, we'll use the Avro format.

## Testing

**Step 1.** `docker compose up -d`  
**Step 2.** Start modules ms-consumer, ms-producer  
**Step 3.** Send data to kafka using this method in producer controller  
`curl --location 'http://localhost:8083/api/v1/producer/send-message' \
--header 'Content-Type: application/json' \
--data '{
    "message": "hello world",
    "content": "content",
    "type": "NEW"
}'`  
**Step 4.** Find logs in consumer app  
`Received message: {"message": "hello world", "content": "new", "type": "NEW"}` 

**Step last.** Stop app docker compose down  

### Check schemas
* **GET /subjects**: returns a list of all registered subjects  
`curl --location 'http://0.0.0.0:8081/subjects'`  
response: `[ "topic-avro-message-value" ]`
* **GET /subjects/\<subject\>/versions**: returns a list of all registered versions for a given subject   
`curl --location 'http://0.0.0.0:8081/subjects/topic-avro-message-value/versions'`   
response: `[ 1 ]`   
* **GET /subjects/\<subject\>/versions/\<version\>**: returns the schema associated with a given version of a given subject  
`curl --location 'http://0.0.0.0:8081/subjects/topic-avro-message-value/versions/1'`  
response: `{
    "subject": "topic-avro-message-value",
    "version": 1,
    "id": 1,
    "schema": "{\"type\":\"record\",\"name\":\"KafkaMessageAvro\",\"namespace\":\"com.obovkun.springkafka.models.message\",\"fields\":[{\"name\":\"message\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"content\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"type\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}"
}`
