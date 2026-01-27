package com.hyunbindev.cardservice.repository.playercard

import com.hyunbindev.cardservice.entity.PlayerCardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface PlayerCardRepository : JpaRepository<PlayerCardEntity, Long> {
    //n+1 문제 해결 fetch join
    @Query("select p from PlayerCardEntity p join fetch p.baseCard where p.owner = :userUuid order by p.level desc, p.id desc")
    fun findAllByOwner(userUuid: UUID): List<PlayerCardEntity>
    @Query("select p from PlayerCardEntity p join fetch p.baseCard where p.id = :id")
    fun findByIdFetchJoin(id:Long): Optional<PlayerCardEntity>
}