INSERT INTO horde (id, horde_name, leader_id) VALUES (1, 'Killemall', 1)

INSERT INTO orc (id, orc_name, orc_race_type, orc_kill_count, horde_id) VALUES (1, 'Tibi', 'MOUNTAIN', 100, null);
INSERT INTO orc (id, orc_name, orc_race_type, orc_kill_count, horde_id) VALUES (2, 'Lajos', 'URUK', 120, 1);

INSERT INTO orc_weapon (orc_id, orc_weapon) VALUES (1, 'SWORD')
INSERT INTO orc_weapon (orc_id, orc_weapon) VALUES (1, 'BOW')
INSERT INTO orc_weapon (orc_id, orc_weapon) VALUES (2, 'KNIFE')

