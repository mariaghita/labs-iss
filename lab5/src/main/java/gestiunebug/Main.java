package gestiunebug;

import gestiunebug.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader menu = new FXMLLoader(getClass().getResource("login.fxml"));

        Scene scene = new Scene(menu.load());
        stage.setScene(scene);

        LoginController loginController = menu.getController();
        loginController.setService();

        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
