# Neo

Neo is a webapp that allows clinicians to track the blood glucose levels of premature babies.

The application runs on a local server and stores the data on in MongoDB (NoSQL) locally. 

In order to run the applicaton, first download MongoDB Community Service at https://www.mongodb.com/try/download/community

Once downloaded, create a database with the name "HospitalRecords", with Collection Name "Babies"

In your IDE, run the "NeoApplication" class. This sets up the local server and connects the HospitalRecords Database in MongoDB to the application.
The application will automatically create 2 baby objects when launched.

# Sweat Glucose Data

Sweat glucose data must be saved in a csv file and the path must be specified in the "UpdateSweatLevels" method in the "Baby Service" class. This way, whenever a get request is specified, the application will parse through the csv data file to first update the sweat data in the database before sending it on to the front-end.





