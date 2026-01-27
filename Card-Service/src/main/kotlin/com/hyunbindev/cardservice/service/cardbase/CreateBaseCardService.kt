package com.hyunbindev.cardservice.service.cardbase

import com.hyunbindev.cardservice.dto.cardbase.CardBaseMetaDataDto
import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.CardBaseImageEntity
import com.hyunbindev.cardservice.event.CardEvent
import com.hyunbindev.cardservice.repository.cardbase.CardBaseImageRepository
import com.hyunbindev.cardservice.repository.cardbase.CardBaseRepository
import com.hyunbindev.cardservice.service.S3Service
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CreateBaseCardService(
    private val cardBaseRepository: CardBaseRepository,
    private val cardBaseImageRepository: CardBaseImageRepository,
    private val s3Service: S3Service,
    private val eventPublisher: ApplicationEventPublisher,
    private val bucketName:String = "cardimage"
) {
    @Transactional
    public fun createBaseCard(cardBaseMetaDataDto: CardBaseMetaDataDto, image: MultipartFile){
        var cardBaseEntity: CardBaseEntity = CardBaseEntity(
            name = cardBaseMetaDataDto.name,
            health = cardBaseMetaDataDto.health,
            attack = cardBaseMetaDataDto.attack,
            defense = cardBaseMetaDataDto.defense,
            criticalRate = cardBaseMetaDataDto.criticalRate,
            description = cardBaseMetaDataDto.description,
            criticalDamage = cardBaseMetaDataDto.criticalDamage
        )

        //카드 메타정보
        cardBaseEntity = cardBaseRepository.save(cardBaseEntity)

        //버킷 내 카드 dir
        val cardImageKey:String = "card_base_image/"+cardBaseEntity.id

        //카드 정보
        val cardBaseImageEntity: CardBaseImageEntity = CardBaseImageEntity(
            cardBase = cardBaseEntity,
            imageKey = cardImageKey,
        )

        cardBaseImageRepository.save(cardBaseImageEntity)

        //이미지 업로드
        s3Service.uploadImage(image, bucketName,cardImageKey)

        //webp 변환을 위한 메세지 발행
        eventPublisher.publishEvent(CardEvent.CardImageUploaded(cardImageKey))
    }
}