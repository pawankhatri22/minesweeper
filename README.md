# Minesweeper Game - Documentation

## Overview
This is a console-based Minesweeper game implemented in Java. It follows object-oriented principles and adheres to the **Single Responsibility Principle (SRP)**. The game randomly places mines on a grid and challenges the user to uncover cells without hitting a mine. It provides feedback at each step and allows for replay after a game is won or lost.

---

## Design and Assumptions

### Design
#### Classes
1. **`MinesweeperGame`**  
   - Orchestrates the game flow.
   - Manages user inputs and integrates other components.

2. **`UserInterface`**  
   - Handles all interactions with the user.
   - Displays the grid, captures inputs, and shows messages.

3. **`GameGrid`**  
   - Manages the grid.
   - Places mines, calculates adjacent mine counts, and processes cell reveals.

4. **`Utility`**  
   - Contains reusable utility functions like input validation and constants.

5. **`GameException`**  
   - Custom exception class for handling game-specific errors.

#### Key Features
- **Randomized mine placement** for variability in gameplay.
- **Dynamic grid updates** based on user actions.
- **Automatic cell reveals** if no adjacent mines exist.
- **Game States**:
  - **Game Over:** A mine is revealed.
  - **Game Won:** All non-mine cells are revealed.
- **Replay prompt** after a game ends.

---

### Assumptions
- User inputs are valid (validated via utility methods).
- The game is played on square grids of size **2x2 or larger**.
- The maximum number of mines is capped at **35% of the grid size** for balance.

---

## Environment Setup

### Prerequisites
- **Java Version:** Java 17 or later
- **Dependency Management:** Maven
- **Operating System:** Windows, macOS, or Linux
- **Development Tools:**
  - Command line/terminal
  - Any text editor or IDE (e.g., IntelliJ IDEA, Eclipse, VS Code)

---

## How to Run the Application

### Steps
1. **Compile the Code**  
   - Open a terminal or command prompt.  
   - Run the following commands:
     ```bash
     mvn clean install
     cd target
     java -jar MineSweeper.jar
     ```

2. **Play the Game**  
   - Follow the on-screen instructions:
     - Enter grid size.
     - Specify the number of mines.
     - Input cell coordinates to reveal.
     - Type **`q`** to quit at any time.

---

## Functionalities

### 1. Starting the Game
- Displays a welcome message.
- Prompts the user to input grid size and the number of mines.

### 2. Grid Display
- Shows the current state of the grid:
  - `_` for unrevealed cells.
  - Numbers for revealed cells indicating adjacent mine counts.
  - `*` for mines (revealed only at the end of the game).

### 3. Gameplay
#### Revealing Cells
- Input coordinates (e.g., **`A1`**).
- Revealed cells trigger:
  - **Chain reveals** if no adjacent mines exist.
  - **Game over** if a mine is revealed.

#### Game End States
- **Game Over:** A mine is uncovered.
- **Game Won:** All non-mine cells are revealed.

### 4. Replay
- Prompts the user to play again or quit.

---

## Example Interaction

```plaintext
Welcome to Minesweeper!
Enter grid size (e.g., 5 for a 5x5 grid): 5
Enter the total number of mines (maximum 8): 3

  1 2 3 4 5 
A _ _ _ _ _ 
B _ _ _ _ _ 
C _ _ _ _ _ 
D _ _ _ _ _ 
E _ _ _ _ _ 

Select a cell to reveal (e.g., A1): A1
  1 2 3 4 5 
A 1 _ _ _ _ 
B _ _ _ _ _ 
C _ _ _ _ _ 
D _ _ _ _ _ 
E _ _ _ _ _ 

Select a cell to reveal (e.g., A1): B2
Boom! You hit a mine. Game Over.

Would you like to play again? (Press 'q' to quit or Enter to continue): q


