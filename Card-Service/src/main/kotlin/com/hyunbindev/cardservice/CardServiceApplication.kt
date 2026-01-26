package com.hyunbindev.cardservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class CardServiceApplication

fun main(args: Array<String>) {
    runApplication<CardServiceApplication>(*args)
}
