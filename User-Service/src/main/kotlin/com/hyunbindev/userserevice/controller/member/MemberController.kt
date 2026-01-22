package com.hyunbindev.userserevice.controller.member

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
import java.util.logging.Logger


@RestController
@RequestMapping("/api/user/v1/member")
class MemberController(
    private val memberService: MemberService
) {
    private val logger = LoggerFactory.getLogger(MemberController::class.java)
    @GetMapping("/me")
    fun getMemberInfoMe(auth: Authentication): ResponseEntity<MemberInfoResponse> {
        logger.debug("test : {}", auth.name)
        return ResponseEntity.ok(memberService.getMember(auth.name))
    }

    @GetMapping("/{userId}")
    fun getMemberInfo(@PathVariable userId: String): ResponseEntity<MemberInfoResponse> {
        return ResponseEntity.ok(memberService.getMember(userId))
    }

    @GetMapping("/members")
    fun getMembers(@RequestParam userIds: List<String>): ResponseEntity<List<MemberInfoResponse>> {
        return ResponseEntity.ok(memberService.getMembers(userIds))
    }
}