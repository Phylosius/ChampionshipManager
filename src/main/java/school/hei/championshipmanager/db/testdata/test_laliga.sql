-- Country
INSERT INTO country (id, name, continent)
VALUES ('MG', 'Madagascar', 'AFRICA'),
       ('CI', 'Côte d''ivoire', 'AFRICA');

--- Clubs
INSERT INTO club (id, name, creation_year, acronym, stadium_name, championship_id)
VALUES ('C4', 'Club 4', 1902, 'C4', 'Stade 4', 'LALIGA'),
       ('C5', 'Club 5', 1905, 'C5', 'Stade 5', 'LALIGA'),
       ('C6', 'Club 6', 1910, 'C6', 'Stade 6', 'LALIGA');

--- Coaches
INSERT INTO coach (id, name, nationality, club_id)
VALUES ('entraineur4', 'Entraîneur 4', 'MG', 'C4'),
       ('entraineur5', 'Entraîneur 5', 'CI', 'C5'),
       ('entraineur6', 'Entraîneur 6', 'ES', 'C6');

--- Players
INSERT INTO player (id, name, age, nationality)
VALUES ('gardien4', 'Gardien 4', 30, 'BR'),
       ('defense4', 'Défense 4', 25, 'BR'),
       ('milieu4', 'Milieu 4', 24, 'FR'),
       ('attaquant4', 'Attaquant 4', 17, 'DE'),

       ('gardien5', 'Gardien 5', 21, 'FR'),
       ('defense5', 'Défense 5', 34, 'BE'),
       ('milieu5', 'Milieu 5', 29, 'FR'),
       ('attaquant5', 'Attaquant 5', 18, 'DE'),

       ('gardien6', 'Gardien 6', 28, 'ES'),
       ('defense6', 'Défense 6', 21, 'BR'),
       ('milieu6', 'Milieu 6', 29, 'IT'),
       ('attaquant6', 'Attaquant 6', 23, 'DE');

--- ClubPlayer
INSERT INTO player_role (club_id, player_id, player_number, player_position)
VALUES ('C4', 'gardien4', 1, 'GOAL_KEEPER'),
       ('C4', 'defense4', 2, 'DEFENSE'),
       ('C4', 'milieu4', 5, 'MIDFIELDER'),
       ('C4', 'attaquant4', 7, 'STRIKER'),

       ('C5', 'gardien5', 1, 'GOAL_KEEPER'),
       ('C5', 'defense5', 2, 'DEFENSE'),
       ('C5', 'milieu5', 5, 'MIDFIELDER'),
       ('C5', 'attaquant5', 7, 'STRIKER'),

       ('C6', 'gardien6', 1, 'GOAL_KEEPER'),
       ('C6', 'defense6', 2, 'DEFENSE'),
       ('C6', 'milieu6', 5, 'MIDFIELDER'),
       ('C6', 'attaquant6', 7, 'STRIKER');

--- Season
INSERT INTO season (id, year, status)
VALUES ('S2024-2025', 2024, 'NOT_STARTED');
--        ('S2025-2026', 2025, 'NOT_STARTED');

-- Matches
INSERT INTO match (id, home_club_id, away_club_id, date, status, season_id, championship_id)
VALUES
       --- 2024
       ('S2024-2025-LALIGA-C4vsC5', 'C4', 'C5', '2024-08-01 18:00:00', 'NOT_STARTED', 'S2024-2025', 'LALIGA'),
       ('S2024-2025-LALIGA-C5vsC6', 'C5', 'C6', '2024-08-02 18:00:00', 'NOT_STARTED', 'S2024-2025', 'LALIGA'),
       ('S2024-2025-LALIGA-C4vsC6', 'C4', 'C6', '2024-08-03 18:00:00', 'NOT_STARTED', 'S2024-2025', 'LALIGA'),
       ('S2024-2025-LALIGA-C6vsC5', 'C6', 'C5', '2024-08-04 18:00:00', 'NOT_STARTED', 'S2024-2025', 'LALIGA'),
       ('S2024-2025-LALIGA-C5vsC4', 'C5', 'C4', '2024-08-05 18:00:00', 'NOT_STARTED', 'S2024-2025', 'LALIGA'),
       ('S2024-2025-LALIGA-C6vsC4', 'C6', 'C4', '2024-08-06 18:00:00', 'NOT_STARTED', 'S2024-2025', 'LALIGA');

