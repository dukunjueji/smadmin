package com.smgoods.common.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * mq配置类
 * @author DK
 */
@Configuration
public class RabbitConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;
    /**
     * 交换机名称
     */
    public static final String MMC_TRAIN_SM_MSG = "mmc_train_sm_msg";
    /**
     * 队列名称
     */
    public static final String GROWTH_QUEUE = "growth_queue";
    public static final String INTEGRAL_QUEUE = "integral_queue";
    public static final String MESSAGE_QUEUE = "message_queue";
    public static final String SMS_QUEUE = "sms_queue";
    public static final String RECHARGE_QUEUE = "recharge_queue";
    /**
     * 路由关键字
     */
    public static final String ROUTINGKEY_GROWTH = "spring-boot-routingKey-growth";
    public static final String ROUTINGKEY_INTEGRAL = "spring-boot-routingKey-integral";
    public static final String ROUTINGKEY_MESSAGE = "spring-boot-routingKey-message";
    public static final String ROUTINGKEY_SMS = "spring-boot-routingKey-sms";
    public static final String ROUTINGKEY_RECHARGE = "spring-boot-routingKey-recharge";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(MMC_TRAIN_SM_MSG);
    }
    /**
     * 获取成长值队列
     * @return
     */
    @Bean
    public Queue growthQueue() {
        return new Queue(GROWTH_QUEUE, true);
    }
    /**
     * 获取积分队列
     * @return
     */
    @Bean
    public Queue integralQueue() {
        return new Queue(INTEGRAL_QUEUE, true);
    }
    /**
     * 获取消息队列
     * @return
     */
    @Bean
    public Queue messageQueue() {
        return new Queue(MESSAGE_QUEUE, true);
    }
    /**
     * 获取短息队列
     * @return
     */
    @Bean
    public Queue smsQueue() {
        return new Queue(SMS_QUEUE, true);
    }
    /**
     * 获取队列
     * @return
     */
    @Bean
    public Queue rechargeQueue() {
        return new Queue(RECHARGE_QUEUE, true);
    }

    /**
     * 绑定交换机与队列
     * @return
     */
    @Bean
    public Binding bindingGrowthQueue() {
        return BindingBuilder.bind(growthQueue()).to(topicExchange()).with(RabbitConfig.ROUTINGKEY_GROWTH);
    }
    @Bean
    public Binding bindingIntegralQueue() {
        return BindingBuilder.bind(integralQueue()).to(topicExchange()).with(RabbitConfig.ROUTINGKEY_INTEGRAL);
    }
    @Bean
    public Binding bindingMessageQueue() {
        return BindingBuilder.bind(messageQueue()).to(topicExchange()).with(RabbitConfig.ROUTINGKEY_MESSAGE);
    }
    @Bean
    public Binding bindingSmsQueue() {
        return BindingBuilder.bind(smsQueue()).to(topicExchange()).with(RabbitConfig.ROUTINGKEY_SMS);
    }
    @Bean
    public Binding bindingRechargeQueue() {
        return BindingBuilder.bind(rechargeQueue()).to(topicExchange()).with(RabbitConfig.ROUTINGKEY_RECHARGE);
    }

}

