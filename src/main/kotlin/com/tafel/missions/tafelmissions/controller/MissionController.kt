package com.tafel.missions.tafelmissions.controller

import com.tafel.missions.tafelmissions.model.Mission
import com.tafel.missions.tafelmissions.model.MissionDetail
import com.tafel.missions.tafelmissions.model.MissionsList
import com.tafel.missions.tafelmissions.service.MissionService
import com.tafel.missions.tafelmissions.utils.model.Filter
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger

@RestController
class MissionController(
        private val missionService: MissionService
) {

    @GetMapping("/teams/{team-id}/missions", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMissions(@PathVariable("team-id") teamId: String, @RequestParam(defaultValue = "1") cursor: BigInteger, filter: Filter): MissionsList? {
        log.info("Fetching missions for team $teamId")
        return missionService.getMissions(teamId, cursor, filter)
    }

    @GetMapping("/teams/{team-id}/missions/{mission-id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMissionById(@PathVariable("team-id") teamId: String, @PathVariable("mission-id") missionId: String): MissionDetail? {
        log.info("Fetching mission for team $teamId with mission id $missionId")
        return missionService.getMissionById(teamId, missionId)
    }

    @PostMapping("/teams/{team-id}/missions", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createMission(@PathVariable("team-id") teamId: String, @RequestBody mission: Mission): MissionDetail {
        log.info("Creating a new mission for team $teamId")
        return missionService.createMission(teamId, mission)
    }

    @PutMapping("/teams/{team-id}/missions/{mission-id}", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateMission(@PathVariable("team-id") teamId: String, @PathVariable("mission-id") missionId: String, @RequestBody mission: Mission): MissionDetail {
        log.info("Updating mission $missionId for team $teamId")
        return missionService.updateMission(teamId, missionId, mission)
    }


    companion object {
        private val log = LoggerFactory.getLogger(MissionController::class.java)
    }

}