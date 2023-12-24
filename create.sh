#!/bin/bash
###################################
# Basic script used to download an
# input file for an Advent Of Code
# problem day, and create the Java
# files from templates.
###################################

if [ "$#" -lt 2 ]; then
    echo -e "Expected at least two inputs, including:\n(1) The year\n(2) The day"
fi

year="${1}"
day="${2}"

if [[ ${day} -lt 10 ]]; then
  day_long="0${day: -1}"
  day="${day: -1}"
else
  day_long="${day}"
fi

echo "Creating template for ${year}, Day ${day_long}:"

if [[ ! -d "./${year}" ]]; then
  echo -e "\t- New year, please create directory structure"
  exit 1
fi

# Input file
echo -e "\t- Creating actual input file"
if [[ -f "./advent-of-code-inputs/${year}/day${day_long}.txt" ]]; then
  echo -e "\t\t- Input file already exists, skipping"
elif [[ -z "${AOC_COOKIE}" ]]; then
  echo -e "\t\t- No cookie set for AOC, cannot create actual input file"
else
  output=$(curl \
          --user-agent "https://github.com/zodac/advent-of-code by zodac" \
          --silent --header "Cookie: session=${AOC_COOKIE}" \
          --write-out "\n%{http_code}" \
          "https://adventofcode.com/${year}/day/${day}/input")

  http_status_code=$(echo "${output}" | tail -1)
  if [[ ${http_status_code} != "200" ]]; then
    echo -e "\t\t- Invalid response code for input ${http_status_code}"
    exit 1;
  fi

  echo "${output}" | head -n -1 > "./advent-of-code-inputs/${year}/day${day_long}.txt" || exit 1
  cd ./advent-of-code-inputs || exit 1
  git add "${year}/day${day_long}.txt"
  git commit --quiet -m "Adding input for ${year}, Day ${day_long}"
  git push --quiet
  cd .. || exit 1
fi

# Example input file
echo -e "\t- Creating example input file"
if [[ -f "./${year}/src/test/resources/day${day_long}.txt" ]]; then
  echo -e "\t\t- Example file already exists, skipping"
else
  touch "./${year}/src/test/resources/day${day_long}.txt"
fi

# Java source
echo -e "\t- Creating Java source file"
if [[ -f "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java" ]]; then
  echo -e "\t\t- Java source file already exists, skipping"
else
  cp ./templates/basic/Day.java "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
  sed -i -e "s|%YEAR%|${year}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
  sed -i -e "s|%DAY%|${day}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
  sed -i -e "s|%DAY_LONG%|${day_long}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
fi

# Java test
echo -e "\t- Creating Java test file"
if [[ -f "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java" ]]; then
  echo -e "\t\t- Java test file already exists, skipping"
else
  cp ./templates/basic/DayTest.java "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
  sed -i -e "s|%YEAR%|${year}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
  sed -i -e "s|%DAY%|${day}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
  sed -i -e "s|%DAY_LONG%|${day_long}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
fi

# Title
echo -e "\t- Retrieving title"
title_output=$(curl \
              --user-agent "https://github.com/zodac/advent-of-code by zodac" \
              --silent \
              --write-out "\n%{http_code}" \
              "https://adventofcode.com/${year}/day/${day}")

title_http_status_code=$(echo "${title_output}" | tail -1)
if [[ ${title_http_status_code} != "200" ]]; then
  echo -e "\t\t- Invalid response code for title ${title_http_status_code}"
  exit 1;
fi

title=$(echo "${title_output}" | head -n -1 | grep '<h2>' | awk '{split($0,a,"<h2>")} END{print a[2]}' | awk '{split($0,a,"</h2>")} END{print a[1]}' | cut -d ':' -f2 | cut -d '-' -f1 | awk '{$1=$1;print}')
if grep -q "${title}" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"; then
  echo -e "\t\t- Title already exists in file, skipping"
else
  sed -i -e "s|%TITLE%|${title}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
fi
