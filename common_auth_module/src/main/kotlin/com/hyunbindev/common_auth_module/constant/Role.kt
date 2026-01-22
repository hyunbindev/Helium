package com.hyunbindev.userserevice.constant.member

import org.springframework.security.core.GrantedAuthority

enum class Role(val value: String,val priority:Int):GrantedAuthority {
    GUEST("ROLE_GUEST",0),
    MEMBER("ROLE_MEMBER",1),
    ADMIN("ROLE_ADMIN",2);

    override fun getAuthority(): String? {
        return this.value
    }
}