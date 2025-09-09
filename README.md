# CS449---Software-Engineering-Project

Java Version: Java JDK-24

GUI: JavaFX 24.0.2

IDE: Eclipse

xUnit framework: JUnit

Programming Style: Google Style

Project Hosting Site: github.com

---

To add the JavaFX .jar files to the project:


Right click the project folder > Properties > select “Java Build Path” in the left-side pane > select “Modulepath” in the main window > Add External Jars > In the window that opens, navigate to “path\to\javafx-sdk-24.0.2\lib” > select all .jar files > Open > Apply 

--


To link the JavaFX library with the program using it:


Right click the program > Run > Run Configurations > select the program in the left-side pane > Under VM arguments enter: --module-path "path\to\javafx-sdk-24.0.2\lib" --add-modules javafx.controls,javafx.fxml   

--

(Change file paths to the javafx sdk lib folder.)


---

## Sprint 0:


The program source files SOSGameGUI.java and the UnitTest.java are located in:
- SOSJavaFX/src/sprint0/product
- SOSJavaFX/src/sprint0/test


Screenshot:

![screenshot_sprint0](https://github.com/jsb58p/CS449---Software-Engineering-Project/blob/main/screenshot_sprint0.png)
