package com.hyunbindev.cardservice.event

sealed class CardEvent {
    data class CardImageUploaded(
        val cardImageKey: String,
    ):CardEvent()
}