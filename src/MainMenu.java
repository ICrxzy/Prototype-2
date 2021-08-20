import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jdk.jfr.internal.tool.Main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenu extends Application implements Initializable  {
    @FXML
    private Label title;
    @FXML
    private Button startButton;

    public void startGame(String[] args){
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    void onStartClicked(ActionEvent e){
        new Game();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane mainPane = (Pane) FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("MENU.fxml")));
        stage.setScene(new Scene(mainPane));
        stage.show();
    }
}
