package com.hyunbindev.cardservice.repository.cardbase

import com.hyunbindev.cardservice.entity.CardBaseEntity
import com.hyunbindev.cardservice.entity.QCardBaseEntity
import com.querydsl.core.QueryFactory
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import java.util.Random

class CardBaseQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory
):CardBaseQueryRepository {
    override fun findRandomOne(): CardBaseEntity? {
        val card = QCardBaseEntity.cardBaseEntity

        val total:Long = queryFactory.select(card.count()).from(card).fetchOne() ?: 0L

        if(total == 0L) return null;

        val randomIndex = Random().nextInt(total.toInt())

        return queryFactory.selectFrom(card)
            .offset(randomIndex.toLong())
            .limit(1)
            .fetchOne()
    }
}