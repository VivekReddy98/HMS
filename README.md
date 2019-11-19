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
1) Start a Ubuntu 16.04 LTS / Ubuntu 18.04 LTS Virtual Machine on VCL.
2) How to get started: https://vcl.ncsu.edu/get-help/documentation/how-do-i-get-started/

### Set up MySQL 8.0 DB. (Dont Use MySQL 5.7)
0) For Clear instructions: https://phoenixnap.com/kb/how-to-install-mysql-on-ubuntu-18-04
1) wget –c https://dev.mysql.com/get/mysql-apt-config_0.8.11-1_all.deb
2) sudo dpkg –i mysql-apt-config_0.8.11-1_all.deb (If you run into an error type this on the command line)
3) sudo apt-get update
4) sudo apt-get install mysql-server
5) NOTE: In this step, please set your db password and (Use Legacy Security Settings if prompted)
6) mysql -u root -p (Enter) and then enter your password when prompted. 
7) If you are logged in, you are good to go.
8) CREATE DATABASE HMS; (Inside the mysql prompt

### Install open-jdk:
1) sudo apt-get install openjdk-8-jdk

### Copy the submission zip folder:
scp -r <localfile> username@tohostname:<path>/<remotefile>


