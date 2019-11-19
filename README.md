# HMC
A CSC 540 Course Project on HealthCare Management System

## Change your paths accordingly
1) export CLASSPATH=".:/afs/bp.ncsu.edu/dist/oracle10g/.install/i386_linux26/OraHome1/jdbc/lib classes12.zip":":/afs/unity.ncsu.edu/users/v/vkarri/HMS":"${CLASSPATH}"
2) export CLASSPATH="/afs/unity.ncsu.edu/users/v/vkarri/HMS/lib/*":"${CLASSPATH}"
3) export HMSPATH="/afs/unity.ncsu.edu/users/s/sgpai2/HMS/"
4) alias java="java -cp .:/afs/bp.ncsu.edu/dist/oracle10g/.install/i386_linux26/OraHome1/jdbc/lib/classes12.zip"
5) add jdk

## Installation Requirements.

PreRequisites (Ideal) 
1) Start a Ubuntu 16.04 LTS / Ubuntu 18.04 LTS Virtual Machine on VCL

### Set up MySQL 8.0 DB. (Dont Use MySQL 5.7)
1) wget –c https://dev.mysql.com/get/mysql-apt-config_0.8.11-1_all.deb
2) sudo dpkg –i mysql-apt-config_0.8.10-1_all.deb
3) sudo apt-get update
4) sudo apt-get install mysql-server
5) NOTE: In this step, please set your db password and enter n/no in the questions followed by.
6) sudo mysql_secure_installation
