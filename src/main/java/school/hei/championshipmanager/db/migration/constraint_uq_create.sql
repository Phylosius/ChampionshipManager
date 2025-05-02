-- object: championship_name_uk | type: CONSTRAINT --
-- ALTER TABLE public.championship DROP CONSTRAINT IF EXISTS championship_name_uk CASCADE;
ALTER TABLE public.championship ADD CONSTRAINT championship_name_uk UNIQUE (name);
-- ddl-end --

-- object: country_name_uk | type: CONSTRAINT --
-- ALTER TABLE public.country DROP CONSTRAINT IF EXISTS country_name_uk CASCADE;
ALTER TABLE public.country ADD CONSTRAINT country_name_uk UNIQUE (name);
-- ddl-end --

-- object: club_name_uk | type: CONSTRAINT --
-- ALTER TABLE public.club DROP CONSTRAINT IF EXISTS club_name_uk CASCADE;
ALTER TABLE public.club ADD CONSTRAINT club_name_uk UNIQUE (name);
-- ddl-end --

-- object: player_role_uk | type: CONSTRAINT --
-- ALTER TABLE public.player_role DROP CONSTRAINT IF EXISTS player_role_uk CASCADE;
ALTER TABLE public.player_role ADD CONSTRAINT player_role_uk UNIQUE (club_id,player_number);
-- ddl-end --

-- object: season_championship_uk | type: CONSTRAINT --
-- ALTER TABLE public.season_championship DROP CONSTRAINT IF EXISTS season_championship_uk CASCADE;
ALTER TABLE public.season_championship ADD CONSTRAINT season_championship_uk UNIQUE (season_id,championship_id);
-- ddl-end --

-- object: match_uk | type: CONSTRAINT --
-- ALTER TABLE public.match DROP CONSTRAINT IF EXISTS match_uk CASCADE;
ALTER TABLE public.match ADD CONSTRAINT match_uk UNIQUE (season_championship_id,home_club_id,away_club_id);
-- ddl-end --

-- object: player_stats_uk | type: CONSTRAINT --
-- ALTER TABLE public.player_stats DROP CONSTRAINT IF EXISTS player_stats_uk CASCADE;
ALTER TABLE public.player_stats ADD CONSTRAINT player_stats_uk UNIQUE (match_id,player_id);
-- ddl-end --

-- object: score_uk | type: CONSTRAINT --
-- ALTER TABLE public.score DROP CONSTRAINT IF EXISTS score_uk CASCADE;
ALTER TABLE public.score ADD CONSTRAINT score_uk UNIQUE (player_id,match_id);
-- ddl-end --

