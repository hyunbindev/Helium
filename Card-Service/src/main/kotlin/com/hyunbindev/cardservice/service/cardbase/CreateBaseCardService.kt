package com.hyunbindev.cardservice.service.cardbase

import com.hyunbindev.cardservice.dto.cardbase.CardBaseMetaDataDto
import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.event.CardEvent
import com.hyunbindev.cardservice.repository.cardbase.CardBaseRepository
import com.hyunbindev.cardservice.service.S3Service
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class CreateBaseCardService(
    private val cardBaseRepository: CardBaseRepository,
    private val s3Service: S3Service,
    private val eventPublisher: ApplicationEventPublisher,
    private val bucketName:String = "cardimage"
) {
    @Transactional
    public fun createBaseCard(cardBaseMetaDataDto: CardBaseMetaDataDto, image: MultipartFile){
        //버킷 내 카드 dir
        val cardImageKey:String = "card_base_image/"+UUID.randomUUID()

        //이미지 업로드
        s3Service.uploadImage(image, bucketName,cardImageKey)

        var cardBaseEntity: CardBaseEntity = CardBaseEntity(
            name = cardBaseMetaDataDto.name,
            health = cardBaseMetaDataDto.health,
            attack = cardBaseMetaDataDto.attack,
            defense = cardBaseMetaDataDto.defense,
            criticalRate = cardBaseMetaDataDto.criticalRate,
            description = cardBaseMetaDataDto.description,
            criticalDamage = cardBaseMetaDataDto.criticalDamage,
            imageKey = cardImageKey,
        )

        //카드 메타정보
        cardBaseEntity = cardBaseRepository.save(cardBaseEntity)

        //이미지 후속처리를 위한 이벤트 발행
        eventPublisher.publishEvent(CardEvent.CardImageUploaded(cardImageKey))
    }
}