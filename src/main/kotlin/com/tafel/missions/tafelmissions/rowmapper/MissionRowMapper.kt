package com.tafel.missions.tafelmissions.rowmapper

import com.tafel.missions.tafelmissions.model.EnumLookUp
import com.tafel.missions.tafelmissions.model.Mission
import com.tafel.missions.tafelmissions.model.MissionDetail
import org.apache.commons.lang.BooleanUtils
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class MissionRowMapper : RowMapper<MissionDetail> {
    override fun mapRow(rs: ResultSet, rowNum: Int): MissionDetail? {
        val mission = Mission(
                name = rs.getString("name"),
                description = rs.getString("description"),
                labels = (rs.getArray("labels")?.array as? Array<String>)?.toList(),
                assigneeIds = (rs.getArray("assignee_ids")?.array as? Array<String>)?.toList(),
                isExplored = BooleanUtils.toBoolean(rs.getString("is_explored")),
                createdBy = rs.getString("created_by"),
                updatedBy = rs.getString("updated_by"),
                estimation = rs.getString("estimation")?:"0",
                sprint = rs.getString("sprint"),
                status = rs.getString("status"),
                teamId = rs.getString("team_id"),
                critical = EnumLookUp.getEnumType(Integer.valueOf(rs.getString("critical")))!!,
                comments = (rs.getArray("comments")?.array as? Array<String>)?.toList(),
                createdAt = rs.getString("created_at"),
                updatedAt = rs.getString("updated_at")
        )
        return MissionDetail(id = rs.getString("id"), mission = mission)
    }
}