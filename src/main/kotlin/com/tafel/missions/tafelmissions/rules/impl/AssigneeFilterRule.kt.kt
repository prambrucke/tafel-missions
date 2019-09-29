package com.tafel.missions.tafelmissions.rules.impl

import com.tafel.missions.tafelmissions.dao.sql.MissionSql
import com.tafel.missions.tafelmissions.rules.FilterRule
import com.tafel.missions.tafelmissions.utils.model.Filter
import org.springframework.stereotype.Component

@Component
class AssigneeFilterRule () : FilterRule {
    override fun isApplicable(filter: Filter): Boolean {
        return filter.assignee?.isNotBlank() ?: false
    }

    override fun applyRule(filter: Filter, query: String, queryMapper: MutableMap<String, Any?>): String {
        queryMapper["assignee"] = filter.assignee!!
        return MissionSql.SELECT_MISSIONS_ASSIGNEE_FILTER
    }
}