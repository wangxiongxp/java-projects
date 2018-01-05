package com.wx.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ExampleApplication.class, args);

		ConfigurableApplicationContext context =
				new SpringApplicationBuilder(ExampleApplication.class)
						.web(false)
						.run(args);

		MyGateway gateway = context.getBean(MyGateway.class);
		gateway.sendToMqtt("foo");

	}

	/**
	 * 配置client factory
	 * tcp://MQTT安装的服务器地址:MQTT定义的端口号
	 * @return
	 */
	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setServerURIs("tcp://127.0.0.1:8083");
//		factory.setUserName("username");
//		factory.setPassword("password");
		return factory;
	}

	/**
	 * 配置producer
	 * @return
	 */
	@Bean
	public IntegrationFlow mqttOutFlow() {
		//console input
//        return IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
//                e -> e.poller(Pollers.fixedDelay(1000)))
//                .transform(p -> p + " sent to MQTT")
//                .handle(mqttOutbound())
//                .get();
		return IntegrationFlows.from(outChannel())
				.handle(mqttOutbound())
				.get();
	}

	@Bean
	public MessageChannel outChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageHandler mqttOutbound() {
		MqttPahoMessageHandler messageHandler =
				new MqttPahoMessageHandler("siSamplePublisher", mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic("siSampleTopic");
		return messageHandler;
	}

	/**
	 * 配置MessagingGateway
	 */
	@MessagingGateway(defaultRequestChannel = "outChannel")
	public interface MyGateway {

		void sendToMqtt(String data);

	}

	/**
	 * 配置consumer
	 * @return
	 */
	@Bean
	public IntegrationFlow mqttInFlow() {
		return IntegrationFlows.from(mqttInbound())
				.transform(p -> p + ", received from MQTT")
				.handle(logger())
				.get();
	}

	private LoggingHandler logger() {
		LoggingHandler loggingHandler = new LoggingHandler("INFO");
		loggingHandler.setLoggerName("siSample");
		return loggingHandler;
	}

	@Bean
	public MessageProducerSupport mqttInbound() {
		MqttPahoMessageDrivenChannelAdapter adapter =
				new MqttPahoMessageDrivenChannelAdapter("siSampleConsumer",
				mqttClientFactory(), "siSampleTopic");
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(1);
		return adapter;
	}

}
