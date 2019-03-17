package com.tafel.missions.tafelmissions.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Mission(
        val name: String,
        val description: String = "Work In Progress",
        val labels: List<String>?,
        val estimation: String?,
        val assigneeIds: List<String>?,
        val createdBy: String,
        val updatedBy:String,
        @get:JsonProperty("is_explored")
        val isExplored: Boolean = false,
        val status: String?,
        val sprint: String?,
        val teamId: String?,
        val critical: Critical = Critical.NORMAL,
        val comments: List<String>?,
        val createdAt: String = OffsetDateTime.now().toString(),
        val updatedAt: String = OffsetDateTime.now().toString()
)


enum class Critical(val priority: Int) {
    HOT_FIX(1),
    CRITICAL(2),
    NORMAL(3)
}

object EnumLookUp{
    private val priorityMap = Critical.values().associateBy(Critical::priority)

    fun getEnumType(priority: Int) = priorityMap[priority]
}