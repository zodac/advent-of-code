# Advent Of Code: Java

## Overview

This project includes solutions to the [Advent of Code](https://adventofcode.com/) programming challenges.
The solutions are written in Java.

## Purpose

This is not an attempt to '[code golf](https://en.wikipedia.org/wiki/Code_golf)' solutions; Java is a verbose enough language, so it's not the right
language for that sort of challenge.

Instead, I aim to create a project with re-usable algorithms and function, that can be re-used as needed rather than having to import large
dependencies like [Apache Commons](https://commons.apache.org/) or [Google Guava](https://github.com/google/guava).

I'm also not aiming for extreme performance. Readability and re-usability are the main goals here, so sometimes I'll use a solution with Collections
and generics rather than a more performant array-based solution. As long as they complete in
a [reasonable time](https://en.wikipedia.org/wiki/Reasonable_time), I'll use the more readable and generic option.

## How To View Solutions

The solutions for a given year are saved in their respective module. The solution for each day is run as a unit test, where the puzzle input is read
and passed into the actual solution class. Sometimes some minor mapping of the input to a POJO will be done.

The result from the solution is then asserted in the unit test, for both part 1 and part 2 of the challenge.

## License

The source code is released under the [BSD Zero Clause License](https://opensource.org/licenses/0BSD).
