package com.hyunbindev.userserevice.entity.wallet

import com.hyunbindev.userserevice.constant.exception.WalletExceptionConst
import com.hyunbindev.userserevice.exception.WalletException
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
class WalletEntity(
    @Id
    val memberId: UUID,
    ) {
    @Column(nullable = false)
    var balance:Long = 0L

    fun withdraw(amount:Long){
        if(amount < 1) throw WalletException(WalletExceptionConst.ILLEGAL_ARGUMENT,"출금 요청 금액은 0보다 커야 합니다.")

        if(this.balance < amount) throw WalletException(WalletExceptionConst.INSUFFICIENT_BALANCE)

        balance -= amount
    }
    fun deposit(amount:Long){
        if(amount < 1) throw WalletException(WalletExceptionConst.ILLEGAL_ARGUMENT,"입금 요청 금액은 0보다 커야 합니다.")
        balance += amount
    }
}