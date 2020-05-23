#!/usr/bin/env sh

# you can use this command to update db.sql file
docker exec -t -e PGPASSWORD=password pgadmin-container /usr/local/pgsql-12/pg_dump -s -w -h "postgres-container" -U "postgres" -d "sample" >db.sql
