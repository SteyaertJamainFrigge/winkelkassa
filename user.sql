CREATE USER 'kassa_usr'@'localhost' IDENTIFIED BY 'Kassa1_pwd';

GRANT SELECT, INSERT, DELETE, show VIEW, update, execute on kassa to 'kassa_usr'@'localhost';