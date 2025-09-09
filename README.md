# CS449---Software-Engineering-Project

Java Version: Java JDK-24

GUI: JavaFX 24.0.2

IDE: Eclipse

xUnit framework: JUnit

Programming Style: Google Style

Project Hosting Site: github.com

---

To link the JavaFX library with the program using it:

Right click the program > Run > Run Configurations > select the program in the left-side pane > Under VM arguments enter:  --module-path "path\to\javafx-sdk-24.0.2\lib" --add-modules javafx.controls,javafx.fxml   

(Change file path as necessary to the javafx sdk lib folder.)

---

## Sprint 1:


The program source files SOSGameGUI.java and the UnitTest.java are located in:
- SOSJavaFX/src/sprint1/product
-  SOSJavaFX/src/sprint1/test

Note: The UI does not scale with the window yet.
