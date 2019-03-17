package com.tafel.missions.tafelmissions.rules.impl

import com.tafel.missions.tafelmissions.dao.sql.MissionSql
import com.tafel.missions.tafelmissions.rules.FilterRule
import com.tafel.missions.tafelmissions.utils.model.Filter
import org.springframework.stereotype.Component

@Component
class LabelsFilterRule() : FilterRule {
    override fun isApplicable(filter: Filter): Boolean {
        return filter.labels?.isNotBlank() ?: false
    }

    override fun applyRule(filter: Filter, query: String, queryMapper: MutableMap<String, Any?>): String {
        queryMapper.put("labels", filter.labels?.split(",")?.toTypedArray())
        return MissionSql.SELECT_MISSIONS_LABELS_FILTER
    }
}