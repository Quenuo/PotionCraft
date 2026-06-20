-- Script para crear un usuario con los permisos de DB_manager
CREATE USER 'app_user'@'localhost' IDENTIFIED BY '1234';

CREATE ROLE IF NOT EXISTS 'db_manager';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, INDEX, ALTER,
CREATE VIEW, SHOW VIEW, TRIGGER, EXECUTE
    ON potioncraft.* TO 'db_manager';

GRANT 'db_manager' TO 'app_user'@'localhost';

SET DEFAULT ROLE db_manager TO 'app_user'@'localhost';
