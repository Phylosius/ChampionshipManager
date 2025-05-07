-- Version TRUNCATE (recommandée)
BEGIN;

TRUNCATE TABLE
    public.score,
    public.player_stats,
    public.match,
    public.player_role,
    public.coach,
    public.player,
    public.club,
    public.season,
    public.championship,
    public.country
    RESTART IDENTITY CASCADE;

COMMIT;
