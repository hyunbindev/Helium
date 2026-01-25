package com.hyunbindev.cardservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import java.net.URI

@Configuration
class S3ClientConfig(
    @Value($$"${minio.endpoint}") private val endpoint: String,
    @Value($$"${minio.access-key}") private val accessKey: String,
    @Value($$"${minio.secret-key}") private val secretKey: String,
) {
    @Bean
    fun s3Client(): S3Client {
        return S3Client.builder()
                .endpointOverride(URI(endpoint))
                .credentialsProvider (
                    StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey))
                ).region(Region.AP_NORTHEAST_1)
                .forcePathStyle(true)
                .build()
    }
}