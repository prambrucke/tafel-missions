package com.tafel.missions.tafelmissions.rules.impl

import com.tafel.missions.tafelmissions.dao.sql.MissionSql
import com.tafel.missions.tafelmissions.rules.FilterRule
import com.tafel.missions.tafelmissions.utils.model.Filter
import org.springframework.stereotype.Component

@Component
class SprintFilterRule() : FilterRule {
    override fun isApplicable(filter: Filter): Boolean {
        return filter.sprint?.isNotBlank() ?: false
    }

    override fun applyRule(filter: Filter, query: String, queryMapper: MutableMap<String, Any?>): String {
        queryMapper["sprint"] = filter.sprint!!
        return MissionSql.SELECT_MISSIONS_SPRINT_FILTER
    }
}