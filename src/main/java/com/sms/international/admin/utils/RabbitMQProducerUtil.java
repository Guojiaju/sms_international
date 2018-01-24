package com.sms.international.admin.utils;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpIllegalStateException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class RabbitMQProducerUtil {
    public static final Logger logger = LoggerFactory.getLogger(RabbitMQProducerUtil.class);
    private ConnectionFactory factory = null;
    public Connection connection = null;
    public Channel channel = null;
    private DeclareOk declareOk;
    private volatile MessageConverter messageConverter = new SimpleMessageConverter();

    public RabbitMQProducerUtil() {
        try {
            factory = new ConnectionFactory();
            factory.setHost(ConstantSys.RABBIT_HOST);
            factory.setPort(ConstantSys.RABBIT_PORT);
            factory.setUsername(ConstantSys.RABBIT_USERNAME);
            factory.setPassword(ConstantSys.RABBIT_PASSWORD);
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(e.getMessage(),e);
        } catch (TimeoutException e) {
            e.printStackTrace();
            logger.info(e.getMessage(),e);
        }
    }

    /**
     * 发送
     * @param queueName	队列名称
     * @param obj	发送对象
     * @throws IOException
     */
    public void send(String queueName, Object obj)
            throws IOException {
        long l = System.currentTimeMillis();
        channel.basicPublish("", queueName, null, serialize(obj));
        logger.info("send success. time-consuming:" + (System.currentTimeMillis() - l));
    }

    /**
     * 发送
     * @param queueName	队列名称
     * @param obj	发送对象
     * @param priority	优先级,最大10,最小为0,数值越大优先级越高
     * @throws IOException
     */
    public void sendPriority(String queueName, Object obj, int priority)throws IOException {
        long l = System.currentTimeMillis();
        BasicProperties.Builder properties = new BasicProperties.Builder();
        properties.priority(priority);
        channel.basicPublish("", queueName, properties.build(), serialize(obj));
        logger.info("send success. time-consuming:" + (System.currentTimeMillis() - l));
    }

    /**
     * 单个获取
     * @param queueName	队列名称
     * @return
     */
    public Object getObj(String queueName) {
        try {
            // 持久化//SUBMIT_CMPP_PRIORITY
            Map<String, Object> args = new HashMap<String, Object>();
            args.put("x-max-priority", 10);
            channel.queueDeclare(queueName, true, false, false, args);
            // 流量控制
            channel.basicQos(1);
            return receive(channel, queueName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 批量获取
     * @param queueName	队列名称
     * @param limit	交换器名称
     * @return
     */
    public List<Object> getObjList(String queueName, int limit){
        List<Object> list = new ArrayList<Object>();
        try {
            // 持久化//SUBMIT_CMPP_PRIORITY
            Map<String, Object> args = new HashMap<String, Object>();
            args.put("x-max-priority", 10);
            channel.queueDeclare(queueName, true, false, false, args);
            // 流量控制
            channel.basicQos(1);
            // 声明消费者
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, false, consumer);
            for (int i = 0; i < limit; i++) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                Object obj = deSerialize(delivery.getBody());
                if (obj != null) {
                    list.add(obj);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // 反馈给服务器表示收到信息
                }
            }
            return list;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 监听Test
     * @param queueName
     * @param exchangeName
     */
    public void listenerTest(String queueName, String exchangeName){
        try {
            // 声明direct类型转发器//SUBMIT_EXCHANGE
            channel.exchangeDeclare(exchangeName, "direct", true);
            // 持久化//SUBMIT_CMPP_PRIORITY
            channel.queueDeclare(queueName, false, false, false, null);
            // 流量控制
            channel.basicQos(1);
            // 将消息队列绑定到Exchange
            channel.queueBind(queueName, exchangeName,queueName);
            // 声明消费者
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, false, consumer);
            while (true) {
                // 等待队列推送消息
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                Object obj = deSerialize(delivery.getBody());
                if (obj != null) {
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // 反馈给服务器表示收到信息
                    /**
                     * 在这里把obj放入缓存队列即可
                     */
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage(),e);
        }
    }

    /**
     * 获取队列长度
     *
     * @param queueName
     *            队列名称
     * @return
     */
    public int getQueueListSize(String queueName) {
        int count = 0;
        try {
            declareOk = channel.queueDeclare(queueName, false, false, false,null);
            count = declareOk.getMessageCount();
        } catch (IOException e) {
            logger.info("[RabbitMQ] Get Queue Size failed..." + e.getMessage(),e);
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 获取队列长度
     *
     * @param queueName
     *            队列名称
     * @return
     */
    public int getChannelQueueListSize(String queueName) {
        int count = 0;
        try {
            Map<String, Object> args = new HashMap<String, Object>();
            args.put("x-max-priority", 10);
            declareOk = channel.queueDeclare(queueName, true, false, false,args);
            count = declareOk.getMessageCount();
        } catch (IOException e) {
            logger.info("[RabbitMQ] Get Queue Size failed..." + e.getMessage(), e);
            e.printStackTrace();
        }
        return count;
    }

    private Object receive(Channel channel, String QUEUE_NAME) throws Exception {
        // 声明消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumer);
        while (true) {
            // 等待队列推送消息
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            Object obj = deSerialize(delivery.getBody());
            if (obj != null) {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // 反馈给服务器表示收到信息
            }
            return obj;
        }
    }

    public void close() {
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            logger.info("[RabbitMQ] channel/connection close failed...",e);
        } catch (TimeoutException e) {
            logger.info("[RabbitMQ] channel/connection close failed...",e);
        }
    }

    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = null;
        HessianOutput output = null;
        try {
            baos = new ByteArrayOutputStream(1024);
            output = new HessianOutput(baos);
            output.startCall();
            output.writeObject(obj);
            output.completeCall();
        } catch (final IOException ex) {
            throw ex;
        } finally {
            if (output != null) {
                try {
                    baos.close();
                } catch (final IOException ex) {
                    logger.info("[RabbitMQ] serializee Exception...",ex);
                }
            }
        }
        return baos != null ? baos.toByteArray() : null;
    }

    public Object deSerialize(byte[] in) throws IOException {
        Object obj = null;
        ByteArrayInputStream bais = null;
        HessianInput input = null;
        try {
            bais = new ByteArrayInputStream(in);
            input = new HessianInput(bais);
            input.startReply();
            obj = input.readObject();
            input.completeReply();
        } catch (final IOException ex) {
            throw ex;
        } catch (final Throwable e) {
            logger.info("[RabbitMQ] Failed to decode object...",e);
        } finally {
            if (input != null) {
                try {
                    bais.close();
                } catch (final IOException ex) {
                    logger.info("[RabbitMQ] Failed to close stream...",ex);
                }
            }
        }
        return obj;
    }

    protected Message convertMessageIfNecessary(final Object object) {
        if (object instanceof Message) {
            return (Message) object;
        }
        return getRequiredMessageConverter().toMessage(object,
                new MessageProperties());
    }

    private MessageConverter getRequiredMessageConverter()
            throws IllegalStateException {
        MessageConverter converter = this.getMessageConverter();
        if (converter == null) {
            throw new AmqpIllegalStateException(
                    "No 'messageConverter' specified. Check configuration of RabbitTemplate.");
        }
        return converter;
    }

    public MessageConverter getMessageConverter() {
        return messageConverter;
    }

}
