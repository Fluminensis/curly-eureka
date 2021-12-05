# Project description
This points system have users with points in their accounts. Users only see a single balance in their accounts, but for reporting purposes we actually track their points per payer/partner. In our system, each transaction record contains: payer (string), points (integer), timestamp (date). This backend service accepts HTTP requests and returns responses based on the following conditions: 1) oldest points to be spent first  2) no payer's points to go negative

## Project components
This repo provides 1) Java program using SpringBoots for backend services, 2) Postman collection to send HTTP requests

# To import the application
1. Find the pointssystem folder from the repo

2. Open Intellij or any other Java IDE, import this project as Maven project
![image](https://user-images.githubusercontent.com/95021886/144756733-3d2c5b9e-6794-4297-adcf-3114c5c89043.png)

3. Right click pom.xml then click "Add as Maven Project"
![image](https://user-images.githubusercontent.com/95021886/144756768-0d5597bc-c08a-46e6-a6f8-ffe1351c49bf.png)

4. Go to Maven window, Click "clean" then "install" to download all dependencies
![image](https://user-images.githubusercontent.com/95021886/144756780-189e0f5c-4bf6-4a69-80fc-ffc2b460006f.png)


##  To run the application
1. In the directory, go to points-system/src/main/java/com/example/pointssystem folder/package

2. Find the PointsSystemApplication

3. Right click the file and run the application

![image](https://user-images.githubusercontent.com/95021886/144756783-c9c33086-8b1c-44e6-8110-ab49113eac1b.png)

4. Wait for the application to be ready (about 30 secs)


# To send HTTP requests

1. Find the Postman collection from the repo

2. Download postman 

3. Import the collection named PointsSystem.postman_collection.json

4. Send the request 

   There are 3 types of requests:
 
     ● Add transactions for a specific payer and date. 

     ● Spend points and get a list of player info and corresponding balance change.

        (Please note that you CANNOT spend more than total points in the system.)
  
     ● Check all payer point balances.



## API request demonstration

Here are the sample API requests with desired responses 

### Transact API
![Transaction 1](https://user-images.githubusercontent.com/95021886/144756924-a5cebeea-7d9f-45a5-accf-29f06866b111.png)
![Transaction 2](https://user-images.githubusercontent.com/95021886/144756929-538bff70-1f81-4de9-ad4c-5e00e6e19a98.png)
![Transaction 3](https://user-images.githubusercontent.com/95021886/144756925-863282ab-85eb-4a9a-be28-6a6e00b8430c.png)
![Transaction 4](https://user-images.githubusercontent.com/95021886/144756926-73340fb2-c84c-4781-a40e-80a0fccbfc87.png)
![Transaction 5](https://user-images.githubusercontent.com/95021886/144756927-e272f4d6-0222-467c-89ee-ecc82bba7eb8.png)


### Spend API
![Spend](https://user-images.githubusercontent.com/95021886/144756923-aa687d19-2618-4130-a463-c2c8df76301e.png)


### Balance API 
![Balance](https://user-images.githubusercontent.com/95021886/144756922-b1e89dac-4286-4173-977f-0b7aab609224.png)

