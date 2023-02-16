package com.genuinenoange;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class GrowthRocketMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrowthRocketMqApplication.class, args);

        try {
            // 创建生产者
            DefaultMQProducer producer = new DefaultMQProducer("TEST_PRODUCER");
            producer.setNamesrvAddr("10.68.2.35:9876");
            producer.start();
//            Message msg = new Message("TEST_JAVA", "TAGA", "这是消息内容".getBytes(StandardCharsets.UTF_8));
//            msg.setDelayTimeLevel(3);
//            SendResult result = producer.send(msg);
//            System.out.println(result);
//            producer.shutdown();

            // 创建消费者1
            DefaultMQPushConsumer consumer1 = new DefaultMQPushConsumer("TEST_CONSUMER2");
            consumer1.setNamesrvAddr("10.68.2.35:9876");
//            consumer1.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer1.subscribe("TEST_JAVA", "*");
            consumer1.setMessageModel(MessageModel.CLUSTERING);
            consumer1.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                System.out.println(Thread.currentThread().getName() + " Receive1 Message: " + list.get(0).getMsgId());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            System.out.println("consumer1 start...");
            consumer1.start();

            // 创建消费者2
            DefaultMQPushConsumer consumer2 = new DefaultMQPushConsumer("TEST_CONSUMER2");
            consumer2.setNamesrvAddr("10.68.2.35:9876");
//            consumer2.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer2.subscribe("TEST_JAVA", "*");
            consumer2.setMessageModel(MessageModel.CLUSTERING);
            consumer2.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                System.out.println(Thread.currentThread().getName() + " Receive2 Message: " + list.get(0).getMsgId());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            System.out.println("consumer2 start...");
            consumer2.start();

            // 创建消费者3
            DefaultMQPushConsumer consumer3 = new DefaultMQPushConsumer("TEST_CONSUMER2");
            consumer3.setNamesrvAddr("10.68.2.35:9876");
//            consumer3.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer3.subscribe("TEST_JAVA", "*");
            consumer3.setMessageModel(MessageModel.CLUSTERING);
            consumer3.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                System.out.println(Thread.currentThread().getName() + " Receive3 Message: " + list.get(0).getMsgId());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            System.out.println("consumer3 start...");
            consumer3.start();

            // 创建消费者4
            DefaultMQPushConsumer consumer4 = new DefaultMQPushConsumer("TEST_CONSUMER2");
            consumer4.setNamesrvAddr("10.68.2.35:9876");
//            consumer4.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer4.subscribe("TEST_JAVA", "*");
            consumer4.setMessageModel(MessageModel.CLUSTERING);
            consumer4.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                System.out.println(Thread.currentThread().getName() + " Receive4 Message: " + list.get(0).getMsgId());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            System.out.println("consumer4 start...");
            consumer4.start();

            Thread.sleep(5000);


            // 发送消息
            Message msg = new Message("TEST_JAVA", "TAGA", "这是消息内容1".getBytes(StandardCharsets.UTF_8));
                        SendResult result = producer.send(msg);
            System.out.println(result);

            msg = new Message("TEST_JAVA", "TAGA", "这是消息内容2".getBytes(StandardCharsets.UTF_8));
                        result = producer.send(msg);
            System.out.println(result);

            msg = new Message("TEST_JAVA", "TAGA", "这是消息内容3".getBytes(StandardCharsets.UTF_8));
                        result = producer.send(msg);
            System.out.println(result);

            msg = new Message("TEST_JAVA", "TAGA", "这是消息内容4".getBytes(StandardCharsets.UTF_8));
                        result = producer.send(msg);
            System.out.println(result);

            msg = new Message("TEST_JAVA", "TAGA", "这是消息内容5".getBytes(StandardCharsets.UTF_8));
                        result = producer.send(msg);
            System.out.println(result);

            msg = new Message("TEST_JAVA", "TAGA", "这是消息内容6".getBytes(StandardCharsets.UTF_8));
                        result = producer.send(msg);
            System.out.println(result);

            msg = new Message("TEST_JAVA", "TAGA", "这是消息内容7".getBytes(StandardCharsets.UTF_8));
                        result = producer.send(msg);
            System.out.println(result);

            msg = new Message("TEST_JAVA", "TAGA", "这是消息内容8".getBytes(StandardCharsets.UTF_8));
                        result = producer.send(msg);
            System.out.println(result);

        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
