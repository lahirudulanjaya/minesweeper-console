# Minesweeper Console

A command-line Java implementation of the classic Minesweeper game.

## 🎮 Features

- Customizable grid size and number of mines (up to 35% of grid)
- Win/loss detection with game board rendering after every move
- Play again functionality
- Follows clean code principles and SOLID design patterns
- Fully tested with JUnit

## 📦 Tech Stack

- Java 17
- Maven
- JUnit 5

## ▶️ Running the Game

### Prerequisites

- Java 17+
- Maven 3+

### Build & Run

```bash
# 1 – run tests and package the fat JAR
mvn clean package

# 2 – play!
java -jar target/minesweeper-1.0-SNAPSHOT.jar

## 🧪 Run Tests

```bash
mvn test
```

## 📁 Project Structure

```
src/
├── main/java/com/lahiru/
│   ├── model/        # Board, Cell, Position
│   ├── controller/   # Game logic and MinePlacer
│   ├── view/         # CLI rendering and I/O
│   ├── util/         # Preconditions
│   └── MainApp.java  # Main entry point
├── test/java/com/lahiru/
    └── ...           # Unit tests
```

## 📜 License

This project is licensed under the MIT License.

## 🎯 Gameplay Details

- The game begins by prompting the user for:
  - Grid size (e.g., 4 for a 4x4 board)
  - Number of mines (up to 35% of the total squares)

- The program generates the grid and randomly places the specified number of mines.

- The user is repeatedly prompted to select a square (e.g., A1, B2):
  - If the square contains a mine → Game over
  - If it does not → The square is revealed and shows how many adjacent squares contain mines
  - If the square has zero adjacent mines → All adjacent safe squares are automatically revealed

- The game is won when all non-mine squares are revealed.

- After each move, the board is updated and shown to the user.

---

## ✅ Gameplay Example (Success)

```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
4
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 
3

Here is your minefield:
  1 2 3 4
A _ _ _ _
B _ _ _ _
C _ _ _ _
D _ _ _ _

Select a square to reveal (e.g. A1): D4
This square contains 0 adjacent mines. 

Here is your updated minefield:
  1 2 3 4
A _ _ 2 0
B _ _ 2 0
C _ 2 1 0
D _ 1 0 0

...
Congratulations, you have won the game!
Press any key to play again...
```

## ❌ Gameplay Example (Failure)

```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
3
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 
3

Here is your minefield:
  1 2 3
A _ _ _
B _ _ _
C _ _ _

Select a square to reveal (e.g. A1): C3
Oh no, you detonated a mine! Game over.
Press any key to play again...
```