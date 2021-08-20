//Imports
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;

@SuppressWarnings("unused")

public class App extends Application {

    @FXML
    private Label title;
    @FXML
    private Button startButton;

    //Other Methods
    @Override
    public void start(Stage stage) throws Exception {
        //Variables
        //The SceneBuilder Graphic
        Pane mainPane = (Pane) FXMLLoader.load(Objects.requireNonNull(App.class.getResource("MENU.fxml")));
        stage.setScene(new Scene(mainPane));
        stage.show();
    }
}