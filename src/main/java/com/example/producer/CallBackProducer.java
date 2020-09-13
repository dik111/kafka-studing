package com.example.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Desription:
 *
 * @ClassName CallBackProducer
 * @Author Zhanyuwei
 * @Date 2020/9/13 6:04 下午
 * @Version 1.0
 **/
public class CallBackProducer {

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

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (int i = 0;i<10;i++){
            producer.send(new ProducerRecord<String, String>("aaa", "test","kafka-demo2---" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null){
                        System.out.println(recordMetadata.partition()+"----"+recordMetadata.offset());

                    }else {
                        e.printStackTrace();
                    }
                }
            });
        }
        producer.close();
    }
}
