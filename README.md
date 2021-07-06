# alea-iacta-sys-lexarcana2
A RPG system module for Alea Iacta Est implementing Lex Arcana - 2nd Edition

## Description
This command can take up to 3 parameters, which are the dice points spent on each dice to roll in the single go. In case this is a fate roll, the command will automatically repeat the roll and add the results together.

### Roll modifiers
Passing these parameters, the associated modifier will be enabled:

* `-v` : Will enable a more verbose mode that will show a detailed version of every result obtained in the roll, grouped together, to better show eventual fate rolls.

## Help print
```
Lex Arcana 2nd Edition [ lex-arcana-2nd | lex ]

Usage: lex -f <singleDice>
   or: lex -f <firstDice> -s <secondDice>
   or: lex -f <firstDice> -s <secondDice> -t <thirdDice>

Description:
This command can take up to 3 parameters, which are the dice
points spent on each dice to roll in the single go. In case
this is a fate roll, the command will automatically repeat
the roll and add the results together.

Options:
  -f, --first-dice=firstDice
                  Dice points to use for the first dice (for a d4 => 4)
  -s, --second-dice=secondDice
                  Dice points to use for the second dice (for a d4 => 4)
  -t, --third-dice=thirdDice
                  Dice points to use for the third dice (for a d4 => 4)
  -h, --help      Print the command help
  -v, --verbose   Enable verbose output
```
