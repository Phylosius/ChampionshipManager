-- object: championship_manager_user | type: ROLE --
-- DROP ROLE IF EXISTS championship_manager_user;
CREATE ROLE championship_manager_user WITH 
	CREATEDB
	INHERIT
	LOGIN
	REPLICATION
	 PASSWORD '********';
-- ddl-end --

