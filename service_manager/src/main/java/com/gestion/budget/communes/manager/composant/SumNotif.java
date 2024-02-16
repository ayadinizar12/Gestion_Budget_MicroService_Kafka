package com.gestion.budget.communes.manager.composant;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

@Component
public class SumNotif {

	static String TOPIC="Sum-Salaires-notification-event";
	
	private static Producer<String, String> AddProducer(){
		Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return new KafkaProducer<>(props);
	}
	
	 public static void produceMessage(String cle, String msg)  {
	        final Producer<String, String> producer = AddProducer();
	        try 
	        {
	        final ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, cle, msg);
	              RecordMetadata metadata = producer.send(record).get();
	        } catch (ExecutionException e){
	        	
	            throw new RuntimeException(e);
	        } catch (InterruptedException e) {
	        	
	            throw new RuntimeException(e);
	        } finally {
	        	
	        producer.flush();
	        producer.close();
	        }
	}
	
}
