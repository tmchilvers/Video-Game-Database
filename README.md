# Video Game Database - MySQL/Java <br/>
Designed by Tristan Chilvers and Jonathan Bahm. <br/><br/>
An original, user friendly interface that connects to MySQL and functions as a easy way to manipulate a Video Game database.<br/>
It is built in a Java environment (JDBC driver) and the GUI is JavaFX dependent, so please ensure that Java is installed in your computer and read below how to install JavaFX. MySQL must also be installed as well.<br/><br/>
A PNG of the schema is available to help visualize the database.<br/>
The database offers essential, but basic functionality and serves as an educational exercise.<br/>

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
