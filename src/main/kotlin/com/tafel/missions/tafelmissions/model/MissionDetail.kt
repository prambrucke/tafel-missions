package com.tafel.missions.tafelmissions.model


data class MissionsList(
        val mission: List<MissionDetail>
)

data class MissionDetail(
        val id: String,
        val mission: Mission
)