package com.tafel.missions.tafelmissions.rules

import com.tafel.missions.tafelmissions.utils.model.Filter

interface FilterRule{
    fun isApplicable(filter: Filter):Boolean
    fun applyRule(filter: Filter,query: String, queryMapper: MutableMap<String, Any?>):String
}