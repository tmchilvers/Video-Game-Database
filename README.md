# VideoGameDatabase
Designed by Jonathan Bahm and Tristan Chilvers. <br/>
There is also a PNG of the schema to help visualize the database 

## Install JavaFX
Helpful link for setting up: https://openjfx.io/openjfx-docs/#introduction <br/>
<br/>
For Windows: <br/>
set PATH_TO_FX="path\to\javafx-sdk-15.0.1\lib" <br/>
<br/>
For Mac: <br/>
export PATH_TO_FX=path/to/javafx-sdk-15.0.1/lib

## Running
To run on Windows: <br/>
javac -cp .;mysql-connector-java-8.0.22.jar --module-path %PATH_TO_FX% --add-modules javafx.controls Interface.java <br/>
java -cp .;mysql-connector-java-8.0.22.jar --module-path %PATH_TO_FX% --add-modules javafx.controls Interface <br/>
<br/>
To run on Mac: <br/>
javac -cp .;mysql-connector-java-8.0.22.jar --module-path $PATH_TO_FX --add-modules javafx.controls Interface.java <br/>
java -cp .;mysql-connector-java-8.0.22.jar --module-path $PATH_TO_FX --add-modules javafx.controls Interface <br/>
