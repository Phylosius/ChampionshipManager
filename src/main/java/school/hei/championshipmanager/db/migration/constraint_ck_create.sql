-- object: match_clubs_ck | type: CONSTRAINT --
-- ALTER TABLE public.match DROP CONSTRAINT IF EXISTS match_clubs_ck CASCADE;
ALTER TABLE public.match ADD CONSTRAINT match_clubs_ck CHECK (host_team_id <> guest_team_id);
-- ddl-end --

