#!/bin/bash

if [ -z "$1" ]; then
    echo "Usage: $0 <java_release>"
    exit 1
fi

source_directory="src/ac_library"

java_files=$(find "$source_directory" -name "*.java")

javac --release "$1" -d out $java_files
jar cf ac_library.jar -C out .
rm -rf out
