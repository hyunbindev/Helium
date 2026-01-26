package com.hyunbindev.cardservice.repository.cardbase

import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.QCardBaseEntity
import com.querydsl.core.QueryFactory
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory

class CardBaseQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory
):CardBaseQueryRepository {
    override fun findRandomOne(): CardBaseEntity? {
        val card = QCardBaseEntity.cardBaseEntity
        return queryFactory.selectFrom(card)
            .orderBy(Expressions.numberTemplate(Double::class.java, "function('RAND')").asc())
            .fetchFirst()
    }
}