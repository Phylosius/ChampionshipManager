-- object: championship_pk | type: CONSTRAINT --
-- ALTER TABLE public.championship DROP CONSTRAINT IF EXISTS championship_pk CASCADE;
ALTER TABLE public.championship ADD CONSTRAINT championship_pk PRIMARY KEY (id);
-- ddl-end --

-- object: country_pk | type: CONSTRAINT --
-- ALTER TABLE public.country DROP CONSTRAINT IF EXISTS country_pk CASCADE;
ALTER TABLE public.country ADD CONSTRAINT country_pk PRIMARY KEY (id);
-- ddl-end --

-- object: club_pk | type: CONSTRAINT --
-- ALTER TABLE public.club DROP CONSTRAINT IF EXISTS club_pk CASCADE;
ALTER TABLE public.club ADD CONSTRAINT club_pk PRIMARY KEY (id);
-- ddl-end --

-- object: coach_pk | type: CONSTRAINT --
-- ALTER TABLE public.coach DROP CONSTRAINT IF EXISTS coach_pk CASCADE;
ALTER TABLE public.coach ADD CONSTRAINT coach_pk PRIMARY KEY (id);
-- ddl-end --

-- object: player_pk | type: CONSTRAINT --
-- ALTER TABLE public.player DROP CONSTRAINT IF EXISTS player_pk CASCADE;
ALTER TABLE public.player ADD CONSTRAINT player_pk PRIMARY KEY (id);
-- ddl-end --

-- object: player_number_pk | type: CONSTRAINT --
-- ALTER TABLE public.player_number DROP CONSTRAINT IF EXISTS player_number_pk CASCADE;
ALTER TABLE public.player_number ADD CONSTRAINT player_number_pk PRIMARY KEY (player_id,club_id);
-- ddl-end --

-- object: season_championship_pk | type: CONSTRAINT --
-- ALTER TABLE public.season_championship DROP CONSTRAINT IF EXISTS season_championship_pk CASCADE;
ALTER TABLE public.season_championship ADD CONSTRAINT season_championship_pk PRIMARY KEY (id);
-- ddl-end --

-- object: season_pk | type: CONSTRAINT --
-- ALTER TABLE public.season DROP CONSTRAINT IF EXISTS season_pk CASCADE;
ALTER TABLE public.season ADD CONSTRAINT season_pk PRIMARY KEY (id);
-- ddl-end --

-- object: match_pk | type: CONSTRAINT --
-- ALTER TABLE public.match DROP CONSTRAINT IF EXISTS match_pk CASCADE;
ALTER TABLE public.match ADD CONSTRAINT match_pk PRIMARY KEY (id);
-- ddl-end --

-- object: player_stats_pk | type: CONSTRAINT --
-- ALTER TABLE public.player_stats DROP CONSTRAINT IF EXISTS player_stats_pk CASCADE;
ALTER TABLE public.player_stats ADD CONSTRAINT player_stats_pk PRIMARY KEY (id);
-- ddl-end --

-- object: score_pk | type: CONSTRAINT --
-- ALTER TABLE public.score DROP CONSTRAINT IF EXISTS score_pk CASCADE;
ALTER TABLE public.score ADD CONSTRAINT score_pk PRIMARY KEY (id);
-- ddl-end --

