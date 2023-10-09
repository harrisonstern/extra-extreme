-- File: V20230911001921__Create_team_table.sql
-- Description: Create the 'Team' table.

create TABLE Team (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL
);
