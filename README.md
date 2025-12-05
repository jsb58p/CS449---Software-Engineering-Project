# CS449---Software-Engineering-Project

Java Version: Java JDK-24

GUI: JavaFX 24.0.2

IDE: Eclipse Version: 2025-06 (4.36.0)

xUnit framework: JUnit 5.12.2

Programming Style: Google Style

Project Hosting Site: github.com

---

To add the JavaFX .jar files to the project:


Right click the project folder > Properties > select “Java Build Path” in the left-side pane > select “Modulepath” in the main window > Add External Jars > In the window that opens, navigate to “path\to\javafx-sdk-24.0.2\lib” > select all .jar files > Open > Apply 

--


To link the JavaFX library with the program using it:


Right click the program > Run as > Run Configurations > select the program in the left-side pane > Arguments tab > Under VM arguments enter: --module-path "path\to\javafx-sdk-24.0.2\lib" --add-modules javafx.controls,javafx.fxml > Apply   

--

(Change file paths to the javafx sdk lib folder.)


---

## Sprint 0:


The program source files SOSGameGUI.java and the UnitTest.java are located in:
- SOSJavaFX/src/sprint0/product
- SOSJavaFX/src/sprint0/test


## Screenshot:

## Unit Test:

![screenshot_sprint0](https://github.com/jsb58p/CS449---Software-Engineering-Project/blob/main/screenshots/screenshot_sprint0test.png)

## GUI:

![screenshot_sprint0](https://github.com/jsb58p/CS449---Software-Engineering-Project/blob/main/screenshots/screenshot_sprint0product.png)

---


## Sprint 4:

The program source files SOSGameGUI.java and the UnitTest.java are located in:
- SOSJavaFX/src/sprint4/product
- SOSJavaFX/src/sprint4/test

## Screenshot:


## GUI:

![screenshot_sprint0](https://github.com/jsb58p/CS449---Software-Engineering-Project/blob/main/screenshots/sosgame.png)

## Class Diagram:

![screenshot_sprint0](https://github.com/jsb58p/CS449---Software-Engineering-Project/blob/main/screenshots/Class%20Diagram.png)
