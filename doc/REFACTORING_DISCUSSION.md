#####Our Refactoring Efforts
We had not completely integrated our separate additions to our program before this lab, and we ended up spending most of our time 
doing this. We had all made substantial additions to the program, and we needed to take the time to make sure our parts came together.

Our big redesign effort during this integration was to change our inheritance hierarchy for our Grid. We needed to change our Grid
interface to an abstract class that would be extended by different grids of different shapes. We realized that our GOLGrid class
had many methods that would be common among any grids of any shape, so we needed a hierarchy to support this. So now we are working on a Grid
abstract class with classes for different shapes in the grid that will extend Grid.