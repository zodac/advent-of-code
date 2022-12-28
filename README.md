# Advent Of Code: Java Edition

![](https://img.shields.io/badge/2022%20⭐-28-yellow)
![](https://img.shields.io/badge/2021%20⭐-19-orange)
![](https://img.shields.io/badge/2020%20⭐-0-red)
![](https://img.shields.io/badge/2019%20⭐-0-red)
![](https://img.shields.io/badge/2018%20⭐-2-orange)
![](https://img.shields.io/badge/2017%20⭐-0-red)
![](https://img.shields.io/badge/2016%20⭐-0-red)
![](https://img.shields.io/badge/2015%20⭐-50-green)

## Overview

This project includes solutions to the [Advent of Code](https://adventofcode.com/) programming challenges.
The solutions are written in Java.

## Purpose

This is not an attempt to '[code golf](https://en.wikipedia.org/wiki/Code_golf)' solutions; Java is already a verbose language, so it's not the right
tool for that sort of challenge.

Instead, I aim to create a project with re-usable algorithms and function, that can be re-used as needed rather than having to import large
dependencies like [Apache Commons](https://commons.apache.org/) or [Google Guava](https://github.com/google/guava).

I'm also not aiming for extreme performance. Readability and re-usability are the main goals here, so sometimes I'll use a solution with Collections
and generics rather than a more performant array-based solution. As long as they complete in a
reasonable time[™](https://en.wikipedia.org/wiki/Reasonable_time), I'll use the more readable and generic option.

## How To View Solutions

The solutions for a given year are saved in their respective module. The solution for each day is run as a unit test, where a puzzle input is read
and passed into the actual solution class. Sometimes some minor mapping of the input to a POJO will be done in the test set-up. The result from the
solution is then asserted in the unit test.

The test module will include assertions for the example from the [Advent of Code](https://adventofcode.com/) challenge, and part 1 and 2 using your
own input files.

## Input Files

As requested by the creator of Advent of Code, no individual input files are included in this repository. My specific inputs are included as a git
submodule in the `advent-of-code-inputs` directory. Feel free to provide your own inputs in that directory. Each year's inputs should be placed in a
module matching the module name in the source code. For example, for **2015, Day 01**, its input should be available at:
`advent-of-code/advent-of-code-inputs/2015/Day01.txt`.

## License

The source code is released under the [BSD Zero Clause License](https://opensource.org/licenses/0BSD).

## Progress And Ranks

<details>
    <summary>2022 Results</summary>

| Day                                            |    Part 1 |    Part 2 |
|:-----------------------------------------------|----------:|----------:|
| [Day 1](https://adventofcode.com/2022/day/1)   | 181,122 ⭐ | 174,520 ⭐ |
| [Day 2](https://adventofcode.com/2022/day/2)   | 146,753 ⭐ | 140,513 ⭐ |
| [Day 3](https://adventofcode.com/2022/day/3)   | 112,894 ⭐ | 107,699 ⭐ |
| [Day 4](https://adventofcode.com/2022/day/4)   |  87,474 ⭐ |  85,471 ⭐ |
| [Day 5](https://adventofcode.com/2022/day/5)   |   9,071 ⭐ |   8,050 ⭐ |
| [Day 6](https://adventofcode.com/2022/day/6)   |   2,913 ⭐ |   3,596 ⭐ |
| [Day 7](https://adventofcode.com/2022/day/7)   |   9,084 ⭐ |   7,992 ⭐ |
| [Day 8](https://adventofcode.com/2022/day/8)   |   4,751 ⭐ |   3,356 ⭐ |
| [Day 9](https://adventofcode.com/2022/day/9)   |   7,495 ⭐ |   5,231 ⭐ |
| [Day 10](https://adventofcode.com/2022/day/10) |   7,670 ⭐ |   5,514 ⭐ |
| [Day 11](https://adventofcode.com/2022/day/11) |   2,664 ⭐ |   2,420 ⭐ |
| [Day 12](https://adventofcode.com/2022/day/12) |   7,311 ⭐ |   6,766 ⭐ |
| [Day 13](https://adventofcode.com/2022/day/13) |  14,960 ⭐ |  14,025 ⭐ |
| [Day 14](https://adventofcode.com/2022/day/14) |  30,919 ⭐ |  30,485 ⭐ |

</details>


<details>
    <summary>2021 Results</summary>

| Day                                            |   Part 1 |   Part 2 |
|:-----------------------------------------------|---------:|---------:|
| [Day 1](https://adventofcode.com/2021/day/1)   | 34,535 ⭐ | 30,143 ⭐ |
| [Day 2](https://adventofcode.com/2021/day/2)   | 43,645 ⭐ | 41,117 ⭐ |
| [Day 3](https://adventofcode.com/2021/day/3)   | 86,014 ⭐ | 67,217 ⭐ |
| [Day 4](https://adventofcode.com/2021/day/4)   | 17,500 ⭐ | 15,575 ⭐ |
| [Day 5](https://adventofcode.com/2021/day/5)   | 45,830 ⭐ | 43,865 ⭐ |
| [Day 6](https://adventofcode.com/2021/day/6)   | 52,188 ⭐ | 46,698 ⭐ |
| [Day 7](https://adventofcode.com/2021/day/7)   | 10,242 ⭐ | 17,459 ⭐ |
| [Day 8](https://adventofcode.com/2021/day/8)   | 27,980 ⭐ | 15,957 ⭐ |
| [Day 9](https://adventofcode.com/2021/day/9)   |          |          |
| [Day 10](https://adventofcode.com/2021/day/10) | 42,885 ⭐ | 41,392 ⭐ |
| [Day 11](https://adventofcode.com/2021/day/11) |          |          |
| [Day 12](https://adventofcode.com/2021/day/12) |          |          |
| [Day 13](https://adventofcode.com/2021/day/13) |          |          |
| [Day 14](https://adventofcode.com/2021/day/14) |          |          |
| [Day 15](https://adventofcode.com/2021/day/15) |          |          |
| [Day 16](https://adventofcode.com/2021/day/16) |          |          |
| [Day 17](https://adventofcode.com/2021/day/17) |          |          |
| [Day 18](https://adventofcode.com/2021/day/18) |          |          |
| [Day 19](https://adventofcode.com/2021/day/19) |          |          |
| [Day 20](https://adventofcode.com/2021/day/20) |          |          |
| [Day 21](https://adventofcode.com/2021/day/21) | 18,674 ⭐ |          |
| [Day 22](https://adventofcode.com/2021/day/22) |          |          |
| [Day 23](https://adventofcode.com/2021/day/23) |          |          |
| [Day 24](https://adventofcode.com/2021/day/24) |          |          |
| [Day 25](https://adventofcode.com/2021/day/25) |          |          |

</details>

<details>
    <summary>2020 Results</summary>

| Day                                            | Part 1 | Part 2 |
|:-----------------------------------------------|-------:|-------:|
| [Day 1](https://adventofcode.com/2020/day/1)   |        |        |
| [Day 2](https://adventofcode.com/2020/day/2)   |        |        |
| [Day 3](https://adventofcode.com/2020/day/3)   |        |        |
| [Day 4](https://adventofcode.com/2020/day/4)   |        |        |
| [Day 5](https://adventofcode.com/2020/day/5)   |        |        |
| [Day 6](https://adventofcode.com/2020/day/6)   |        |        |
| [Day 7](https://adventofcode.com/2020/day/7)   |        |        |
| [Day 8](https://adventofcode.com/2020/day/8)   |        |        |
| [Day 9](https://adventofcode.com/2020/day/9)   |        |        |
| [Day 10](https://adventofcode.com/2020/day/10) |        |        |
| [Day 11](https://adventofcode.com/2020/day/11) |        |        |
| [Day 12](https://adventofcode.com/2020/day/12) |        |        |
| [Day 13](https://adventofcode.com/2020/day/13) |        |        |
| [Day 14](https://adventofcode.com/2020/day/14) |        |        |
| [Day 15](https://adventofcode.com/2020/day/15) |        |        |
| [Day 16](https://adventofcode.com/2020/day/16) |        |        |
| [Day 17](https://adventofcode.com/2020/day/17) |        |        |
| [Day 18](https://adventofcode.com/2020/day/18) |        |        |
| [Day 19](https://adventofcode.com/2020/day/19) |        |        |
| [Day 20](https://adventofcode.com/2020/day/20) |        |        |
| [Day 21](https://adventofcode.com/2020/day/21) |        |        |
| [Day 22](https://adventofcode.com/2020/day/22) |        |        |
| [Day 23](https://adventofcode.com/2020/day/23) |        |        |
| [Day 24](https://adventofcode.com/2020/day/24) |        |        |
| [Day 25](https://adventofcode.com/2020/day/25) |        |        |

</details>

<details>
    <summary>2019 Results</summary>

| Day                                            | Part 1 | Part 2 |
|:-----------------------------------------------|-------:|-------:|
| [Day 1](https://adventofcode.com/2019/day/1)   |        |        |
| [Day 2](https://adventofcode.com/2019/day/2)   |        |        |
| [Day 3](https://adventofcode.com/2019/day/3)   |        |        |
| [Day 4](https://adventofcode.com/2019/day/4)   |        |        |
| [Day 5](https://adventofcode.com/2019/day/5)   |        |        |
| [Day 6](https://adventofcode.com/2019/day/6)   |        |        |
| [Day 7](https://adventofcode.com/2019/day/7)   |        |        |
| [Day 8](https://adventofcode.com/2019/day/8)   |        |        |
| [Day 9](https://adventofcode.com/2019/day/9)   |        |        |
| [Day 10](https://adventofcode.com/2019/day/10) |        |        |
| [Day 11](https://adventofcode.com/2019/day/11) |        |        |
| [Day 12](https://adventofcode.com/2019/day/12) |        |        |
| [Day 13](https://adventofcode.com/2019/day/13) |        |        |
| [Day 14](https://adventofcode.com/2019/day/14) |        |        |
| [Day 15](https://adventofcode.com/2019/day/15) |        |        |
| [Day 16](https://adventofcode.com/2019/day/16) |        |        |
| [Day 17](https://adventofcode.com/2019/day/17) |        |        |
| [Day 18](https://adventofcode.com/2019/day/18) |        |        |
| [Day 19](https://adventofcode.com/2019/day/19) |        |        |
| [Day 20](https://adventofcode.com/2019/day/20) |        |        |
| [Day 21](https://adventofcode.com/2019/day/21) |        |        |
| [Day 22](https://adventofcode.com/2019/day/22) |        |        |
| [Day 23](https://adventofcode.com/2019/day/23) |        |        |
| [Day 24](https://adventofcode.com/2019/day/24) |        |        |
| [Day 25](https://adventofcode.com/2019/day/25) |        |        |

</details>

<details>
    <summary>2018 Results</summary>

| Day                                            |   Part 1 |   Part 2 |
|:-----------------------------------------------|---------:|---------:|
| [Day 1](https://adventofcode.com/2018/day/1)   | 27,792 ⭐ | 22,808 ⭐ |
| [Day 2](https://adventofcode.com/2018/day/2)   |          |          |
| [Day 3](https://adventofcode.com/2018/day/3)   |          |          |
| [Day 4](https://adventofcode.com/2018/day/4)   |          |          |
| [Day 5](https://adventofcode.com/2018/day/5)   |          |          |
| [Day 6](https://adventofcode.com/2018/day/6)   |          |          |
| [Day 7](https://adventofcode.com/2018/day/7)   |          |          |
| [Day 8](https://adventofcode.com/2018/day/8)   |          |          |
| [Day 9](https://adventofcode.com/2018/day/9)   |          |          |
| [Day 10](https://adventofcode.com/2018/day/10) |          |          |
| [Day 11](https://adventofcode.com/2018/day/11) |          |          |
| [Day 12](https://adventofcode.com/2018/day/12) |          |          |
| [Day 13](https://adventofcode.com/2018/day/13) |          |          |
| [Day 14](https://adventofcode.com/2018/day/14) |          |          |
| [Day 15](https://adventofcode.com/2018/day/15) |          |          |
| [Day 16](https://adventofcode.com/2018/day/16) |          |          |
| [Day 17](https://adventofcode.com/2018/day/17) |          |          |
| [Day 18](https://adventofcode.com/2018/day/18) |          |          |
| [Day 19](https://adventofcode.com/2018/day/19) |          |          |
| [Day 20](https://adventofcode.com/2018/day/20) |          |          |
| [Day 21](https://adventofcode.com/2018/day/21) |          |          |
| [Day 22](https://adventofcode.com/2018/day/22) |          |          |
| [Day 23](https://adventofcode.com/2018/day/23) |          |          |
| [Day 24](https://adventofcode.com/2018/day/24) |          |          |
| [Day 25](https://adventofcode.com/2018/day/25) |          |          |

</details>

<details>
    <summary>2017 Results</summary>

| Day                                            | Part 1 | Part 2 |
|:-----------------------------------------------|-------:|-------:|
| [Day 1](https://adventofcode.com/2017/day/1)   |        |        |
| [Day 2](https://adventofcode.com/2017/day/2)   |        |        |
| [Day 3](https://adventofcode.com/2017/day/3)   |        |        |
| [Day 4](https://adventofcode.com/2017/day/4)   |        |        |
| [Day 5](https://adventofcode.com/2017/day/5)   |        |        |
| [Day 6](https://adventofcode.com/2017/day/6)   |        |        |
| [Day 7](https://adventofcode.com/2017/day/7)   |        |        |
| [Day 8](https://adventofcode.com/2017/day/8)   |        |        |
| [Day 9](https://adventofcode.com/2017/day/9)   |        |        |
| [Day 10](https://adventofcode.com/2017/day/10) |        |        |
| [Day 11](https://adventofcode.com/2017/day/11) |        |        |
| [Day 12](https://adventofcode.com/2017/day/12) |        |        |
| [Day 13](https://adventofcode.com/2017/day/13) |        |        |
| [Day 14](https://adventofcode.com/2017/day/14) |        |        |
| [Day 15](https://adventofcode.com/2017/day/15) |        |        |
| [Day 16](https://adventofcode.com/2017/day/16) |        |        |
| [Day 17](https://adventofcode.com/2017/day/17) |        |        |
| [Day 18](https://adventofcode.com/2017/day/18) |        |        |
| [Day 19](https://adventofcode.com/2017/day/19) |        |        |
| [Day 20](https://adventofcode.com/2017/day/20) |        |        |
| [Day 21](https://adventofcode.com/2017/day/21) |        |        |
| [Day 22](https://adventofcode.com/2017/day/22) |        |        |
| [Day 23](https://adventofcode.com/2017/day/23) |        |        |
| [Day 24](https://adventofcode.com/2017/day/24) |        |        |
| [Day 25](https://adventofcode.com/2017/day/25) |        |        |

</details>

<details>
    <summary>2016 Results</summary>

| Day                                            | Part 1 | Part 2 |
|:-----------------------------------------------|-------:|-------:|
| [Day 1](https://adventofcode.com/2016/day/1)   |        |        |
| [Day 2](https://adventofcode.com/2016/day/2)   |        |        |
| [Day 3](https://adventofcode.com/2016/day/3)   |        |        |
| [Day 4](https://adventofcode.com/2016/day/4)   |        |        |
| [Day 5](https://adventofcode.com/2016/day/5)   |        |        |
| [Day 6](https://adventofcode.com/2016/day/6)   |        |        |
| [Day 7](https://adventofcode.com/2016/day/7)   |        |        |
| [Day 8](https://adventofcode.com/2016/day/8)   |        |        |
| [Day 9](https://adventofcode.com/2016/day/9)   |        |        |
| [Day 10](https://adventofcode.com/2016/day/10) |        |        |
| [Day 11](https://adventofcode.com/2016/day/11) |        |        |
| [Day 12](https://adventofcode.com/2016/day/12) |        |        |
| [Day 13](https://adventofcode.com/2016/day/13) |        |        |
| [Day 14](https://adventofcode.com/2016/day/14) |        |        |
| [Day 15](https://adventofcode.com/2016/day/15) |        |        |
| [Day 16](https://adventofcode.com/2016/day/16) |        |        |
| [Day 17](https://adventofcode.com/2016/day/17) |        |        |
| [Day 18](https://adventofcode.com/2016/day/18) |        |        |
| [Day 19](https://adventofcode.com/2016/day/19) |        |        |
| [Day 20](https://adventofcode.com/2016/day/20) |        |        |
| [Day 21](https://adventofcode.com/2016/day/21) |        |        |
| [Day 22](https://adventofcode.com/2016/day/22) |        |        |
| [Day 23](https://adventofcode.com/2016/day/23) |        |        |
| [Day 24](https://adventofcode.com/2016/day/24) |        |        |
| [Day 25](https://adventofcode.com/2016/day/25) |        |        |

</details>

<details>
    <summary>2015 Results</summary>

| Day                                            |   Part 1 |   Part 2 |
|:-----------------------------------------------|---------:|---------:|
| [Day 1](https://adventofcode.com/2015/day/1)   | 16,209 ⭐ | 65,488 ⭐ |
| [Day 2](https://adventofcode.com/2015/day/2)   | 51,249 ⭐ | 46,112 ⭐ |
| [Day 3](https://adventofcode.com/2015/day/3)   | 41,971 ⭐ | 38,509 ⭐ |
| [Day 4](https://adventofcode.com/2015/day/4)   | 34,991 ⭐ | 33,645 ⭐ |
| [Day 5](https://adventofcode.com/2015/day/5)   | 34,146 ⭐ | 28,979 ⭐ |
| [Day 6](https://adventofcode.com/2015/day/6)   | 27,303 ⭐ | 26,097 ⭐ |
| [Day 7](https://adventofcode.com/2015/day/7)   | 19,007 ⭐ | 18,331 ⭐ |
| [Day 8](https://adventofcode.com/2015/day/8)   | 18,022 ⭐ | 17,020 ⭐ |
| [Day 9](https://adventofcode.com/2015/day/9)   | 15,559 ⭐ | 15,246 ⭐ |
| [Day 10](https://adventofcode.com/2015/day/10) | 16,779 ⭐ | 16,163 ⭐ |
| [Day 11](https://adventofcode.com/2015/day/11) | 14,370 ⭐ | 14,182 ⭐ |
| [Day 12](https://adventofcode.com/2015/day/12) | 14,601 ⭐ | 12,618 ⭐ |
| [Day 13](https://adventofcode.com/2015/day/13) | 12,312 ⭐ | 12,150 ⭐ |
| [Day 14](https://adventofcode.com/2015/day/14) | 13,165 ⭐ | 12,305 ⭐ |
| [Day 15](https://adventofcode.com/2015/day/15) | 10,850 ⭐ | 10,699 ⭐ |
| [Day 16](https://adventofcode.com/2015/day/16) | 11,389 ⭐ | 10,976 ⭐ |
| [Day 17](https://adventofcode.com/2015/day/17) | 10,461 ⭐ | 10,282 ⭐ |
| [Day 18](https://adventofcode.com/2015/day/18) |  9,975 ⭐ |  9,840 ⭐ |
| [Day 19](https://adventofcode.com/2015/day/19) |  9,462 ⭐ |  7,173 ⭐ |
| [Day 20](https://adventofcode.com/2015/day/20) |  8,617 ⭐ |  8,089 ⭐ |
| [Day 21](https://adventofcode.com/2015/day/21) |  7,838 ⭐ |  7,667 ⭐ |
| [Day 22](https://adventofcode.com/2015/day/22) |  6,084 ⭐ |  5,923 ⭐ |
| [Day 23](https://adventofcode.com/2015/day/23) |  7,328 ⭐ |  7,290 ⭐ |
| [Day 24](https://adventofcode.com/2015/day/24) |  6,323 ⭐ |  6,240 ⭐ |
| [Day 25](https://adventofcode.com/2015/day/25) |  7,497 ⭐ |  5,393 ⭐ |

</details>
