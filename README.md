Campaign Clarity
========
Required Installation
----------------------
To use this, you need python3. Make sure you have python3 installed, and accessible by the 'python3' command. 
You also need to have JDK 1.6+ installed. 

This uses the sunlight foundation's data. So, you need to install the python api via pip. 
Instructions for the installation can be found [here](http://sunlightfoundation.com/blog/2012/02/13/introducing-python-sunlight)

The web wrapper is in io.js. Install [io.js](https://iojs.org/en/index.html).

This project is also on [Hackpad](https://opendatadaydc.hackpad.com/Correlation-between-Money-and-Votes-rK20nNdsVlc)

Usage
-----
  1. `git clone` the project 
  2. If you do not have python3, install it :P 
  3. `cd` into the directory 
  4. run `npm install` to run the required packages
  5. run `javac ContributersByIndustry.java` to compile the java code
  6. run `npm start` to start the web wrapper 
  7. navigate to `localhost:3000/search` in a web browser to get started! You can enter either the keyword or a bill-id and it will search! 
  8. If you prefer to use a command line, you can do so by running `python3 VoteLinker.py -v [bill-id] | java ContributersByIndustry


  

