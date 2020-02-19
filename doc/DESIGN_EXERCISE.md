# Simulation Lab Discussion
## Names and NetIDs

Connor Penny - cgp19

Brian Li - bl195

Michael Williams- mw376

### High Level Design Ideas



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