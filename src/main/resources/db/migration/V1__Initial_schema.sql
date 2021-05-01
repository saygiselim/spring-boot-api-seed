SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;
SET default_tablespace = '';
SET default_table_access_method = heap;

-- Member Table
CREATE TABLE public.member (
    id integer NOT NULL,
    created_on timestamp without time zone NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    updated_on timestamp without time zone NOT NULL
);
ALTER TABLE public.member OWNER TO postgres;
CREATE SEQUENCE public.member_id_seq AS integer START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
ALTER TABLE public.member_id_seq OWNER TO postgres;
ALTER SEQUENCE public.member_id_seq OWNED BY public.member.id;
ALTER TABLE ONLY public.member ALTER COLUMN id SET DEFAULT nextval('public.member_id_seq'::regclass);
ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.member
    ADD CONSTRAINT email_unique UNIQUE (email);

-- Sample Table
CREATE TABLE public.sample (
    id integer NOT NULL,
    content text NOT NULL,
    created_on timestamp without time zone NOT NULL,
    title text NOT NULL,
    updated_on timestamp without time zone NOT NULL
);
ALTER TABLE public.sample OWNER TO postgres;
CREATE SEQUENCE public.sample_id_seq AS integer START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
ALTER TABLE public.sample_id_seq OWNER TO postgres;
ALTER SEQUENCE public.sample_id_seq OWNED BY public.sample.id;
ALTER TABLE ONLY public.sample ALTER COLUMN id SET DEFAULT nextval('public.sample_id_seq'::regclass);
ALTER TABLE ONLY public.sample
    ADD CONSTRAINT sample_pkey PRIMARY KEY (id);