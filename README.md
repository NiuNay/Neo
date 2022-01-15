# Neo

Neo is a webapp that allows clinicians to track the blood glucose levels of premature babies.

This repository contains source code for the backend of the application.

The full-stack application has been deployed on Heroku which can be accessed at this link: https://neo-patient-care.herokuapp.com/ 

Specifically, the app has the following functionalities:
1. Allows clinicians to add custom calibration values (a y-intercept and x-intercept value) and potential time delays into sweat glucose recordings.
2. Allows clinicians to add custom notes at specific time points.
3. Allows clinicians to input prick data at specific time points.
4. Retrieves sweat data in the form of current outputted from a device. **This has been simulated by a csv file containing a columm of time and another of current as we have not been given details on how data will be received by the application from the device. It is assumed that every 10s, a new row of current and time will be inputted in to the csv**
5. Plots the graph of sweat data vs time and prick data vs time accounting for delays and different calibrations inputted by clinicians and displays the inputted notes.

## General System Description

This app was made using a React.js - Java SpringBoot - MongoDB stack. 
Data is sourced from .csv files (named after the id of each patient eg. for baby 124790, '124790.csv') that have been stored on an Amazon S3 bucket container, and all inputted fields are stored on MongoDB Atlas - the cloud based version of MongoDB databases (NoSQL).

## Sweat Data Retrieval
As mentioned above, we have not received information from our client pertaining to how data will be sent from device to be integrated with this application. As such we have simulated this through the use of .csv files. It has been assumed that at regular time intervals, the device will update the csv files for each patient in the S3 bucket. 

Each time the user presses the "View Glucose Levels" function to view the glucose graph data, this sends a GET request that retrieves and plots the data in the .csv file for the appropriate patient. This method assumes nothing of how the data will be transmitted. Once we receive further information about the transmission of data, the app can be edited to create a live grapher that can update at regular time intervals.

In general clinicians check patient vital signs every 4 hours, and assuming that data from the device is sent every 10s, this would mean 1440 data points will be added to the database at the time of each check. Our test csv file contains 3k data points from a sampled sine signal, which demonstrates that it would still comfortably operate regardless of how data is transmitted from the device.

## Manually Inputting Prick Data, Calibration, Notes and Delay

If the user wants to input prick data, calibration (in the form of gradient or intercept) or delay they can do so on the respective input pages. Once the save data button is clicked, the data will be funneled to the MongoDB Atlas database.

After inputting the desired fields, if the user goes back to the "View glucose levels page", they will see the updated values for notes on the table on the left, the corresponding shifts in the sweat data plot and the prick data values that have been inputted in the graph on the right. 

Notes can be added on the "View Glucose Levels" page - you must be in the appropriate time frame to be able to view the corresponding notes that have been added for that period.

If you would like to view the data stored in MongoDB - to see how varying the parameters changes the graphing data, you can download mongoDB compass and connect to this server:
mongodb+srv://neoclient:neoclient1@hospitalrecords1.hybox.mongodb.net


## General Notes
If the app is being launched it may take a few seconds to load the baby objects on the select patient page (as Heroku starts up the application servers).

When the "View Glucose Levels" page is first assessed, depending on the data, the timeframe may have to be adjusted. The default data follows a sine wave, where a different calibration/delay values have been given for different days. 


## Deployment
Since the frontend and backend of the project were developed on separate repositories, they are both continuously integrated and deployed on Heroku. <br />
As mentioned above, the frontend url can be found at: https://neo-patient-care.herokuapp.com/ <br />
The backend url can be found at: https://neo-monitoring.herokuapp.com/


## Running the Application Locally
Please refer to the Final Report for instructions on this.

The application **must** be run locally if you want to run the unit tests.
