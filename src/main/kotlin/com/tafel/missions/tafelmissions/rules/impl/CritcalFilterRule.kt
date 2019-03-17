package com.tafel.missions.tafelmissions.rules.impl

import com.tafel.missions.tafelmissions.dao.sql.MissionSql
import com.tafel.missions.tafelmissions.rules.FilterRule
import com.tafel.missions.tafelmissions.utils.model.Filter
import org.springframework.stereotype.Component

@Component
class CriticalFilterRule() : FilterRule {
    override fun isApplicable(filter: Filter): Boolean {
        return filter.critical != null
    }

    override fun applyRule(filter: Filter, query: String, queryMapper: MutableMap<String, Any?>): String {
        queryMapper.put("critical", filter.critical?.priority.toString())
        return MissionSql.SELECT_MISSIONS_CRITICAL_FILTER
    }
}