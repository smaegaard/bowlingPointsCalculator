## Bowling points calculator

This project was used in an technincal interview.

#### building and running

clone this repository to an folder and run 

> gradle fatjar

and to run the build

> java -jar build/libs/bowlingPointsCalculator-1.0-SNAPSHOT.jar 

#### running test

> gradle test

#### TODO

After this was turned in, I looked through the code once again 
before the interview and found a few things that needed to be cleaned up.
I clean up an few, and there is a few of the errors that are not going to 
be fixed.

Among then are:
* APIPointsAndToken class is more or lesss the same as PointsAndToken. 
  There should only be on class. And the conversion between 1D and 2D List, 
  should happen in TranditionalScoreCalculator.
* More tests. mock the RESTClient class and do some testing of  the PointService.
* The TraditionalScoreCalculator has a error and there are not test case for it.
  If there is strike in frame 9 and a split in frame 10 the score is not 
  correctly calculated.
* PointService should only return a Json string string and not a class, this 
  decouples the PointService from the rest. And should only recieved an Json
  string as input as well.
* Model class could have an contructor class that takes a Json string, and 
  also a method that returns the class as a Json string. If not the constructor,
  then some other method that initiate the the class with the correct values.

