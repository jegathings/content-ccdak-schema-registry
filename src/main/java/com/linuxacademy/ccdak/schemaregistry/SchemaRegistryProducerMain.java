package com.linuxacademy.ccdak.schemaregistry;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class SchemaRegistryProducerMain {
    public static void main(String[] args) {
        final Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://kafka1:8081");
        KafkaProducer<String, Person> producer = new KafkaProducer<String, Person>(props);
        Person kenny = new Person(125745, "Kenny", "Armstrong", "kenny@linuxacademy.com");
        producer.send(new ProducerRecord<String, Person>("employees", kenny.getId().toString(), kenny));
        Person terry = new Person(943256, "Terry", "Cox", "terry@linuxacademy.com");
        producer.send(new ProducerRecord<String, Person>("employees", terry.getId().toString(), terry));
        producer.close();
    }
}