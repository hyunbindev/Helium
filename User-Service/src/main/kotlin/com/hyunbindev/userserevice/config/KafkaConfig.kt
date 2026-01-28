package com.hyunbindev.userserevice.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.converter.JacksonJsonMessageConverter
import org.springframework.kafka.support.converter.JsonMessageConverter
import org.springframework.kafka.support.converter.RecordMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer

import kotlin.jvm.java

@Configuration
@EnableKafka
class KafkaConfig{
    @Value($$"${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Bean
    fun producerFactory(): ProducerFactory<String, Any> {
        val configProps = mutableMapOf<String, Any>()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers

        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java

        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "org.springframework.kafka.support.serializer.JsonSerializer"

        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory())
    }
    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        val configProps = mutableMapOf<String, Any>()
        configProps[BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers

        configProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java

        return DefaultKafkaConsumerFactory<String, Any>(configProps)
    }
    @Bean
    fun messageConverter(): RecordMessageConverter {
        return JacksonJsonMessageConverter()
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.setConsumerFactory(consumerFactory())
        factory.setRecordMessageConverter(messageConverter())

        return factory
    }
}