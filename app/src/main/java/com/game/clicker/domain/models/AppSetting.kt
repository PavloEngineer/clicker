package com.game.clicker.domain.models

interface AppSettings {

    fun setUserId(userId: Int?)

    fun getUserId(): Int?
}