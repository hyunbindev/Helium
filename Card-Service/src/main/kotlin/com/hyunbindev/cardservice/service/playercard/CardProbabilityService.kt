package com.hyunbindev.cardservice.service.playercard

import com.hyunbindev.cardservice.entity.CardGradeEntity
import com.hyunbindev.cardservice.repository.cardbase.CardGradeRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
class CardProbabilityService(
    private val cardGradeRepository: CardGradeRepository
) {
    private lateinit var grades: List<CardGradeEntity>
    private  var probablites: MutableList<CardGradeProbability> = mutableListOf()
    private var totalWeight:Int = 0
    private val secureRandom = SecureRandom()

    @PostConstruct
    fun init(){
        grades = cardGradeRepository.findAllByOrderByWeightAsc()
        grades.forEach{
            totalWeight += it.weight
            probablites.add(CardGradeProbability(totalWeight,it))
        }
        probablites.reverse()
    }

    fun getCardGrade():CardGradeEntity{
        val randNum = secureRandom.nextInt(totalWeight)+1
        for(p in probablites){
            if(randNum <= p.gradeWeightSum) return p.grade
        }

        throw IllegalStateException("Failed to draw card grade")
    }
}
private class CardGradeProbability(
    val gradeWeightSum:Int,
    val grade: CardGradeEntity,
)