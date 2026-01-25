package com.hyunbindev.cardservice.entity

import com.hyunbindev.cardservice.constant.ImageStatus
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne

@Entity
class CardBaseImageEntity(
    @Id
    val id:Long? = null,
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    val cardBase: CardBaseEntity,

    val key:String,

    val status: ImageStatus = ImageStatus.PENDING
) {
}