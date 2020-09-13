package com.example.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.RecordAccumulator;

import java.util.Properties;

/**
 * Desription:
 *
 * @ClassName MyProducer
 * @Author Zhanyuwei
 * @Date 2020/9/13 3:48 下午
 * @Version 1.0
 **/
public class MyProducer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        //kafka 集群，broker-list
        properties.put("bootstrap.servers", "master:9092");

        // ACK应答级别
        properties.put("acks", "all");

        //重试次数
        properties.put("retries", 1);

        //批次大小
        properties.put("batch.size", 16384);

        //等待时间
        properties.put("linger.ms", 1);

        //RecordAccumulator 缓冲区大小
        properties.put("buffer.memory", 33554432);

        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i = 0;i<10;i++){
            producer.send(new ProducerRecord<String, String>("first","test","kafka-demo---"+i));
        }

        producer.close();
    }
}
