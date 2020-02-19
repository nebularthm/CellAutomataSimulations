# Simulation Lab Discussion
## Names and NetIDs

Connor Penny - cgp19

Brian Li - bl195

Michael Williams- mw376

### High Level Design Ideas

1. The cell will update its state based on the rules in the Simulate Class, the cell itself will just recieve this information for updating its state as a parameter

2.Based on the rules, a cell will have pointers to what cells count as its neighbhors, and the simulate class will handle updating the cell based on its neighbors on a cell by cell basis, to prevent a cell from interfering with its neighbhors

3.  The grid itself will just be a data structure containing cell objects, the Simulate and related calsses will handle updating the grid based on the rules for the simulation.


4. The information from a config file will contain valid cell neghbors, the rules of the game, the initial state(seed) of the grid, and the size of the grid. This information will be processed by a file reading class.

5. Each frame a view of the grid is updated in the GUI, so that the user can see in real time what is happening on a frame by frame basis.

### CRC Card Classes

This class's purpose is to hold a grid of Cell objects as an instance
variable and update those cells over the timeline of the simulation by passing
the state change to the cell as a parameter:
```java
 public class Simulate {
     public void updateCellGrid ()
 }
```

This class's purpose is to hold information about its state and neighbors and
to be able to update itself over time.
```java
 public class Cell {
     public void updateState ()
```

```java 
 public class CellGrid {
 }
```


### Use Cases

 * Apply the rules to a cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all of its neighbors)
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * Move to the next generation: update all cells in a simulation from their current state to their next state
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * Switch simulations: load a new simulation from a data file, replacing the current running simulation with the newly loaded one
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```