A small, simple program which uses a dictionary to search for words that matches the users input.

As of 23rd of January, the program contains two puzzle solvers.

1. Anagram Solver
	This takes a given input from the user, and finds all matching words containing the same letters.

	Example:
	-> jctsieu
	[justice]

	-> gmear
	[regma, marge, gamer]

	
	-> frksea
	[fakers, freaks]




2. Crossword Solver
	This takes a given input from the user, and finds all words that match this word.
	The user can use '_' to add wildcards, and the program will try every letter in the
	english alphabet to find matches.

	Example:
	-> test
	[test]

	-> te_t
	[text, test, tent, teat]

	-> t__t
	[twit, twat, tuft, trot, tret, tout, tost, tort, toot, toit, toft, tint, tilt, that, 
	text, test, tent, teat, taut, tart, tact]


3. Crossword Solver (Custom alphabet)
	Does the same as (2), but adds an additional parameter: Alphabet.
	The user can choose what letters to match wildcards with.

	Example:
	-> str_ke   aeiouy
	[stroke, strike, strake]


	-> t__t     abcdef
	[teat, tact]

	
	-> ______   esa
	[assess, sasses]  
