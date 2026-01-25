package com.hyunbindev.cardservice.controller.admin

import com.hyunbindev.cardservice.dto.cardbase.CardBaseMetaDataDto
import com.hyunbindev.common_auth_module.annotation.UserUuid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RestController
@RequestMapping("/api/user/v1/admin/card")
class CardAdminController
{
    @PostMapping("/card/base")
    fun createCardBase(
        @UserUuid userUuid: UUID,
        @RequestPart("metadata") metaDataDto: CardBaseMetaDataDto,
        @RequestPart("image")image: MultipartFile
    ): ResponseEntity<Void> {

    }

    @DeleteMapping("/card/base/{cardId}")
    fun deleteCard(@UserUuid userUuid: UUID, @PathVariable cardId:Long): ResponseEntity<Void> {

    }

    @PutMapping("/card/base/{cardId}}")
    fun updateCard(@UserUuid userUuid:UUID, @PathVariable cardId:Long):ResponseEntity<Void>{

    }
}