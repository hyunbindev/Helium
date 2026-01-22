package com.hyunbindev.userserevice.repository.member

import com.hyunbindev.common_auth_module.constant.OAuth2Provider
import com.hyunbindev.userserevice.entity.member.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberRepository: JpaRepository<MemberEntity, UUID> {
    /**
     * provider 와 providerId로 사용자 조회
     */
    fun findByProviderAndProviderId(provider: OAuth2Provider, providerId: String): MemberEntity?

}