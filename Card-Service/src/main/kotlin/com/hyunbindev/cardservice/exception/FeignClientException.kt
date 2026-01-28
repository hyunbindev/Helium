package com.hyunbindev.cardservice.exception

import org.springframework.http.HttpStatus

class FeignClientException(val status: HttpStatus, val code:String, message:String, val timestamp:String):
    RuntimeException(message) {
}