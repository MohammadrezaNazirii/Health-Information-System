package HIS;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinWidth(400);
        stage.setMinHeight(700);
        stage.setTitle("HIS");
        Scenes.LoginScene(stage);
        stage.show();

    }
}
