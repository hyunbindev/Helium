package com.hyunbindev.cardservice.entity

import com.hyunbindev.cardservice.constant.enhance.StatType
import com.hyunbindev.cardservice.dto.card.PlayerCardDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.apache.kafka.clients.admin.FeatureUpdate

@Entity
class CardUpGradeSelectionEntity(
    @ManyToOne
    val playerCard:PlayerCardEntity,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status:UpgradeStatus,

    @Enumerated(EnumType.STRING)
    var statType: StatType,

) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? = null

}
enum class UpgradeStatus{
    PENDING,
    APPROVED,
}