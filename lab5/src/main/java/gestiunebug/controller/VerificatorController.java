package gestiunebug.controller;

import gestiunebug.model.Bug;
import gestiunebug.repository.bugs.BugRepository;
import gestiunebug.repository.user.UserRepository;
import gestiunebug.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class VerificatorController {
    private Service service;
    private Properties properties;

    @FXML
    Label verificatorNameLbl;

    @FXML
    TableView<Bug> bugsTable;

    @FXML
    TableColumn<String, Bug> nameColumn;

    @FXML
    TableColumn<String, Bug> statusColumn;
    public void setVerificator(String user){
        currentUser = user;
    }
    ObservableList<Bug> model = FXCollections.observableArrayList();
    List<Bug> allBugs;
    private String currentUser;

    public VerificatorController(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("bd.config"));
        } catch (IOException ex) {
            System.out.println("Cannot find bd.config: " + ex);
        }
        this.properties = properties;
    }

    public void setService(){
        UserRepository userRepository = new UserRepository(properties);
        BugRepository bugRepository = new BugRepository(properties);

        this.service = new Service(userRepository, bugRepository);
        setAllBugs();
        initModel();
    }
    @FXML
    private void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("denumire"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        bugsTable.setItems(model);
        verificatorNameLbl.setText(currentUser);
    }
    private void initModel(){
        model.setAll(allBugs);
    }
    private void setAllBugs(){allBugs = service.findAllBugsByVerificator(currentUser);}
}
