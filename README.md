# TheseusAndMinotaur
AI Solution for puzzle game designed by Robert Abbott

## About
The game involves the two titular characters: Theseus and the Minotaor. The player plays as Theseus and has the ability to move up, down, left, or right or to stay in place. For every move that Theseus gets, the Minotaur gets two. However, the Minotaur will always try to move horizontally closer to Theseus if it can. Otherwise, it will move vertically. If the Minotaur catches Theseus before Theseus reaches the goal, then the game is over.

## AI
In order to solve the puzzle for any level, I used a breadth first search. Because the Minotaur has predictable rules that it must follow, there was no need for a minimax algorithm. Given any state, I can find the up to 5 child states corresponding to the possible moves by Theseus and the response by the Minotaur. Since the path that the character take to reach their positions is irrelevant, I can keep track of states that have already existed and not bother searching down them. 