--        --- 2025
--        ('S2025-2026-LALIGA-C4vsC5', 'C4', 'C5', '2025-08-01 18:00:00', 'NOT_STARTED', 'S2025-2026', 'LALIGA'),
--        ('S2025-2026-LALIGA-C5vsC6', 'C5', 'C6', '2025-08-02 18:00:00', 'NOT_STARTED', 'S2025-2026', 'LALIGA'),
--        ('S2025-2026-LALIGA-C4vsC6', 'C4', 'C6', '2025-08-03 18:00:00', 'NOT_STARTED', 'S2025-2026', 'LALIGA'),
--        ('S2025-2026-LALIGA-C6vsC5', 'C6', 'C5', '2025-08-04 18:00:00', 'NOT_STARTED', 'S2025-2026', 'LALIGA'),
--        ('S2025-2026-LALIGA-C5vsC4', 'C5', 'C4', '2025-08-05 18:00:00', 'NOT_STARTED', 'S2025-2026', 'LALIGA'),
--        ('S2025-2026-LALIGA-C6vsC4', 'C6', 'C4', '2025-08-06 18:00:00', 'NOT_STARTED', 'S2025-2026', 'LALIGA');

--- Scores
INSERT INTO score (match_id, player_id, minute_of_goal, own_goal)
VALUES
       --- 2024
       ('S2024-2025-LALIGA-C4vsC5', 'gardien4', 1, true),
       ('S2024-2025-LALIGA-C4vsC5', 'attaquant4', 2, false),
       ('S2024-2025-LALIGA-C4vsC5', 'attaquant4', 8, false),
       ('S2024-2025-LALIGA-C4vsC5', 'milieu4', 50, false),
       ('S2024-2025-LALIGA-C4vsC5', 'defense4', 60, false),

       ('S2024-2025-LALIGA-C5vsC6', 'attaquant6', 21, false),
       ('S2024-2025-LALIGA-C5vsC6', 'attaquant5', 88, false),

       ('S2024-2025-LALIGA-C4vsC6', 'attaquant4', 69, false),

       ('S2024-2025-LALIGA-C5vsC4', 'attaquant5', 88, false),

       ('S2024-2025-LALIGA-C6vsC4', 'attaquant4', 56, false),
       ('S2024-2025-LALIGA-C6vsC4', 'gardien4', 88, true),
       ('S2024-2025-LALIGA-C6vsC4', 'gardien4', 89, true),
       ('S2024-2025-LALIGA-C6vsC4', 'gardien4', 90, true),
       ('S2024-2025-LALIGA-C6vsC4', 'milieu4', 90, false);

       --- 2025
--        ('S2025-2026-LALIGA-C4vsC5', 'gardien4', 1, true),
--        ('S2025-2026-LALIGA-C4vsC5', 'attaquant4', 2, false),
--        ('S2025-2026-LALIGA-C4vsC5', 'attaquant4', 8, false),
--        ('S2025-2026-LALIGA-C4vsC5', 'milieu4', 50, false),
--        ('S2025-2026-LALIGA-C4vsC5', 'defense4', 60, false),
--
--        ('S2025-2026-LALIGA-C5vsC6', 'attaquant6', 21, false),
--
--        ('S2025-2026-LALIGA-C4vsC6', 'attaquant4', 69, false),
--
--        ('S2025-2026-LALIGA-C5vsC4', 'attaquant5', 88, false),
--
--        ('S2025-2026-LALIGA-C6vsC4', 'attaquant4', 56, false),
--        ('S2025-2026-LALIGA-C6vsC4', 'gardien4', 88, true),
--        ('S2025-2026-LALIGA-C6vsC4', 'gardien4', 89, true),
--        ('S2025-2026-LALIGA-C6vsC4', 'gardien4', 90, true),
--        ('S2025-2026-LALIGA-C6vsC4', 'milieu4', 90, false);
--

