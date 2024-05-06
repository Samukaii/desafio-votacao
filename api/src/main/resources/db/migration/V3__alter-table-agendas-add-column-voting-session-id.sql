alter table agendas
    ADD COLUMN voting_session_id INTEGER REFERENCES voting_sessions (id) ON DELETE CASCADE;