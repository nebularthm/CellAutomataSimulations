# Simulation Design Plan
### Team Number 05
### Names 
Michael Williams mw376

Brian Li: bl195


## Design Overview
Model: SImulate Class- In charge of controlling the simulation/grid elements, modifies grid state, knows the rules of the simulation

        Grid - Class: In charge of different grid formations
        
        Cell - Class- Each type of cell, has a state, knows it's neighbhors
        
        File reader- Gets the rules, starting config, everyhting else
        

View: View Class- Has Start, Step, End

Start- USer loads a config,

End- End button for ending simulation

Step- Animating the model



Controller-

Controller Class- Pass file to the Model

Passes the grid to the view

## Design Details

In the View, the Start method passes the selected file intro the Controller, where The file is then passed to the Model's File Reader, which passes the config to simulate



## Design Considerations

We do not know a class hierarchy for the model at this time, but we will work something out.



## User Interfac

First Screen- Select Simulation

SEcond Screen- Select Simulation File

Third Screen- See Simulation- see end button

4th Screen- Final view of the simulation, take back to start menu




## Team Responsibilities

 * Team Member #1 Brian - GUI/View and testing

 * Team Member #2 Michael Model and Testing

 * Team Member #3 Conner- Controller and Testing and part of the model

