package com.hyunbindev.userserevice.dto.member

import java.util.UUID

data class MemberInfoResponse(
    val id: UUID,
    val nickName: String,
    val profileImageUrl: String?,
    val money:Long
) {
}