package com.hyunbindev.cardservice.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JacksonJsonSerializer

import kotlin.jvm.java

@Configuration
class KafkaConfig{
    @Value($$"${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Bean
    fun producerFactory(): ProducerFactory<String, Any> {
        val configProps = mutableMapOf<String, Any>()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers

        val jacksonJsonSerializer = JacksonJsonSerializer<Any>().apply {
            isAddTypeInfo = false
        }
        return DefaultKafkaProducerFactory(configProps, StringSerializer(),jacksonJsonSerializer)
    }

@Bean
fun kafkaTemplate(): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory())
    }
}