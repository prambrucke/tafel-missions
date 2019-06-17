package com.tafel.missions.tafelmissions.service

import com.tafel.missions.tafelmissions.dao.MissionDao
import com.tafel.missions.tafelmissions.exception.NotFoundException
import com.tafel.missions.tafelmissions.model.Mission
import com.tafel.missions.tafelmissions.model.MissionDetail
import com.tafel.missions.tafelmissions.model.MissionsList
import com.tafel.missions.tafelmissions.utils.model.Filter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger

@Service
class MissionService(
        private val missionDao: MissionDao
) {

    fun getMissions(teamId: String, cursor:BigInteger , filter: Filter?=null) =
          MissionsList(mission = missionDao.getMissionDetails(teamId=teamId,cursor=cursor,filter = filter))


    fun getMissionById(teamId: String, missionId: String): MissionDetail {
        val missionsList = missionDao.getMissionDetails(teamId, missionId)
        return if (!missionsList.isEmpty()) missionsList[0] else throw NotFoundException("Mission details not found")
    }


    @Transactional
    fun createMission(teamId: String, mission: Mission) =
            missionDao.createMission(teamId,mission)


    @Transactional
    fun updateMission(teamId: String, missionId: String, mission: Mission): MissionDetail {
        missionDao.updateMission(teamId, missionId, mission)
        return getMissionById(teamId, missionId)
    }

}