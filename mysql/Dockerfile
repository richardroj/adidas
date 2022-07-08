FROM mysql/mysql-server:5.7.25

ADD schema.sql /docker-entrypoint-initdb.d
ADD data.sql /docker-entrypoint-initdb.d

EXPOSE 3306
