package com.example.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * Desription:
 *
 * @ClassName MyPartitioner
 * @Author Zhanyuwei
 * @Date 2020/9/13 7:49 下午
 * @Version 1.0
 **/
public class MyPartitioner implements Partitioner {


    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