--- Playing time
INSERT INTO player_stats (match_id, player_id, playing_time)
VALUES ('S2024-2025-LALIGA-C4vsC5', 'gardien4', 32400),
       ('S2024-2025-LALIGA-C4vsC5', 'defense4', 32400),
       ('S2024-2025-LALIGA-C4vsC5', 'milieu4', 31400),
       ('S2024-2025-LALIGA-C4vsC5', 'attaquant4', 32400),

       ('S2024-2025-LALIGA-C5vsC4', 'gardien4', 0),
       ('S2024-2025-LALIGA-C5vsC4', 'defense4', 0),
       ('S2024-2025-LALIGA-C5vsC4', 'milieu4', 0),
       ('S2024-2025-LALIGA-C5vsC4', 'attaquant4', 0),

       ('S2024-2025-LALIGA-C4vsC6', 'gardien4', 0),
       ('S2024-2025-LALIGA-C4vsC6', 'defense4', 0),
       ('S2024-2025-LALIGA-C4vsC6', 'milieu4', 0),
       ('S2024-2025-LALIGA-C4vsC6', 'attaquant4', 0),

       ('S2024-2025-LALIGA-C6vsC4', 'gardien4', 0),
       ('S2024-2025-LALIGA-C6vsC4', 'defense4', 0),
       ('S2024-2025-LALIGA-C6vsC4', 'milieu4', 0),
       ('S2024-2025-LALIGA-C6vsC4', 'attaquant4', 0),


       ('S2024-2025-LALIGA-C4vsC5', 'gardien5', 32400),
       ('S2024-2025-LALIGA-C4vsC5', 'defense5', 32400),
       ('S2024-2025-LALIGA-C4vsC5', 'milieu5', 32400),
       ('S2024-2025-LALIGA-C4vsC5', 'attaquant5', 32400),

       ('S2024-2025-LALIGA-C5vsC4', 'gardien5', 0),
       ('S2024-2025-LALIGA-C5vsC4', 'defense5', 0),
       ('S2024-2025-LALIGA-C5vsC4', 'milieu5', 0),
       ('S2024-2025-LALIGA-C5vsC4', 'attaquant5', 0),

       ('S2024-2025-LALIGA-C6vsC5', 'gardien5', 0),
       ('S2024-2025-LALIGA-C6vsC5', 'defense5', 0),
       ('S2024-2025-LALIGA-C6vsC5', 'milieu5', 0),
       ('S2024-2025-LALIGA-C6vsC5', 'attaquant5', 0),

       ('S2024-2025-LALIGA-C5vsC6', 'gardien5', 0),
       ('S2024-2025-LALIGA-C5vsC6', 'defense5', 0),
       ('S2024-2025-LALIGA-C5vsC6', 'milieu5', 0),
       ('S2024-2025-LALIGA-C5vsC6', 'attaquant5', 0),


       ('S2024-2025-LALIGA-C4vsC6', 'gardien6', 32400),
       ('S2024-2025-LALIGA-C4vsC6', 'defense6', 32400),
       ('S2024-2025-LALIGA-C4vsC6', 'milieu6', 32400),
       ('S2024-2025-LALIGA-C4vsC6', 'attaquant6', 32400),

       ('S2024-2025-LALIGA-C6vsC4', 'gardien6', 0),
       ('S2024-2025-LALIGA-C6vsC4', 'defense6', 0),
       ('S2024-2025-LALIGA-C6vsC4', 'milieu6', 0),
       ('S2024-2025-LALIGA-C6vsC4', 'attaquant6', 0),

       ('S2024-2025-LALIGA-C5vsC6', 'gardien6', 0),
       ('S2024-2025-LALIGA-C5vsC6', 'defense6', 0),
       ('S2024-2025-LALIGA-C5vsC6', 'milieu6', 0),
       ('S2024-2025-LALIGA-C5vsC6', 'attaquant6', 0),

       ('S2024-2025-LALIGA-C6vsC5', 'gardien6', 0),
       ('S2024-2025-LALIGA-C6vsC5', 'defense6', 0),
       ('S2024-2025-LALIGA-C6vsC5', 'milieu6', 0),
       ('S2024-2025-LALIGA-C6vsC5', 'attaquant6', 0);


