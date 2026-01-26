package com.hyunbindev.cardservice.service.cardbase

import com.hyunbindev.cardservice.repository.cardbase.CardBaseRepository
import org.springframework.stereotype.Service

@Service
class CardService(
    private val cardBaseRepository: CardBaseRepository
) {

}