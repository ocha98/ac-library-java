#!/bin/bash

cd src/acl
javac -d ../bin *.java
cd ../bin
jar cvf acl.jar *
