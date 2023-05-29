package com.the12smb.submissionjetpackcompose.model



data class Content(
    val id: Long,
    val name: String,
    val type: String,
    val complexity: String,
    val role: String,
    val lore: String,
    val photo: Int
)