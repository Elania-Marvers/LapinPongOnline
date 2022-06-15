package com.game.lapinpongonline

data class MatchBean(
    var id: String? = null,
    var nameTeam1: String? = null,
    var nameTeam2: String? = null,
    var idUserCreator: String? = null,
    var scoreTeam1: Int = 0,
    var scoreTeam2: Int = 0,
    var time: Long = 0,
    var timeRemaining: Int = 90,
    var isPlay: Boolean = false,
    var player_one: String? = null,
    var player_two: String? = null,
    var bunny: Boolean = true
)