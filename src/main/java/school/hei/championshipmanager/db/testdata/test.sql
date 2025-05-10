--- Clubs
INSERT INTO club (id, name, creation_year, acronym, stadium_name, championship_id)
VALUES ('C1', 'Club 1', 1902, 'C1', 'Stade 1', 'EPL'),
       ('C2', 'Club 2', 1905, 'C2', 'Stade 2', 'EPL'),
       ('C3', 'Club 3', 1910, 'C3', 'Stade 3', 'EPL');

--- Coaches
INSERT INTO coach (id, name, nationality, club_id)
VALUES ('entraineur1', 'Entraîneur 1', 'FR', 'C1'),
       ('entraineur2', 'Entraîneur 2', 'IT', 'C2'),
       ('entraineur3', 'Entraîneur 2', 'DE', 'C3');

--- Players
INSERT INTO player (id, name, age, nationality)
VALUES ('gardien1', 'Gardien 1', 30, 'ES'),
       ('defense1', 'Défense 1', 25, 'ES'),
       ('milieu1', 'Milieu 1', 24, 'ES'),
       ('attaquant1', 'Attaquant 1', 17, 'ES'),

       ('gardien2', 'Gardien 2', 21, 'ES'),
       ('defense2', 'Défense 2', 34, 'BE'),
       ('milieu2', 'Milieu 2', 29, 'FR'),
       ('attaquant2', 'Attaquant 2', 18, 'DE'),

       ('gardien3', 'Gardien 3', 28, 'BR'),
       ('defense3', 'Défense 3', 21, 'BR'),
       ('milieu3', 'Milieu 3', 29, 'FR'),
       ('attaquant3', 'Attaquant 3', 23, 'DE');

--- ClubPlayer
INSERT INTO player_role (club_id, player_id, player_number, player_position)
VALUES ('C1', 'gardien1', 1, 'GOAL_KEEPER'),
       ('C1', 'defense1', 2, 'DEFENSE'),
       ('C1', 'milieu1', 5, 'MIDFIELDER'),
       ('C1', 'attaquant1', 7, 'STRIKER'),

       ('C2', 'gardien2', 1, 'GOAL_KEEPER'),
       ('C2', 'defense2', 2, 'DEFENSE'),
       ('C2', 'milieu2', 5, 'MIDFIELDER'),
       ('C2', 'attaquant2', 7, 'STRIKER'),

       ('C3', 'gardien3', 1, 'GOAL_KEEPER'),
       ('C3', 'defense3', 2, 'DEFENSE'),
       ('C3', 'milieu3', 5, 'MIDFIELDER'),
       ('C3', 'attaquant3', 7, 'STRIKER');

--- Season
INSERT INTO season (id, year, status)
VALUES ('S2024-2025', 2024, 'NOT_STARTED'),
       ('S2025-2026', 2025, 'NOT_STARTED');

