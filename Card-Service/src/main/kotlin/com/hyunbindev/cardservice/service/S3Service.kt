package com.hyunbindev.cardservice.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.ObjectCannedACL
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Service
class S3Service(
    private val s3Client: S3Client
) {
     fun uploadImage(file: MultipartFile, bucket:String, key:String){
        val request: PutObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .acl(ObjectCannedACL.PUBLIC_READ)
            .build()
        s3Client.putObject(request, RequestBody.fromInputStream(file.inputStream, file.size))
    }
}