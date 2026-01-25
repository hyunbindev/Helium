package com.hyunbindev.cardservice.service.card

import com.hyunbindev.cardservice.dto.cardbase.CardBaseMetaDataDto
import com.hyunbindev.cardservice.repository.cardbase.CardBaseRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CreateBaseCardService(
    private val cardBaseRepository: CardBaseRepository
) {
    @Transactional
    public fun createBaseCard(cardBaseMetaDataDto: CardBaseMetaDataDto, image: MultipartFile){

    }

}