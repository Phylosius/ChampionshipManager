-- object: public.championship | type: TABLE --
-- DROP TABLE IF EXISTS public.championship CASCADE;
CREATE TABLE public.championship (
	id varchar NOT NULL DEFAULT uuid_generate_v4(),
	name varchar NOT NULL,
	country_id varchar NOT NULL

);
-- ddl-end --
ALTER TABLE public.championship OWNER TO championship_manager_user;
-- ddl-end --

-- object: public.country | type: TABLE --
-- DROP TABLE IF EXISTS public.country CASCADE;
CREATE TABLE public.country (
	id varchar NOT NULL DEFAULT uuid_generate_v4(),
	name varchar NOT NULL,
	continent public."CONTINENT_TYPE" DEFAULT EUROPE

);
-- ddl-end --
ALTER TABLE public.country OWNER TO championship_manager_user;
-- ddl-end --

-- object: public.club | type: TABLE --
-- DROP TABLE IF EXISTS public.club CASCADE;
CREATE TABLE public.club (
	id varchar NOT NULL DEFAULT uuid_generate_v4(),
	name varchar NOT NULL,
	creation_year integer NOT NULL,
	acronym varchar(3) NOT NULL,
	stadium_name varchar NOT NULL,
	championship_id varchar NOT NULL

);
-- ddl-end --
ALTER TABLE public.club OWNER TO championship_manager_user;
-- ddl-end --

-- object: public.coach | type: TABLE --
-- DROP TABLE IF EXISTS public.coach CASCADE;
CREATE TABLE public.coach (
	id varchar NOT NULL DEFAULT uuid_generate_v4(),
	name varchar,
	nationality varchar NOT NULL,
	club_id varchar NOT NULL

);
-- ddl-end --
ALTER TABLE public.coach OWNER TO championship_manager_user;
-- ddl-end --

-- object: public.player | type: TABLE --
-- DROP TABLE IF EXISTS public.player CASCADE;
CREATE TABLE public.player (
	id varchar NOT NULL DEFAULT uuid_generate_v4(),
	name varchar NOT NULL,
	"position" public."PLAYER_POSITION" NOT NULL,
	age integer NOT NULL,
	nationality varchar NOT NULL

);
-- ddl-end --
ALTER TABLE public.player OWNER TO championship_manager_user;
-- ddl-end --

-- object: public.player_number | type: TABLE --
-- DROP TABLE IF EXISTS public.player_number CASCADE;
CREATE TABLE public.player_number (
	club_id varchar NOT NULL,
	player_id varchar NOT NULL,
	player_number integer NOT NULL

);
-- ddl-end --
ALTER TABLE public.player_number OWNER TO championship_manager_user;
-- ddl-end --

-- object: public.season_championship | type: TABLE --
-- DROP TABLE IF EXISTS public.season_championship CASCADE;
CREATE TABLE public.season_championship (
	id varchar NOT NULL DEFAULT uuid_generate_v4(),
	season_id varchar NOT NULL,
	championship_id varchar NOT NULL

);
-- ddl-end --
ALTER TABLE public.season_championship OWNER TO championship_manager_user;
-- ddl-end --

-- object: public.season | type: TABLE --
-- DROP TABLE IF EXISTS public.season CASCADE;
CREATE TABLE public.season (
	id varchar NOT NULL DEFAULT uuid_generate_v4(),
	year integer NOT NULL,
	status public."EVENT_STATUS" DEFAULT NOT_STARTED

);
-- ddl-end --
ALTER TABLE public.season OWNER TO championship_manager_user;
-- ddl-end --

-- object: public.match | type: TABLE --
-- DROP TABLE IF EXISTS public.match CASCADE;
CREATE TABLE public.match (
	id varchar NOT NULL DEFAULT uuid_generate_v4(),
	season_championship_id varchar NOT NULL,
	home_club_id varchar NOT NULL,
	away_club_id varchar NOT NULL,
	date timestamp DEFAULT null,
	status public."EVENT_STATUS" DEFAULT NOT_STARTED

);
-- ddl-end --
ALTER TABLE public.match OWNER TO championship_manager_user;
-- ddl-end --

-- object: public.player_stats | type: TABLE --
-- DROP TABLE IF EXISTS public.player_stats CASCADE;
CREATE TABLE public.player_stats (
	id varchar NOT NULL DEFAULT uuid_generate_v4(),
	match_id varchar NOT NULL,
	player_id varchar NOT NULL,
	playing_time integer DEFAULT null

);
-- ddl-end --
COMMENT ON COLUMN public.player_stats.playing_time IS E'in seconds';
-- ddl-end --
ALTER TABLE public.player_stats OWNER TO championship_manager_user;
-- ddl-end --

-- object: public.score | type: TABLE --
-- DROP TABLE IF EXISTS public.score CASCADE;
CREATE TABLE public.score (
	id varchar NOT NULL DEFAULT uuid_generate_v4(),
	player_id varchar,
	match_id varchar,
	minute_of_goal integer,
	own_goal boolean DEFAULT false

);
-- ddl-end --
ALTER TABLE public.score OWNER TO championship_manager_user;
-- ddl-end --

