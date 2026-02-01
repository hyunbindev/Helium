package com.hyunbindev.cardservice.service.cardbase

import com.hyunbindev.cardservice.dto.cardbase.CardBaseMetaDataDto
import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.CardGradeEntity
import com.hyunbindev.cardservice.repository.cardbase.CardBaseRepository
import com.hyunbindev.cardservice.service.S3Service
import com.hyunbindev.cardservice.service.playercard.CardProbabilityService
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.security.SecureRandom
import java.util.UUID

@Service
class CreateBaseCardService(
    private val cardBaseRepository: CardBaseRepository,
    private val s3Service: S3Service,
    private val eventPublisher: ApplicationEventPublisher,
    private val cardProbabilityService: CardProbabilityService,
    private val bucketName:String = "cardimage",

    private val secureRandom: SecureRandom = SecureRandom()
) {
    @Transactional
    public fun createBaseCard(cardBaseMetaDataDto: CardBaseMetaDataDto, image: MultipartFile){
        //버킷 내 카드 dir
        val cardImageKey:String = "card_base_image/"+UUID.randomUUID()

        //이미지 업로드
        s3Service.uploadImage(image, bucketName,cardImageKey)

        //등급 뽑기
        val grade: CardGradeEntity = cardProbabilityService.getCardGrade()


        val totalStatPoint:Int = 10
        val healthWeight:Int = secureRandom.nextInt(100)+1
        val attackWeight:Int = secureRandom.nextInt(100)+1
        val defenseWeight:Int = secureRandom.nextInt(100)+1

        var totalWeight:Int = 0

        totalWeight+=healthWeight
        totalWeight+=attackWeight
        totalWeight+=defenseWeight

        //카드 entity 생성
        var cardBaseEntity: CardBaseEntity = CardBaseEntity(
            name = cardBaseMetaDataDto.name,
            health = (healthWeight*totalStatPoint)/totalWeight*100,
            attack = (attackWeight*totalStatPoint)/totalWeight*10,
            defense = (defenseWeight*totalStatPoint)/totalWeight,
            criticalRate = 0.0,
            criticalDamage = 1.0,
            description = cardBaseMetaDataDto.description,
            imageKey = cardImageKey,
            grade = grade,
        )

        //카드 메타정보
        cardBaseEntity = cardBaseRepository.save(cardBaseEntity)

        //이미지 후속처리를 위한 이벤트 발행
        //eventPublisher.publishEvent(CardEvent.CardImageUploaded(cardImageKey))
    }
}