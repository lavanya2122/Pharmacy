# Pharmacy
Coding Challenge - Feb 2019

About:
This repository was committed by me as part of coding challenge for the course of Data Engineering as assigned to me in Feb 2019

Design:
PharmacyProgram.java is the starting point of execution in this solution which optionally takes source file input as parameter otherwise picks a static source file from input directory

Used BufferedReader to read input file and processed data using Drug.java to create Drug Type object which is then stored in a HashMap using Drug Name as Key and Drug object as value (Drug object consists of Drug Name, # Prescribed, Total Cost).
Since HashMap maintains unique key, there will not be repetition of same drug multiple times but just the # of times unique prescribers & cumulative cost

Comparator is being used to sort the HashMap to order records by total cost and then by Drug Name when there is a match between Drug totals

Output file: 
A text file with name 'top_cost_drug.txt' will be created in output folder after successful execution of the program

Assumptions: 
It is assumed that input file contains exactly 5 columns of data (Id, First Name, Last Name, Drug Name, Cost) 
Any records that have more/ less than 5 columns are ignored with a print statement to the console with the record number that is being skipped

