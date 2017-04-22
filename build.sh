#!/bin/bash

cd src

echo Compiling java...
javac jmath/*.java jmath/function/*.java jmath/function/parsing/*.java jmath/types/*.java 2> javac.out
echo Done

echo Creating Manifest.txt...
echo $'Manifest-version: 1.0' > Manifest.txt
echo Done

echo Creating jar...
jar cfm ../jmath.jar Manifest.txt jmath/*.class jmath/function/*.class jmath/function/parsing/*.class jmath/types/*.class
echo Done

echo Removing transient resources...
rm Manifest.txt javac.out jmath/*.class jmath/function/*.class jmath/function/parsing/*.class jmath/types/*.class
echo Done


