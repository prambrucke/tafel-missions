package com.tafel.missions.tafelmissions.dao.sql

object MissionSql {
    const val INSERT_MISSION = """
                INSERT INTO tafel_missions.mission (id,name,description,created_by,updated_by,labels,estimation,assignee_ids,is_explored,status,sprint,team_id,critical,comments,created_at,updated_at)
                                             values (:id,:name,:description,:created_by,:updated_by,:labels,:estimation,:assignee_ids,:is_explored,:status,:sprint,:team_id,:critical,:comments,:created_at,:updated_at);
            """

    const val SELECT_MISSIONS = """
              SELECT id, name, description, created_by,updated_by, estimation, labels, assignee_ids, is_explored, status, sprint, team_id, critical, comments, created_at, updated_at ,
              row_number() over (order by critical , created_at desc)
              FROM tafel_missions.mission WHERE team_id=:team_id and id=coalesce(:id , id)
            """


    const val GET_NEXT_MISSION_ID = """
                insert into tafel_missions.mission_sequence (team_id, next_mission_id) values (:team_id,'0') ON CONFLICT (team_id)
                DO update set next_mission_id= coalesce(mission_sequence.next_mission_id::BIGINT+1 ,EXCLUDED.next_mission_id::BIGINT)
                returning next_mission_id::BIGINT+1;
            """

    const val UPDATE_MISSION = """
            UPDATE tafel_missions.mission SET
            name=coalesce(:name,name),
            description=coalesce(:description,description),
            labels=coalesce(:labels,labels),
            estimation=coalesce(:estimation,estimation),
            assignee_ids=coalesce(:assignee_ids,assignee_ids),
            is_explored=coalesce(:is_explored,is_explored),
            status=coalesce(:status,status),
            sprint=coalesce(:sprint,sprint),
            critical=coalesce(:critical,critical),
             comments=coalesce(:comments,comments),
             updated_by=:updated_by,
             updated_at=:updated_at
            WHERE id=:id and team_id=:team_id;
                """

    const val SELECT_MISSIONS_PAGINATION_PREFIX = """select * from( """

    const val SELECT_MISSIONS_PAGINATION_SUFFIX = """ ) ordered_mission where row_number between :current_cursor and :next_cursor """

    const val SELECT_MISSIONS_LABELS_FILTER = """ and labels @> :labels::text[]  """

    const val SELECT_MISSIONS_CRITICAL_FILTER = """ and critical=coalesce(:critical , critical) """

    const val SELECT_MISSIONS_ASSIGNEE_FILTER = """ and :assignee= ANY (assignee_ids) """

    const val SELECT_MISSIONS_SPRINT_FILTER = """ and sprint=coalesce(:sprint , sprint)"""

}