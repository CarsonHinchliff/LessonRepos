package org.example;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author Carson
 * @created 2025/3/12 星期三 下午 03:05
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TestDisruptor();
    }

    static void TestDisruptor() {
        int bufferSize = 1024;
        Disruptor<MessageEvent> disruptor = new Disruptor<>(
                new MessageEventFactory(),
                bufferSize,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
        );
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.start();
        RingBuffer<MessageEvent> ringBuffer = disruptor.getRingBuffer();
        IntStream.range(0, 100).forEach(i -> {
            ringBuffer.publishEvent((event, sequence, buffer) -> {
                event.setMessage("Hello World!" + " Index: " + i);
            });
        });
    }
}