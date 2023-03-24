Create schema saludos;
use saludos;
GRANT ALL PRIVILEGES ON saludos.* TO 'yo'@'localhost';
GRANT ALL PRIVILEGES ON saludos.* TO 'yo'@'127.0.0.1';
flush privileges;