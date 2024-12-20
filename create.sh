#!/bin/bash
####################################
# Utility script used to download an
# input file for an Advent Of Code
# problem day, and create the Java
# files from templates.
####################################

YEAR_REGEX='^20(1[5-9]|2[0-4])$'
DAY_REGEX='^(0?[1-9]|1[0-9]|2[0-5])$'
TYPE_REGEX='^(basic|char_grid|int_grid)$'
USER_AGENT_VALUE="https://github.com/zodac/advent-of-code by zodac"

function main() {
  year=""
  day=""
  type="basic"
  type_name="Basic"
  type_directory="basic"

  while getopts 'y:d:t:' flag; do
    case "${flag}" in
      y) year="${OPTARG}" ;;
      d) day="${OPTARG}" ;;
      t) type="${OPTARG}" ;;
      *) print_usage
         exit 1 ;;
    esac
  done

  if [[ -z "${year}" ]] || [[ -z "${day}" ]]; then
      print_usage
      exit 1
  fi

  if ! [[ "${year}" =~ ${YEAR_REGEX} ]] ; then
     _error "Input year '${year}' is not a valid year"
     print_usage
     exit 1
  fi

  if ! [[ "${day}" =~ ${DAY_REGEX} ]] ; then
     _error "Input day '${day}' is not a valid day"
     print_usage
     exit 1
  fi

  if ! [[ "${type}" =~ ${TYPE_REGEX} ]] ; then
     _error "Input type '${type}' is not a valid type"
     print_usage
     exit 1
  else
    case "${type}" in
      basic)
        type_name="Basic"
        type_directory="basic"
        ;;
      char_grid)
        type_name="Character Grid"
        type_directory="grid/char"
        ;;
      int_grid)
        type_name="Integer Grid"
        type_directory="grid/int"
        ;;
    esac
  fi

  if [[ ${day} -lt 10 ]]; then
    # Add leading 0 to be used in files, but keep option without a leading 0 for HTTP requests
    day_long="0${day: -1}"
    day="${day: -1}"
  else
    day_long="${day}"
  fi

  _debug "Creating ${type_name} template for ${year}, Day ${day_long}:"

  if [[ ! -d "./${year}" ]]; then
    _error "\t- New year, please create directory structure"
    exit 1
  fi

  # Input file
  _info "\t- Creating actual input file"
  if [[ -f "./advent-of-code-inputs/${year}/day${day_long}.txt" ]]; then
    _warning "\t\t- Input file already exists, skipping"
  elif [[ -z "${AOC_COOKIE}" ]]; then
    _warning "\t\t- No cookie set for AOC, cannot create actual input file"
  else
    # Do not include '\n' when writing out the HTTP code since it will already be on its own line, and would add an extra line for actual inputs
    output=$(curl \
            --user-agent "${USER_AGENT_VALUE}" \
            --silent --header "Cookie: session=${AOC_COOKIE}" \
            --write-out "%{http_code}" \
            "https://adventofcode.com/${year}/day/${day}/input")

    http_status_code=$(echo "${output}" | tail -1)
    if [[ ${http_status_code} != "200" ]]; then
      _error "\t\t- Invalid response code for input ${http_status_code}"
      exit 1;
    fi

    echo "${output}" | head -n -1 > "./advent-of-code-inputs/${year}/day${day_long}.txt" || exit 1
    cd ./advent-of-code-inputs || exit 1
    git add "${year}/day${day_long}.txt"
    git commit --quiet -m "Adding input for ${year}, Day ${day_long}"
    git push origin HEAD:main --quiet
    cd .. || exit 1
  fi

  # Example input file
  _info "\t- Creating example input file"
  if [[ -f "./${year}/src/test/resources/day${day_long}.txt" ]]; then
    _warning "\t\t- Example file already exists, skipping"
  else
    touch "./${year}/src/test/resources/day${day_long}.txt"
  fi

  # Java source
  _info "\t- Creating Java source file"
  if [[ -f "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java" ]]; then
    _warning "\t\t- Java source file already exists, skipping"
  else
    if [[ ! -f "./templates/${type_directory}/Day.java" ]]; then
      _error "\t\t- Template source file './templates/${type_directory}/Day.java' does not exist"
      exit 1
    fi

    cp "./templates/${type_directory}/Day.java" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
    sed -i -e "s|%YEAR%|${year}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
    sed -i -e "s|%DAY%|${day}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
    sed -i -e "s|%DAY_LONG%|${day_long}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
  fi

  # Java test
  _info "\t- Creating Java test file"
  if [[ -f "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java" ]]; then
    _warning "\t\t- Java test file already exists, skipping"
  else
    if [[ ! -f "./templates/${type_directory}/DayTest.java" ]]; then
      _error "\t\t- Template test file './templates/${type_directory}/DayTest.java' does not exist"
      exit 1
    fi

    cp "./templates/${type_directory}/DayTest.java" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
    sed -i -e "s|%YEAR%|${year}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
    sed -i -e "s|%DAY%|${day}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
    sed -i -e "s|%DAY_LONG%|${day_long}|g" "./${year}/src/test/java/me/zodac/advent/Day${day_long}Test.java"
  fi

  # Title
  _info "\t- Retrieving title"
  if ! grep -q "%TITLE%" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"; then
    _warning "\t\t- Title already exists in file, skipping"
  else
    # Include '\n' when writing out the HTTP code since it will not be on its own line otherwise
    title_output=$(curl \
                    --user-agent "${USER_AGENT_VALUE}" \
                    --silent \
                    --write-out "\n%{http_code}" \
                    "https://adventofcode.com/${year}/day/${day}")

    title_http_status_code=$(echo "${title_output}" | tail -1)
    if [[ ${title_http_status_code} != "200" ]]; then
      _error "\t\t- Invalid response code for title ${title_http_status_code}"
      exit 1;
    fi

    title=$(echo "${title_output}" | head -n -1 | grep '<h2>' | awk '{split($0, a, "<h2>")} END {print a[2]}' | awk '{split($0, a, "</h2>")} END {print a[1]}' | cut -d ':' -f2 | awk '{split($0, a, "---")} END {print a[1]}' | awk '{$1=$1;print}')
    sed -i -e "s|%TITLE%|${title}|g" "./${year}/src/main/java/me/zodac/advent/Day${day_long}.java"
  fi

  _success "${type_name} template created for ${year}, Day ${day_long}"
}

function _debug() {
  # shellcheck disable=SC2059
  printf "\033[1;34m$*\033[0m\n"
}

function _info() {
  # shellcheck disable=SC2059
  printf "$*\n"
}

function _success() {
  # shellcheck disable=SC2059
  printf "\033[1;32m$*\033[0m\n"
}

function _warning() {
  # shellcheck disable=SC2059
  printf "\033[1;33m$*\033[0m\n"
}

function _error() {
  # shellcheck disable=SC2059
  printf "\033[1;31m$*\033[0m\n"
}

function print_usage() {
  _warning "Usage:\n\t-y: Year (2015-2024)\n\t-d: Day (1-25)\n\t-t: Type (Optional: 'basic', 'char_grid', 'int_grid')"
}

# Start script execution
set -euo pipefail
main "$@"
