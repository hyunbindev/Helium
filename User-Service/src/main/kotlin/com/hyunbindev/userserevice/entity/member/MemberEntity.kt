package com.hyunbindev.apiserver.entity.member

import com.hyunbindev.apiserver.constant.oauth.OAuth2Provider
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.util.UUID

/**
 * Member Entity
 * @author hyunbindev
 * @since 2026-01-15
 * initial member entity
 */
@Entity
class MemberEntity(
    @Id
    @Column(updatable=false, nullable = false, columnDefinition = "BINARY(16)")
    val id : UUID = UUID.randomUUID(),

    @Enumerated(EnumType.STRING)
    @Column(updatable=false,nullable = false)
    val provider: OAuth2Provider,

    @Column(updatable=false, nullable = false)
    val providerId:String,

    @Column(nullable = false)
    val nickname : String,

    @Column(nullable = true)
    val profileImageUrl:String?,

    @Column(nullable = false)
    val money : Long=0L,
) {
}