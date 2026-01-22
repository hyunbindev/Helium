package com.hyunbindev.apiserver.repository.member

import com.hyunbindev.apiserver.constant.oauth.OAuth2Provider
import com.hyunbindev.apiserver.entity.member.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberRepository: JpaRepository<MemberEntity, UUID> {
    /**
     * provider 와 providerId로 사용자 조회
     */
    fun findByProviderAndProviderId(provider: OAuth2Provider, providerId: String): MemberEntity?
}