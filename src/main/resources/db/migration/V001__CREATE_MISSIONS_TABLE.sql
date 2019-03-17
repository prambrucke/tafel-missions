
CREATE TABLE mission(
  id text not null,
  name text not null,
  description text not null,
  created_by text not null,
  updated_by text,
  labels text[],
  estimation text,
  assignee_ids text[],
  is_explored text not null,
  status text,
  sprint text,
  team_id text not null,
  critical text,
  comments text[],
  created_at timestamp without time zone,
  updated_at timestamp without time zone,
  primary key (id,team_id)
);


CREATE TABLE mission_sequence(
    team_id text not null,
    next_mission_id text not null,
    primary key(team_id)
)