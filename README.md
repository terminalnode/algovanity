# AlgoVanity
A webserver for generating, storing and querying [Algorand](https://developer.algorand.org/) addresses. The purpose is
to create as many addresses as possible in order to find ones with interesting patterns (e.g. addresses starting with
some variant of a word or name).

The web server is for storing new addresses, generated from a third party source, or querying the addresses generated
thus far.

# Requirements
The application is developed/tested with...
 - [Eclipse Temurin 17](https://projects.eclipse.org/projects/adoptium.temurin)
 - PostgreSQL 15, beta 1 (see [docker-compose config](docker/db))
