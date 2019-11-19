# Healthcare Management System
A CSC 540 (Database Management Concepts and Systems) Course Project on HealthCare Management System.

## Team Members:
1) Vivek Reddy Karri 
2) Sahil Papalkar
3) Sanket Gopalkrishna Pai
4) Saurabh Vinod Shingte

## Installation Requirements.

### Software Requirements (This Branch)
1) Java 8
2) MySQL 8.0

#### PreRequisites (Ideal) 
1) Start a Ubuntu 16.04 LTS / Ubuntu 18.04 LTS Virtual Machine on VCL.
2) How to get started: https://vcl.ncsu.edu/get-help/documentation/how-do-i-get-started/

### Set up MySQL 8.0 DB. (Don't Use MySQL 5.7)
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

## Instructions to Populate the Database and Start the Application.

### Copy the submission zip folder:
1) scp -r <localfile> username@tohostname:<path>/<remotefile>

### unzip the directory
1) unzip HMS.zip

### Repo Walkthough
1) All sql scripts are present in sql/ folder
2) The Source code is written in utilities/ folders
3) scripts for populating data in written in  tests/ folder
4) lib/ folder has necessary external jars.
5) HMS.jar is the one to look out for.

### Set the environment variables 
1) Edit the setup.sh file to set the HMS path accordingly
2) Run the Setup File using "source setup.sh" command.

### Populating the DB 
0) Go to tests/Connect.java file, enter your mysql username and the password.
1) Make sure you are in the HMSPATH or inside HMS folder.
2) First compile the script which populated the DB.
3) Follow the instructions as specified by the program.

Commands to run the script:
0) cd $HMSPATH
1) javac tests/TestSchemaCreation.java
2) java tests.TestSchemaCreation
3) Enter 1 & 2.

### Run the application 
0) Edit the file utilities/ConnectDB.java with your username and pasword.
1) Compile this java file (CMD: javac utilities/ConnectDB.java)
2) Add this file to the HMS.jar on the home folder. (CMD: jar uf HMS.jar utilities/ConnectDB.class)
3) Execute the jar to start the application (CMD: java -jar HMS.jar)
4) You are good to go.




 
