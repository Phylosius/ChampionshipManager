-- object: public."CONTINENT_TYPE" | type: TYPE --
-- DROP TYPE IF EXISTS public."CONTINENT_TYPE" CASCADE;
CREATE TYPE public."CONTINENT_TYPE" AS
ENUM ('AFRICA','ANTARCTICA','ASIA','OCEANIA','EUROPE','NORTH_AMERICA','SOUTH_AMERICA');
-- ddl-end --
ALTER TYPE public."CONTINENT_TYPE" OWNER TO championship_manager_user;
-- ddl-end --

-- object: public."PLAYER_POSITION" | type: TYPE --
-- DROP TYPE IF EXISTS public."PLAYER_POSITION" CASCADE;
CREATE TYPE public."PLAYER_POSITION" AS
ENUM ('STRIKER','MIDFIELDER','DEFENSE','GOAL_KEEPER');
-- ddl-end --
ALTER TYPE public."PLAYER_POSITION" OWNER TO championship_manager_user;
-- ddl-end --

-- object: public."EVENT_STATUS" | type: TYPE --
-- DROP TYPE IF EXISTS public."EVENT_STATUS" CASCADE;
CREATE TYPE public."EVENT_STATUS" AS
ENUM ('NOT_STARTED','STARTED','FINISHED');
-- ddl-end --
ALTER TYPE public."EVENT_STATUS" OWNER TO championship_manager_user;
-- ddl-end --

-- object: public."MATCH_STATUS" | type: TYPE --
-- DROP TYPE IF EXISTS public."MATCH_STATUS" CASCADE;
CREATE TYPE public."MATCH_STATUS" AS
ENUM ('DRAW','HOME_WIN','AWAY_WIN');
-- ddl-end --
ALTER TYPE public."MATCH_STATUS" OWNER TO championship_manager_user;
-- ddl-end --

