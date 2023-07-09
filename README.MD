# Puzzle Solver
A small, simple console app which uses the english dictionary to search for words that matches the users input.
As of 23rd of January 2022, the program contains four puzzle solvers.

## Usage
```
	# Compile
	javac *.java

	# Run
	java PuzzleSolverProgram
```

### 1. Anagram Solver
Takes a given input from the user, and finds all matching words containing the same letters.

#### Sample:
```
	-> jctsieu
	[justice]

	-> gmear
	[regma, marge, gamer]

	
	-> frksea
	[fakers, freaks]
```



### 2. Crossword Solver 
Takes a given input from the user, and finds all words that match this word.
The user can use '_' to add wildcards, and the program will try every letter in the
english alphabet to find matches.

#### Sample:
```
	-> test
	[test]

	-> te_t
	[text, test, tent, teat]

	-> t__t
	[twit, twat, tuft, trot, tret, tout, tost, tort, toot, toit, toft, tint, tilt, that, 
	text, test, tent, teat, taut, tart, tact]
```

### 3. Crossword Solver (Custom alphabet)
Takes a given input from the user, and finds all words that match this word using only letters from the second input.
The user can choose what letters to match wildcards with.

#### Sample:
```
	-> str_ke
	-> aeiouy
	[stroke, strike, strake]


	-> t__t
	-> abcdef
	[teat, tact]

	
	-> ______
	-> esa
	[assess, sasses]  
```

### 4. Wordle Solver
Takes a given input of current word. This would equal to green spaces in the wordle we try to solve.

The second input should contain all characters to exclude from the result. This would equal to the grey characters that is not in the word.

The third input contains the characters we know is in the word and also the position it does not belong to. This would equal to the yellow characters in the solution. The position number should start with 1 and end with 5. 

#### Sample:
```
	# Assuming the wordle is WATER and our first word was WARPS

	# We know the position of the first two letters
	-> WA____
	# Neither P or S was in the word, so we exclude those from the result
	-> PS
	# We know the letter R is in the word and that it is not on position 3
	-> R3
	[WAXER, WAVER, WATER, WALER, WAKER, WAGER, WAFER, WADER]


	-> Q__Y_
	-> E
	-> 
	[QUAYS]


	-> _____
	-> VPUMOIS
	-> R2N1N4E2E5
	[ENTRY, ENTER, ENDER, CANER, ANGER, ANEAR]
```