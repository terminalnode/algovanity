CREATE USER algouser WITH PASSWORD 'algopassword';
CREATE DATABASE algodb;
GRANT ALL PRIVILEGES ON DATABASE algodb TO algouser;
\c algodb
GRANT ALL PRIVILEGES ON SCHEMA public TO algouser;
