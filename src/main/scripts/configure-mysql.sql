## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

#Create database service accounts
CREATE USER 'zozo'@'localhost' IDENTIFIED BY 'zozo';
CREATE USER 'zozo'@'localhost' IDENTIFIED BY 'zozo';
CREATE USER 'zozo'@'%' IDENTIFIED BY 'zozo';
CREATE USER 'zozo'@'%' IDENTIFIED BY 'zozo';

#Database grants
GRANT SELECT ON sfg_dev.* to 'zozo'@'localhost';
GRANT INSERT ON sfg_dev.* to 'zozo'@'localhost';
GRANT DELETE ON sfg_dev.* to 'zozo'@'localhost';
GRANT UPDATE ON sfg_dev.* to 'zozo'@'localhost';
GRANT SELECT ON sfg_prod.* to 'zozo'@'localhost';
GRANT INSERT ON sfg_prod.* to 'zozo'@'localhost';
GRANT DELETE ON sfg_prod.* to 'zozo'@'localhost';
GRANT UPDATE ON sfg_prod.* to 'zozo'@'localhost';
GRANT SELECT ON sfg_dev.* to 'zozo'@'%';
GRANT INSERT ON sfg_dev.* to 'zozo'@'%';
GRANT DELETE ON sfg_dev.* to 'zozo'@'%';
GRANT UPDATE ON sfg_dev.* to 'zozo'@'%';
GRANT SELECT ON sfg_prod.* to 'zozo'@'%';
GRANT INSERT ON sfg_prod.* to 'zozo'@'%';
GRANT DELETE ON sfg_prod.* to 'zozo'@'%';
GRANT UPDATE ON sfg_prod.* to 'zozo'@'%';