# CS449---Software-Engineering-Project
Java Version: Java JDK-24

GUI: JavaFX 24

IDE: Eclipse

---

To link the JavaFX library with the program using it:

Right click the program > Run > Run Configurations > select the program in the left-side pane > Under VM arguments enter:  --module-path "path\to\javafx-sdk-24.0.2\lib" --add-modules javafx.controls,javafx.fxml   

(Change file path as necessary to the javafx sdk lib folder.)
