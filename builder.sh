#!/bin/bash

set -e

cd src/ac_library
javac -d ../bin *.java
cd ../bin
jar cf ac_library.jar *
