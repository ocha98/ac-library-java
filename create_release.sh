#!/bin/bash

java_files=$(find src/ac_library -name "*.java")

# for java17
javac --release 17 -d out17 $java_files
jar --create --file ac_library17.jar -C out17 .

# for java 21
javac --release 21 -d out21 $java_files
jar --create --file ac_library21.jar -C out21 .

# zip
zip -r ac-library-java.zip src/ doc/ LICENCE ac_library17.jar ac_library21.jar embedder.py builder.sh embedder.py

rm -rf out17 out21
