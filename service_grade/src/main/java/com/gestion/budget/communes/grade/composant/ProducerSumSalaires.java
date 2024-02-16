package com.gestion.budget.communes.grade.composant;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

@Component
public class ProducerSumSalaires {
	
    static String TOPIC = "SumSalaires-event";
    
    private static Producer<String, String> AddProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    public static void produceMessage(String cle, String msg) throws Exception {
        final Producer<String, String> producer = AddProducer();
        try {
                final ProducerRecord<String, String> record= new ProducerRecord<>(TOPIC, cle, msg);
                RecordMetadata metadata = producer.send(record).get();
        } finally {
        	
        producer.flush();
        producer.close();
        }
    }


}
