package com.hyunbindev.userserevice.service.auth

import com.hyunbindev.userserevice.constant.exception.AuthExceptionConst
import com.hyunbindev.userserevice.constant.exception.MemberExceptionConst
import com.hyunbindev.userserevice.entity.member.MemberEntity
import com.hyunbindev.userserevice.exception.AuthException
import com.hyunbindev.userserevice.exception.MemberException
import com.hyunbindev.userserevice.repository.member.MemberRepository
import com.hyunbindev.userserevice.security.jwt.JwtProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.lang.reflect.Member
import java.util.UUID
import java.util.concurrent.TimeUnit

@Service
class TokenService(
    private val stringRedisTemplate: StringRedisTemplate,
    private val jwtProvider: JwtProvider,
    private val memberRepository: MemberRepository,
    @Value($$"${jwt.access.expire}") private val refreshExpireIn:Long
) {
    /**
     * refresh token 저장
     */
    public fun saveRefreshToken(userId:String ,refreshToken: String) {
        stringRedisTemplate.opsForValue().set(
            "RT: $userId",
            refreshToken,
            refreshExpireIn,
            TimeUnit.SECONDS)
    }

    /**
     * access token 재발급
     */
    public fun reCreateAccessToken(userId:String , refreshToken: String):String{
        //UUID 객체로 변환
        val memberUuid: UUID = UUID.fromString(userId);

        //UUID를 통한 사용자 조회
        val member: MemberEntity = memberRepository.findById(memberUuid).orElseThrow{ AuthException(
            exceptionConstant = AuthExceptionConst.INVALID_AUTH_REQUEST,
            isDeleteRefreshToken = true
        ) }

        //refresh token 조회
        val savedRefreshToken = getRefreshToken(userId);

        //쿠키에 저장된 Refresh token과 서버에 저장된 refresh token 검증
        if(savedRefreshToken != refreshToken)
            throw AuthException(
            exceptionConstant = AuthExceptionConst.INVALID_AUTH_REQUEST,
            isDeleteRefreshToken = true)

        //토큰 발급
        return jwtProvider.createAccessToken(member);
    }

    /**
     * refresh token 조회
     */
    public fun getRefreshToken(userId:String):String{
        //refresh token 조회
        val refreshToken:String = stringRedisTemplate.opsForValue().get("RT: $userId")?:throw AuthException(AuthExceptionConst.REFRESH_TOKEN_EXPIRE)
        return refreshToken;
    }

    public fun deleteRefreshToken(userId:String): Boolean? = stringRedisTemplate.delete("RT: $userId")
}