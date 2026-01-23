package com.hyunbindev.cardservice.repository

import org.springframework.data.jpa.repository.JpaRepository

interface PlayerCardRepository : JpaRepository<PlayerCardRepository, Long> {
}