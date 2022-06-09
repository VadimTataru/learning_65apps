package com.example.learning65apps.domain.models

data class ContactModel(
    val id: Int,
    val contactName: String,
    val firstPhone: String,
    val secondPhone: String,
    val firstEmail: String,
    val secondEmail: String,
    val contactDescription: String,
    val contactPhoto: Int
)