-- Matches
INSERT INTO match (id, home_club_id, away_club_id, date, status, season_id, championship_id)
VALUES
       --- 2024
       ('S2024-2025-EPL-C1vsC2', 'C1', 'C2', '2024-08-01 18:00:00', 'NOT_STARTED', 'S2024-2025', 'EPL'),
       ('S2024-2025-EPL-C2vsC3', 'C2', 'C3', '2024-08-08 18:00:00', 'NOT_STARTED', 'S2024-2025', 'EPL'),
       ('S2024-2025-EPL-C1vsC3', 'C1', 'C3', '2024-08-15 18:00:00', 'NOT_STARTED', 'S2024-2025', 'EPL'),
       ('S2024-2025-EPL-C3vsC2', 'C3', 'C2', '2024-08-22 18:00:00', 'NOT_STARTED', 'S2024-2025', 'EPL'),
       ('S2024-2025-EPL-C2vsC1', 'C2', 'C1', '2024-08-29 18:00:00', 'NOT_STARTED', 'S2024-2025', 'EPL'),
       ('S2024-2025-EPL-C3vsC1', 'C3', 'C1', '2024-09-05 18:00:00', 'NOT_STARTED', 'S2024-2025', 'EPL'),

       --- 2025
       ('S2025-2026-EPL-C1vsC2', 'C1', 'C2', '2025-08-01 18:00:00', 'NOT_STARTED', 'S2025-2026', 'EPL'),
       ('S2025-2026-EPL-C2vsC3', 'C2', 'C3', '2025-08-08 18:00:00', 'NOT_STARTED', 'S2025-2026', 'EPL'),
       ('S2025-2026-EPL-C1vsC3', 'C1', 'C3', '2025-08-15 18:00:00', 'NOT_STARTED', 'S2025-2026', 'EPL'),
       ('S2025-2026-EPL-C3vsC2', 'C3', 'C2', '2025-08-22 18:00:00', 'NOT_STARTED', 'S2025-2026', 'EPL'),
       ('S2025-2026-EPL-C2vsC1', 'C2', 'C1', '2025-08-29 18:00:00', 'NOT_STARTED', 'S2025-2026', 'EPL'),
       ('S2025-2026-EPL-C3vsC1', 'C3', 'C1', '2025-09-05 18:00:00', 'NOT_STARTED', 'S2025-2026', 'EPL');

--- Scores
INSERT INTO score (match_id, player_id, minute_of_goal, own_goal)
VALUES
       --- 2024
       ('S2024-2025-EPL-C1vsC2', 'gardien1', 1, true),
       ('S2024-2025-EPL-C1vsC2', 'attaquant1', 2, false),
       ('S2024-2025-EPL-C1vsC2', 'attaquant1', 8, false),
       ('S2024-2025-EPL-C1vsC2', 'milieu1', 50, false),
       ('S2024-2025-EPL-C1vsC2', 'defense1', 60, false),

       ('S2024-2025-EPL-C2vsC3', 'attaquant3', 21, false),
       ('S2024-2025-EPL-C2vsC3', 'attaquant2', 88, false),

       ('S2024-2025-EPL-C1vsC3', 'attaquant1', 69, false),

       ('S2024-2025-EPL-C2vsC1', 'attaquant2', 88, false),

       ('S2024-2025-EPL-C3vsC1', 'attaquant1', 56, false),
       ('S2024-2025-EPL-C3vsC1', 'gardien1', 88, true),
       ('S2024-2025-EPL-C3vsC1', 'gardien1', 89, true),
       ('S2024-2025-EPL-C3vsC1', 'gardien1', 90, true),
       ('S2024-2025-EPL-C3vsC1', 'milieu1', 90, false),

       --- 2025
       ('S2025-2026-EPL-C1vsC2', 'gardien1', 1, true),
       ('S2025-2026-EPL-C1vsC2', 'attaquant1', 2, false),
       ('S2025-2026-EPL-C1vsC2', 'attaquant1', 8, false),
       ('S2025-2026-EPL-C1vsC2', 'milieu1', 50, false),
       ('S2025-2026-EPL-C1vsC2', 'defense1', 60, false),

       ('S2025-2026-EPL-C2vsC3', 'attaquant3', 21, false),
       ('S2025-2026-EPL-C2vsC3', 'attaquant2', 88, false),

       ('S2025-2026-EPL-C1vsC3', 'attaquant1', 69, false),

       ('S2025-2026-EPL-C2vsC1', 'attaquant2', 88, false),

       ('S2025-2026-EPL-C3vsC1', 'attaquant1', 56, false),
       ('S2025-2026-EPL-C3vsC1', 'gardien1', 88, true),
       ('S2025-2026-EPL-C3vsC1', 'gardien1', 89, true),
       ('S2025-2026-EPL-C3vsC1', 'gardien1', 90, true),
       ('S2025-2026-EPL-C3vsC1', 'milieu1', 90, false);




