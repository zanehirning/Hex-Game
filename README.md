# Hex-Game

All code can be found under master branch.

This code takes a set of moves, moves.txt and plays a hex game based on the moves. It utilizes a disjoint set to tell whether a player has won the game. The disjoint set utilizies union by size as well as up-tree path compression to make O(1) find times. This means that the code is able to determine a winner in O(1).
