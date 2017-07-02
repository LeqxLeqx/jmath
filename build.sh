#!/bin/bash

cd src

echo Compiling java...
javac `find . -name *.java` 2> javac.out

if [[ $? != 0 ]] ; then
  echo Failed to compile. Error log saved to javac.out
  exit 1
fi

echo Done

echo Creating Manifest.txt...
echo $'Manifest-version: 1.0' > Manifest.txt
echo Done

echo Creating jar...
jar cfm ../jmath.jar Manifest.txt `find . -name *.class` 2> jar.out

if [[ $? != 0 ]] ; then
  echo Failed to create jar-file. Error log saved to jar.out
  exit 2
fi

echo Done



echo Removing transient resources...
rm Manifest.txt javac.out jar.out `find . -name *.class`
echo Done


