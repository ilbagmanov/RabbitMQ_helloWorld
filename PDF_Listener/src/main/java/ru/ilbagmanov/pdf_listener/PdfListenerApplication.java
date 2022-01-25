package ru.ilbagmanov.pdf_listener;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class PdfListenerApplication {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("files_topic_exchange");
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.nonDurable().withArgument("x-dead-letter-exchange", "deadLetterExchange")
                .withArgument("x-dead-letter-routing-key", "deadLetter").build();
    }

    @Bean
    public Queue foodQueue() {
        return new Queue("food");
    }

    @Bean
    public Queue moneyQueue() {
        return new Queue("money");
    }

    @Bean
    public Queue dismissalQueue() {
        return new Queue("dismissal");
    }


    @Bean
    public Binding bindingFood() {
        return BindingBuilder.bind(foodQueue()).to(topicExchange()).with("files.pdf.food");
    }
    @Bean
    public Binding bindingMoney() {
        return BindingBuilder.bind(moneyQueue()).to(topicExchange()).with("files.pdf.money");
    }
    @Bean
    public Binding bindingDismissal() {
        return BindingBuilder.bind(dismissalQueue()).to(topicExchange()).with("files.pdf.dismissal");
    }

    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> containerFactory(ConnectionFactory rabbitConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory);
        factory.setPrefetchCount(10);
        factory.setConcurrentConsumers(5);
        return factory;
    }


    public static void main(String[] args) {
        SpringApplication.run(PdfListenerApplication.class, args);
    }

}
