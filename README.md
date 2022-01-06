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

In general clinicians check patient vital signs every 4 hours, and assuming that data from the device is sent every 10s, this would mean 1440 data points will be added to the database at the time of each check. Our test csv file contains 3k data points from a sampled sine signal, which demonstrates that it would stil comfortably operate regardless of how data is transmitted from the device.

## Deployment
Since the frontend and backend of the project were developed on separate repositories, they are both continuously integrated and deployed on Heroku. 
As mentioned above, the frontend url can be found at: https://neo-patient-care.herokuapp.com/ 
The backend url can be found at: https://neo-monitoring.herokuapp.com/



