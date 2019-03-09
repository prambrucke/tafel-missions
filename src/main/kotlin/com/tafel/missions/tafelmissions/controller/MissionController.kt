package com.tafel.missions.tafelmissions.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tafel.missions.tafelmissions.model.Mission
import com.tafel.missions.tafelmissions.model.MissionDetail
import com.tafel.missions.tafelmissions.model.MissionsList
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MissionController(
        private val objectMapper: ObjectMapper
) {

    @GetMapping("/teams/{team-id}/missions", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMissions(@PathVariable("team-id") teamId: String): MissionsList? {
        return null
    }

    @GetMapping("/teams/{team-id}/missions/{mission-id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMissionById(@PathVariable("team-id") teamId: String, @PathVariable("mission-id") missionId: String): MissionDetail? {
        return null
    }

    @PostMapping("/teams/{team-id}/missions", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createMission(@PathVariable("team-id") teamId: String, @RequestBody mission: Mission): MissionDetail {
        return MissionDetail(id = "1222", mission = mission)
    }

    @PutMapping("/teams/{team-id}/missions/{mission-id}", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateMission(@PathVariable("team-id") teamId: String, @PathVariable("mission-id") missionId: String, @RequestBody mission: Mission): MissionDetail {
        return MissionDetail(id = missionId, mission = mission)
    }

}