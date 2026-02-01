package com.hyunbindev.cardservice.service.cardbase

import com.hyunbindev.cardservice.constant.exception.CardExceptionConst
import com.hyunbindev.cardservice.dto.cardbase.CardBaseMetaDataDto
import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.CardGradeEntity
import com.hyunbindev.cardservice.exception.CardException
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
        if(cardBaseRepository.existsByName(cardBaseMetaDataDto.name)) throw CardException(
            CardExceptionConst.DUPLICATED_CARD_NAME,
            "이미 존재하는 카드 이름입니다."
        )

        //버킷 내 카드 dir
        val cardImageKey:String = "card_base_image/"+UUID.randomUUID()

        //이미지 업로드
        s3Service.uploadImage(image, bucketName,cardImageKey)

        //등급 뽑기
        val grade: CardGradeEntity = cardProbabilityService.getCardGrade()

        // 1. 가중치를 배열로 선언
        val weights = intArrayOf(
            secureRandom.nextInt(100) + 1, // Health
            secureRandom.nextInt(100) + 1, // Attack
            secureRandom.nextInt(100) + 1  // Defense
        )
        val totalWeight = weights.sum()

        val totalStatPoint:Int = 7

        val points = weights.map { (it * totalStatPoint) / totalWeight }.toMutableList()


        var remainder = totalStatPoint - points.sum()
        while (remainder > 0) {
            // 가장 비중이 높은 인덱스를 찾아서 1점 추가
            val maxIdx = weights.indices.maxByOrNull { weights[it] } ?: 0
            points[maxIdx]++
            remainder--
        }


        //카드 entity 생성
        var cardBaseEntity: CardBaseEntity = CardBaseEntity(
            name = cardBaseMetaDataDto.name,
            health = (points[0] + 1) * 100,
            attack = (points[1] + 1) * 10,
            defense = (points[2] + 1) * 1,
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