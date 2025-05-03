-- object: championship_country_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.championship DROP CONSTRAINT IF EXISTS championship_country_id_fk CASCADE;
ALTER TABLE public.championship ADD CONSTRAINT championship_country_id_fk FOREIGN KEY (country_id)
REFERENCES public.country (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: club_championship_id | type: CONSTRAINT --
-- ALTER TABLE public.club DROP CONSTRAINT IF EXISTS club_championship_id CASCADE;
ALTER TABLE public.club ADD CONSTRAINT club_championship_id FOREIGN KEY (championship_id)
REFERENCES public.championship (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: coach_nationality_fk | type: CONSTRAINT --
-- ALTER TABLE public.coach DROP CONSTRAINT IF EXISTS coach_nationality_fk CASCADE;
ALTER TABLE public.coach ADD CONSTRAINT coach_nationality_fk FOREIGN KEY (nationality)
REFERENCES public.country (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: coach_club_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.coach DROP CONSTRAINT IF EXISTS coach_club_id_fk CASCADE;
ALTER TABLE public.coach ADD CONSTRAINT coach_club_id_fk FOREIGN KEY (club_id)
REFERENCES public.club (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: player_nationality_fk | type: CONSTRAINT --
-- ALTER TABLE public.player DROP CONSTRAINT IF EXISTS player_nationality_fk CASCADE;
ALTER TABLE public.player ADD CONSTRAINT player_nationality_fk FOREIGN KEY (nationality)
REFERENCES public.country (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: player_role_player_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.player_role DROP CONSTRAINT IF EXISTS player_role_player_id_fk CASCADE;
ALTER TABLE public.player_role ADD CONSTRAINT player_role_player_id_fk FOREIGN KEY (player_id)
REFERENCES public.player (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: player_role_club_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.player_role DROP CONSTRAINT IF EXISTS player_role_club_id_fk CASCADE;
ALTER TABLE public.player_role ADD CONSTRAINT player_role_club_id_fk FOREIGN KEY (club_id)
REFERENCES public.club (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: match_season_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.match DROP CONSTRAINT IF EXISTS match_season_id_fk CASCADE;
ALTER TABLE public.match ADD CONSTRAINT match_season_id_fk FOREIGN KEY (season_id)
REFERENCES public.season (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: match_championship_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.match DROP CONSTRAINT IF EXISTS match_championship_id_fk CASCADE;
ALTER TABLE public.match ADD CONSTRAINT match_championship_id_fk FOREIGN KEY (championship_id)
REFERENCES public.championship (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: match_home_club_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.match DROP CONSTRAINT IF EXISTS match_home_club_id_fk CASCADE;
ALTER TABLE public.match ADD CONSTRAINT match_home_club_id_fk FOREIGN KEY (home_club_id)
REFERENCES public.club (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: match_away_club_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.match DROP CONSTRAINT IF EXISTS match_away_club_id_fk CASCADE;
ALTER TABLE public.match ADD CONSTRAINT match_away_club_id_fk FOREIGN KEY (away_club_id)
REFERENCES public.club (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: player_stats_match_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.player_stats DROP CONSTRAINT IF EXISTS player_stats_match_id_fk CASCADE;
ALTER TABLE public.player_stats ADD CONSTRAINT player_stats_match_id_fk FOREIGN KEY (match_id)
REFERENCES public.match (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: player_stats_player_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.player_stats DROP CONSTRAINT IF EXISTS player_stats_player_id_fk CASCADE;
ALTER TABLE public.player_stats ADD CONSTRAINT player_stats_player_id_fk FOREIGN KEY (player_id)
REFERENCES public.player (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: score_player_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.score DROP CONSTRAINT IF EXISTS score_player_id_fk CASCADE;
ALTER TABLE public.score ADD CONSTRAINT score_player_id_fk FOREIGN KEY (player_id)
REFERENCES public.player (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

-- object: score_match_id_fk | type: CONSTRAINT --
-- ALTER TABLE public.score DROP CONSTRAINT IF EXISTS score_match_id_fk CASCADE;
ALTER TABLE public.score ADD CONSTRAINT score_match_id_fk FOREIGN KEY (match_id)
REFERENCES public.match (id) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE NO ACTION;
-- ddl-end --

