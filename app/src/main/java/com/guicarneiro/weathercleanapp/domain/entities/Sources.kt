package com.guicarneiro.weathercleanapp.domain.entities

data class Sources(
    val title: String,
    val slug: String,
    val url: String,
    val crawlRate: Int,
)