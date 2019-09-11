package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

abstract class SubWindow {

    @FXML
    AnchorPane root;

    @FXML
    private void returnToMainScene(){
        Stage stage = (Stage) root.getScene().getWindow();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
            Scene scene = new Scene(root);
            stage.hide();
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setFullScreen(false);
            stage.centerOnScreen();
            stage.show();
        }catch (IOException e){
            throw new KassaException("Unable to return to main scene");
        }
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
