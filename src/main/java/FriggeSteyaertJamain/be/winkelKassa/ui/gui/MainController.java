package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class MainController {

    @FXML
    private AnchorPane barcodeView;

    @FXML
    private void runBarcodePrinter(){
        Stage stage = (Stage) barcodeView.getScene().getWindow();
        try {
            startWindow(stage, "/fxml/barcodePrinter.fxml", false);
        } catch (Exception e) {
            throw new KassaException("Unable to run new Window", e);
        }
    }

    @FXML
    void runCashRegister() {
        /*Stage stage = (Stage) barcodeView.getScene().getWindow();
        try {
            startWindow(stage, "/fxml/TODO.fxml");
        } catch (Exception e) {
            throw new KassaException("Unable to run new Window", e);
        }
        */
        System.out.println("run cash register");

    }

    @FXML
    void runArticleMgmt(){
        Stage stage = (Stage) barcodeView.getScene().getWindow();
        try {
            startWindow(stage, "/fxml/articleManagement.fxml", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("run article management");
    }

    private void startWindow(@NotNull Stage window, String resource, boolean maximized) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(resource));
        Scene scene = new Scene(root);
        window.hide();
        window.setScene(scene);
        window.setMaximized(maximized);
        window.show();
    }

}

