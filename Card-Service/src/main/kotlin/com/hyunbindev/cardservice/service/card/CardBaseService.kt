package com.hyunbindev.cardservice.service.card

import com.hyunbindev.cardservice.dto.cardbase.CreateCardBaseDto
import com.hyunbindev.cardservice.repository.CardBaseRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CardBaseService (
    private val CardBaseRepository : CardBaseRepository
){
    @Transactional
    fun createBaseCard(createCardBaseDto: CreateCardBaseDto){

    }
    @Transactional
    fun updateBaseCard(){

    }
    @Transactional
    fun deleteBaseCard(){

    }
}