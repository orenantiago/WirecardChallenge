# WirecardChallenge
This is my solution to wirecardbrasil's technical [challenge](https://github.com/wirecardBrasil/challenge). A postman documentation to help clarify how to use the API can be found [here](https://documenter.getpostman.com/view/2581871/RztmspT4). 

## How to Run the project
### Requirements
The requirements to run this project are Gradle 4.4, Java 1.8 and MySQL 14.14.   
After installing the required software, you should proceed to create a database. Run the following code:   
`sudo mysql -password`   
This will require you to type your sudo password.   
Once inside Mysql console, run
```create database wirecard_challenge;```,  
Then `GRANT ALL PRIVILEGES ON wirecard_challenge.* TO 'wirecard'@'localhost' IDENTIFIED BY 'password';`
### Running the project
