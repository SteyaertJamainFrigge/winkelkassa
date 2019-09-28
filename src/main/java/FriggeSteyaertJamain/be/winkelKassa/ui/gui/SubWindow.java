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
    private Stage stage;

    @FXML
    private void returnToParentScene(){
        stage = (Stage) root.getScene().getWindow();
        if(stage.getOwner() == null) returnToMainScene();
        else closeWindow();
    }

    @FXML
    private void returnToMainScene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
            Scene scene = new Scene(root);
            this.stage.hide();
            this.stage.setScene(scene);
            this.stage.setMaximized(false);
            this.stage.setFullScreen(false);
            this.stage.centerOnScreen();
            this.stage.show();
        }catch (IOException e){
            throw new KassaException("Unable to return to main scene");
        }
    }

    @FXML
    private void closeWindow(){
        this.stage.close();
    }

}
