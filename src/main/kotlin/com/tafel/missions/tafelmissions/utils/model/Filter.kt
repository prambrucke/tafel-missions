package com.tafel.missions.tafelmissions.utils.model

import com.tafel.missions.tafelmissions.model.Critical

data class Filter(
        val labels:String?,
        val assignee:String?,
        val sprint:String?,
        val critical:Critical?
)