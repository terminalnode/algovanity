-- User and password are based on the "local" application configuration.
-- This makes it so you could use this database as your local environment as well.
CREATE USER algouser WITH PASSWORD 'algopassword';
CREATE DATABASE algodb;
GRANT ALL PRIVILEGES ON DATABASE algodb TO algouser;
