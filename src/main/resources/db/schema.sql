DROP TABLE orc IF EXISTS;
DROP TABLE horde IF EXISTS;

create table horde
(
  id         bigint IDENTITY
    primary key,
  horde_name varchar(255) null,
  leader_id  bigint       null
);

create index FK93g94iaxa7poqympun552s09j
  on horde (leader_id);

create table orc
(
  id             bigint IDENTITY
    primary key,
  orc_kill_count bigint       null,
  orc_name       varchar(255) null,
  orc_race_type  varchar(255) null,
  horde_id       bigint       null
);

create index FKev52779b7pwn7ukhskr1ev7lt
  on orc (horde_id);

create table orc_weapon
(
  orc_id     bigint       not null,
  orc_weapon varchar(255) null
);

create index FK1lqihhlwvs5u9v6wm39icnj7w
  on orc_weapon (orc_id);

