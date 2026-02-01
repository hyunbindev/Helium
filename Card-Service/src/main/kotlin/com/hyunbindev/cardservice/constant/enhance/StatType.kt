package com.hyunbindev.cardservice.constant.enhance

enum class StatType(val label:String, val baseValue: Double) {
    HEALTH("체력",100.0),
    ATTACK("공격력",10.0),
    DEFENSE("방어력",1.0),
    CRITICAL_RATE("치명타 확률", 0.01),
    CRITICAL_DAMAGE("치명타 피해",0.05)
}