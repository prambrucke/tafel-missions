package com.tafel.missions.tafelmissions.dao

import com.tafel.missions.tafelmissions.dao.sql.MissionSql
import com.tafel.missions.tafelmissions.model.Mission
import com.tafel.missions.tafelmissions.model.MissionDetail
import com.tafel.missions.tafelmissions.rowmapper.MissionRowMapper
import com.tafel.missions.tafelmissions.rules.FilterRule
import com.tafel.missions.tafelmissions.utils.model.Filter
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.time.OffsetDateTime

@Repository
class MissionDao(
        private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
        private val filterRules: List<FilterRule>
) {


    fun getAndUpdateNextMissionId(teamId: String) =
            namedParameterJdbcTemplate.queryForObject(MissionSql.GET_NEXT_MISSION_ID, mapOf("team_id" to teamId), String::class.java)

    fun createMission(teamId: String, mission: Mission): MissionDetail {
        val missionId = getAndUpdateNextMissionId(teamId)
        namedParameterJdbcTemplate.update(MissionSql.INSERT_MISSION, mapOf(
                "id" to missionId,
                "team_id" to teamId,
                "name" to mission.name,
                "description" to mission.description,
                "created_by" to mission.createdBy,
                "updated_by" to mission.createdBy,
                "labels" to mission.labels?.toTypedArray(),
                "estimation" to mission.estimation,
                "assignee_ids" to mission.assigneeIds?.toTypedArray(),
                "is_explored" to mission.isExplored,
                "status" to mission.status,
                "sprint" to mission.sprint,
                "critical" to mission.critical.priority,
                "comments" to mission.comments?.toTypedArray(),
                "created_at" to OffsetDateTime.parse(mission.createdAt),
                "updated_at" to OffsetDateTime.parse(mission.updatedAt)))
        return MissionDetail(id = missionId!!, mission = mission)

    }

    fun updateMission(teamId: String, missionId: String, mission: Mission) {
        namedParameterJdbcTemplate.update(MissionSql.UPDATE_MISSION, mapOf(
                "id" to missionId,
                "team_id" to teamId,
                "updated_by" to mission.updatedBy,
                "name" to mission.name,
                "description" to mission.description,
                "labels" to mission.labels,
                "assignee_ids" to mission.assigneeIds?.toTypedArray(),
                "is_explored" to mission.isExplored.toString(),
                "estimation" to mission.estimation,
                "status" to mission.status,
                "sprint" to mission.sprint,
                "critical" to mission.critical.priority.toString(),
                "comments" to mission.comments?.toTypedArray(),
                "updated_at" to OffsetDateTime.now()))

    }

    fun getMissionDetails(teamId: String, missionId: String? = null, filter: Filter? = null, cursor: BigInteger? = BigInteger.ONE): List<MissionDetail> {
        val sqlParamMap = mutableMapOf("team_id" to teamId, "id" to missionId, "current_cursor" to cursor, "next_cursor" to cursor!!.add(BigInteger("9")))
        val queryMapPair = buildFilterQuery(filter, MissionSql.SELECT_MISSIONS, sqlParamMap)
        return namedParameterJdbcTemplate.query(queryMapPair.first,
                queryMapPair.second, missionRowMapper)
    }


    private fun buildFilterQuery(filter: Filter?, query: String, queryMapper: MutableMap<String, Any?>): Pair<String, MutableMap<String, Any?>> {
        val dynamicQuery = filterRules
            .filter { it.isApplicable(filter!!) }
            .map { it.applyRule(filter!!, query, queryMapper) }
            .fold(query) { current, next -> current + next }

        return Pair(MissionSql.SELECT_MISSIONS_PAGINATION_PREFIX + dynamicQuery + MissionSql.SELECT_MISSIONS_PAGINATION_SUFFIX, queryMapper)
    }

    companion object {
        val missionRowMapper = MissionRowMapper()
    }

}