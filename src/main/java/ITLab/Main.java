package ITLab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/main.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ITLab");
        stage.setMinWidth(1400);
        stage.setMinHeight(800);
        stage.setMaximized(true);
        //stage.getIcons().add(new Image("../resources/images/itlab.png"));
        //stage.getIcons().add(new Image(Main.class.getResourceAsStream("../resources/images/itlab.png\"")));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}