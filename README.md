# swgoh-mysql

## Purpose
This application will create and populate database tables and views for a guild and guild membership for Star Wars, Galaxy of Heroes

## Requirements
This application uses Java 11+, and a MySQL 5.8+ server needs to be running as well (can be local or remote).
The recommended usage involves using maven, version 3+.

## Usage
The easiest way to run this application is to:
1. Clone this repository
2. Navigate to the root of the application in the command line
3. Run the following: mvn spring-boot:run -Dspring-boot.run.arguments={guild_identifier}
Replace {guild_identifier} with your guild's identifier. It can be found by locating your guild in swgoh.gg, then taking the last part.
For example, for the guild https://swgoh.gg/g/VC-ZpmNNRnaMOqopvVFXag/, the program execution would be mvn spring-boot:run -Dspring-boot.run.arguments=VC-ZpmNNRnaMOqopvVFXag

## Issues
If you experience any issues getting this to run, please log an issue here on the github project.
