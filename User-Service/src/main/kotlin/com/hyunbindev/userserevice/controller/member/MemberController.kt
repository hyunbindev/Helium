package com.hyunbindev.userserevice.controller.member

import com.hyunbindev.common_auth_module.annotation.UserUuid
import com.hyunbindev.userserevice.controller.auth.AuthController
import com.hyunbindev.userserevice.dto.member.MemberInfoResponse
import com.hyunbindev.userserevice.service.member.MemberService
import org.slf4j.LoggerFactory

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.logging.Logger


@RestController
@RequestMapping("/api/user/v1/member")
class MemberController(
    private val memberService: MemberService
) {
    private val logger = LoggerFactory.getLogger(MemberController::class.java)
    @GetMapping("/me")
    fun getMemberInfoMe(@UserUuid userUuid: UUID): ResponseEntity<MemberInfoResponse> {
        return ResponseEntity.ok(memberService.getMember(userUuid))
    }

    @GetMapping("/{userId}")
    fun getMemberInfo(@PathVariable userId: String): ResponseEntity<MemberInfoResponse> {
        val userUuid: UUID = UUID.fromString(userId)
        return ResponseEntity.ok(memberService.getMember(userUuid))
    }

    @GetMapping("/members")
    fun getMembers(@RequestParam userIds: List<String>): ResponseEntity<List<MemberInfoResponse>> {
        return ResponseEntity.ok(memberService.getMembers(userIds))
    }
}