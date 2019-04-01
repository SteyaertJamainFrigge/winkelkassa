package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public abstract class SubWindow {

    @FXML
    protected Button returnBtn;

    void changeReturnBtnStyle(){
        // configure returnBtn
        returnBtn.getStyleClass().add("icon-button");
        returnBtn.setPickOnBounds(true);
        Region icon = new Region();
        icon.getStyleClass().add("icon");
        returnBtn.setGraphic(icon);
    }

    void returnToMainScene() throws Exception{
        Stage stage = (Stage) returnBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root);
        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.show();
    }

}
