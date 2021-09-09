# cityRouteFinder
(Group Project : Max 2 People)

This project asked us to use a satellite map of the city of Waterford.
The project was split in half.
For the first half we were asked to use Breadth-First Search(BFS) and the second half using Dijkstra's algorithm.

With the user's input of a starting point and a finishing point, we were to determine the shortest route (in terms of distance) between the starting point/landmark and destination.

The key point of this CA exercise is in the use of graphs (i.e., nodes/vertices connected by
links/edges), along with some algorithms to search/traverse graphs. 

First we created a user-friendly JavaFX graphical user interface. 
This let the user choose their starting and finishing points.

We then mapped out nodes along the roads that BFS would use to distance-check and ultimatly provide the shortest route(min. units/pixels) for the user.

For Dijkstra's algorithm we had to implement it into our code so that it would map out the roads.
The original nodes for the roads were still used for the user to choose start/finish points.
