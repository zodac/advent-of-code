#!/bin/bash
###################################
# Basic script used to download an
# input file for an Advent Of Code
# problem day, and create the Java
# files from templates.
###################################

if [ "$#" -ne 2 ]; then
    echo "Expected two inputs, the year and the day"
fi

year="${1}"
day="${2}"

if [[ ${day} -lt 10 ]]; then
  day_long="0${day: -1}"
else
  day_long="${day}"
fi

echo "Creating template for ${year}, Day ${day_long}:"

if [[ ! -d "./${year}" ]]; then
  echo -e "\tNew year, please create directory structure"
  exit 1
fi

echo -e "\t- Creating Java source file"
cp ./templates/Day.java "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
sed -i -e "s|%YEAR%|${year}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
sed -i -e "s|%DAY%|${day}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
sed -i -e "s|%DAY_LONG%|${day_long}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"

echo -e "\t- Creating Java test file"
cp ./templates/DayTest.java "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
sed -i -e "s|%YEAR%|${year}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
sed -i -e "s|%DAY%|${day}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
sed -i -e "s|%DAY_LONG%|${day_long}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"

echo -e "\t- Creating example input file"
touch "./${year}/src/test/resources/day${day_long}.txt"

echo -e "\t- Creating actual input file"
if [[ -z "${AOC_COOKIE}" ]]; then
  echo -e "\t\tNo cookie set for AOC, cannot create actual input file"
else
  curl --silent --header "Cookie: session=${AOC_COOKIE}" "https://adventofcode.com/${year}/day/${day}/input" > "./advent-of-code-inputs/${year}/day${day_long}.txt" || exit 1
  cd ./advent-of-code-inputs || exit 1
  git add "${year}/day${day_long}.txt"
  git commit -m "Adding input for ${year}, Day ${day}"
  git push --quiet
  cd .. || exit 1
fi

echo -e "\t- Retrieving title"
title=$(curl --silent "https://adventofcode.com/${year}/day/${day}" | grep '<h2>' | awk '{split($0,a,"<h2>")} END{print a[2]}' | awk '{split($0,a,"</h2>")} END{print a[1]}' | cut -d ':' -f2 | cut -d '-' -f1 | awk '{$1=$1;print}')
sed -i -e "s|%TITLE%|${title}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
