#!/bin/bash
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *#
#*                                                                         *#
#*  jmath: a library for mathematical computation for Java                 *#
#*  Copyright (C) 2017  LeqxLeqx                                           *#
#*                                                                         *#
#*  This program is free software: you can redistribute it and/or modify   *#
#*  it under the terms of the GNU General Public License as published by   *#
#*  the Free Software Foundation, either version 3 of the License, or      *#
#*  (at your option) any later version.                                    *#
#*                                                                         *#
#*  This program is distributed in the hope that it will be useful,        *#
#*  but WITHOUT ANY WARRANTY; without even the implied warranty of         *#
#*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          *#
#*  GNU General Public License for more details.                           *#
#*                                                                         *#
#*  You should have received a copy of the GNU General Public License      *#
#*  along with this program.  If not, see <http://www.gnu.org/licenses/>.  *#
#*                                                                         *#
#* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *#


if [[ $1 == "clean" ]] ; then
  rm -f jmath-*.jar
  rm -rf bin
  exit 0
fi

if [[ !(-e bin) ]] ; then
  mkdir bin
fi

cd src

echo Compiling java...
javac -d ../bin `find . -name *.java` 2> ../javac.out

if [[ $? != 0 ]] ; then
  echo Failed to compile. Error log saved to javac.out
  exit 1
fi

cd ../bin

echo Done

echo Creating Manifest.txt...
echo $'Manifest-version: 1.0' > Manifest.txt
echo Done

echo Creating jar...
jar cfm jmath.jar Manifest.txt `find . -name *.class` 2> ../jar.out

if [[ $? != 0 ]] ; then
  echo Failed to create jar-file. Error log saved to jar.out
  exit 2
fi

echo Done


echo Getting version...

echo $'import jmath.JMath; public class VersionGetter { public static void main(String[] args) { System.out.print(JMath.getVersion()); } }' > VersionGetter.java
javac -cp .:jmath.jar VersionGetter.java
VERSION=`java -cp .:jmath.jar VersionGetter`

echo Done

echo Moving bin/jmath.jar to ./jmath-$VERSION.jar...
mv ./jmath.jar ../jmath-$VERSION.jar
echo Done

cd ..

echo Removing transient resources...
rm -f bin/Manifest.txt javac.out jar.out `find . -name *.class`
echo Done


