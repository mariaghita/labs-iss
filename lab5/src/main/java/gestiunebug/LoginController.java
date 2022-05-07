package gestiunebug;

import gestiunebug.controller.ProgramatorController;
import gestiunebug.controller.VerificatorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import gestiunebug.repository.bugs.BugRepository;

import gestiunebug.repository.user.UserRepository;
import gestiunebug.service.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class LoginController {
    private Properties properties;
    private Service service;

    public LoginController(){
        Properties properties = new Properties();
        try{
            properties.load(new FileReader("bd.config"));
        }catch (IOException e){
            System.out.println("Cannot find bd.config: " + e);
        }
        this.properties = properties;
    }

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    @FXML
    Label wrongLoginLbl;

    public void setService(){
        UserRepository userRepository = new UserRepository(properties);
        BugRepository bugRepository = new BugRepository(properties);

        this.service = new Service(userRepository, bugRepository);
    }

    public void userLogin(javafx.event.ActionEvent actionEvent){
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())
            wrongLoginLbl.setText("Enter all your credentials!");
        else{
            try{
                String role = service.findExistenceOfUser(usernameField.getText(), passwordField.getText());
                if(Objects.equals(role, "programator"))
                    switchWindowForProgramator(actionEvent, usernameField.getText());
                else
                    switchWindowForVerificator(actionEvent, usernameField.getText());
            }catch(Exception e){
                //e.printStackTrace();
                wrongLoginLbl.setText(e.getMessage());
            }
        }
    }

    private void switchWindowForProgramator(javafx.event.ActionEvent actionEvent, String username) throws IOException{
        FXMLLoader newMenu = new FXMLLoader(Main.class.getResource("programator_view.fxml"));
        Scene newMenuScene = new Scene(newMenu.load());

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        ProgramatorController userController = newMenu.getController();
        userController.setProgramator(username);
        userController.setService();

        stage.setScene(newMenuScene);
        stage.show();

    }

    private void switchWindowForVerificator(ActionEvent actionEvent, String username) throws IOException{
        FXMLLoader newMenu = new FXMLLoader(Main.class.getResource("verificator_view.fxml"));
        Scene newMenuScene = new Scene(newMenu.load());

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        VerificatorController userController = newMenu.getController();
        userController.setVerificator(username);
        userController.setService();

        stage.setScene(newMenuScene);
        stage.show();
    }

}
