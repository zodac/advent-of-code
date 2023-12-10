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
  day="${day: -1}"
else
  day_long="${day}"
fi

echo "Creating template for ${year}, Day ${day_long}:"

if [[ ! -d "./${year}" ]]; then
  echo -e "\tNew year, please create directory structure"
  exit 1
fi

# Input file
echo -e "\t- Creating actual input file"
if [[ -z "${AOC_COOKIE}" ]]; then
  echo -e "\t\tNo cookie set for AOC, cannot create actual input file"
else
  output=$(curl \
          --user-agent "https://github.com/zodac/advent-of-code by zodac" \
          --silent --header "Cookie: session=${AOC_COOKIE}" \
          --write-out "%{http_code}" \
          "https://adventofcode.com/${year}/day/${day}/input")

  http_status_code=$(echo "${output}" | tail -1)
  if [[ ${http_status_code} != "200" ]]; then
    echo "Invalid response code for input ${http_status_code}"
    exit 1;
  fi

  echo "${output}" | head -n -1 > "./advent-of-code-inputs/${year}/day${day_long}.txt" || exit 1
  cd ./advent-of-code-inputs || exit 1
  git add "${year}/day${day_long}.txt"
  git commit --quiet -m "Adding input for ${year}, Day ${day}"
  git push --quiet
  cd .. || exit 1
fi

# Example input file
echo -e "\t- Creating example input file"
touch "./${year}/src/test/resources/day${day_long}.txt"

# Java source
echo -e "\t- Creating Java source file"
cp ./templates/Day.java "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
sed -i -e "s|%YEAR%|${year}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
sed -i -e "s|%DAY%|${day}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
sed -i -e "s|%DAY_LONG%|${day_long}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"

# Java test
echo -e "\t- Creating Java test file"
cp ./templates/DayTest.java "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
sed -i -e "s|%YEAR%|${year}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
sed -i -e "s|%DAY%|${day}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
sed -i -e "s|%DAY_LONG%|${day_long}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"

# Title
echo -e "\t- Retrieving title"
title_output=$(curl \
              --user-agent "https://github.com/zodac/advent-of-code by zodac" \
              --silent \
              --write-out "%{http_code}" \
              "https://adventofcode.com/${year}/day/${day}")

title_http_status_code=$(echo "${output}" | tail -1)
if [[ ${title_http_status_code} != "200" ]]; then
  echo "Invalid response code for title ${http_status_code}"
  exit 1;
fi

title=$(echo "${title_output}" | head -n -1 | grep '<h2>' | awk '{split($0,a,"<h2>")} END{print a[2]}' | awk '{split($0,a,"</h2>")} END{print a[1]}' | cut -d ':' -f2 | cut -d '-' -f1 | awk '{$1=$1;print}')
sed -i -e "s|%TITLE%|${title}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"