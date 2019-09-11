package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class MainController {
    public MenuBar menubar;
    public MenuItem quitItem;
    public MenuItem ClientItem;
    public MenuItem supliersItem;
    public MenuItem productItem;
    public MenuItem packagingItem;
    public MenuItem transactionsItem;
    public MenuItem usersItem;
    public MenuItem exportItem;
    public MenuItem optionsItem;
    public MenuItem action1Item;


    @FXML
    private Button showRegisterBtn;

    private Stage register;
    private Stage registerDisplay;


    @FXML
    void quit(ActionEvent event) {
        System.exit(0);
    }

    public void initialize() throws Exception{
        startRegister();
    }

    @FXML
    void ShowRegister(){
        register.show();
        registerDisplay.show();
        Stage stage = (Stage) showRegisterBtn.getScene().getWindow();
        stage.close();
    }

    private void startRegister() throws Exception{
        register = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/kassa.fxml"));
        Scene scene = new Scene(root);
        register.setScene(scene);
        register.setFullScreen(true);
        register.setFullScreenExitHint("");
        register.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        startRegisterDisplay();
    }

    private void startRegisterDisplay() throws IOException{
        registerDisplay = new Stage();
        Parent display = FXMLLoader.load(getClass().getResource("/fxml/kassadisplay.fxml"));
        Scene diplayScene = new Scene(display);
        registerDisplay.setScene(diplayScene);
        registerDisplay.initOwner(register);
    }

    private void startWindow(@NotNull Stage window, String resource,  boolean fullscreen) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource(resource));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/style/css/stylesheet.css");
            window.hide();
            window.setScene(scene);
            window.setFullScreen(fullscreen);
            window.show();
    }


    public void startClientWindow() throws IOException {
        Stage window = (Stage) this.menubar.getScene().getWindow();
        startWindow(window, "/fxml/KlantFiche.fxml", true);
    }

    public void startSuppliersScene() {
    }

    public void startProductWindow() throws IOException {
        Stage window = (Stage) this.menubar.getScene().getWindow();
         startWindow(window, "/fxml/articleManagement.fxml", false);
    }

    public void startPagagingScene() {
    }
}

