BEGIN;

-- 3. club
INSERT INTO public.club (name, creation_year, acronym, stadium_name, championship_id)
VALUES
    ('Manchester United',    1878, 'MUN', 'Old Trafford',        'EPL'),
    ('Real Madrid',          1902, 'RMA', 'Santiago Bernabéu',  'LALIGA'),
    ('Bayern Munich',        1900, 'FCB', 'Allianz Arena',      'BUNDESLIGA'),
    ('Paris Saint-Germain',  1970, 'PSG', 'Parc des Princes',    'LIGUE1'),
    ('Juventus',             1897, 'JUV', 'Allianz Stadium',    'SERIEA');

-- 4. coach (nationality → country_id)
INSERT INTO public.coach (name, nationality, club_id)
VALUES
    ('Erik ten Hag',         'GB', (SELECT id FROM public.club WHERE acronym = 'MUN')),
    ('Carlo Ancelotti',      'ES', (SELECT id FROM public.club WHERE acronym = 'RMA')),
    ('Julian Nagelsmann',    'DE', (SELECT id FROM public.club WHERE acronym = 'FCB')),
    ('Luis Enrique',         'FR', (SELECT id FROM public.club WHERE acronym = 'PSG')),
    ('Massimiliano Allegri', 'IT', (SELECT id FROM public.club WHERE acronym = 'JUV'));

-- 5. player (nationality → country_id)
INSERT INTO public.player (name, age, nationality)
VALUES
    ('Bruno Fernandes', 29, 'PT'),
    ('Sergio Ramos',     39, 'ES'),
    ('Joshua Kimmich',   29, 'DE'),
    ('Kylian Mbappé',    25, 'FR'),
    ('Federico Chiesa',  26, 'IT');

-- 6. player_role (utilisation des ENUM valides)
INSERT INTO public.player_role (club_id, player_id, player_number, player_position, is_active)
VALUES
    (
        (SELECT id FROM public.club   WHERE acronym = 'MUN'),
        (SELECT id FROM public.player WHERE name    = 'Bruno Fernandes'),
        18, 'MIDFIELDER', TRUE
    ),
    (
        (SELECT id FROM public.club   WHERE acronym = 'RMA'),
        (SELECT id FROM public.player WHERE name    = 'Sergio Ramos'),
        4,  'DEFENSE',    TRUE
    ),
    (
        (SELECT id FROM public.club   WHERE acronym = 'FCB'),
        (SELECT id FROM public.player WHERE name    = 'Joshua Kimmich'),
        6,  'MIDFIELDER', TRUE
    ),
    (
        (SELECT id FROM public.club   WHERE acronym = 'PSG'),
        (SELECT id FROM public.player WHERE name    = 'Kylian Mbappé'),
        7,  'STRIKER',    TRUE
    ),
    (
        (SELECT id FROM public.club   WHERE acronym = 'JUV'),
        (SELECT id FROM public.player WHERE name    = 'Federico Chiesa'),
        22, 'STRIKER',    TRUE
    );

-- 7. season
INSERT INTO public.season (year, status)
VALUES
    (2025, 'NOT_STARTED');

-- 8. match (exemple pour EPL)
INSERT INTO public.match (season_id, championship_id, home_club_id, away_club_id, date, status)
VALUES (
           (SELECT id FROM public.season       WHERE year = 2025),
           'EPL',
           (SELECT id FROM public.club         WHERE acronym = 'MUN'),
           (SELECT id FROM public.club         WHERE acronym = 'FCB'),
           '2025-08-15 20:45:00',
           'NOT_STARTED'
       );

-- 9. player_stats
INSERT INTO public.player_stats (match_id, player_id, playing_time)
VALUES
    (
        (SELECT id FROM public.match WHERE home_club_id = (SELECT id FROM public.club WHERE acronym = 'MUN')
                                       AND away_club_id = (SELECT id FROM public.club WHERE acronym = 'FCB')),
        (SELECT id FROM public.player WHERE name = 'Bruno Fernandes'),
        90
    ),
    (
        (SELECT id FROM public.match WHERE home_club_id = (SELECT id FROM public.club WHERE acronym = 'MUN')
                                       AND away_club_id = (SELECT id FROM public.club WHERE acronym = 'FCB')),
        (SELECT id FROM public.player WHERE name = 'Joshua Kimmich'),
        90
    );

-- 10. score
INSERT INTO public.score (player_id, match_id, minute_of_goal, own_goal)
VALUES
    (
        (SELECT id FROM public.player WHERE name = 'Bruno Fernandes'),
        (SELECT id FROM public.match  WHERE home_club_id = (SELECT id FROM public.club WHERE acronym = 'MUN')
                                        AND away_club_id = (SELECT id FROM public.club WHERE acronym = 'FCB')),
        23,
        FALSE
    ),
    (
        (SELECT id FROM public.player WHERE name = 'Joshua Kimmich'),
        (SELECT id FROM public.match  WHERE home_club_id = (SELECT id FROM public.club WHERE acronym = 'MUN')
                                        AND away_club_id = (SELECT id FROM public.club WHERE acronym = 'FCB')),
        67,
        FALSE
    );

COMMIT;
