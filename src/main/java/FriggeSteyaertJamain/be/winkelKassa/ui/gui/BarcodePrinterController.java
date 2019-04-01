package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import FriggeSteyaertJamain.be.winkelKassa.domain.barcode.BarCode128Generator;

import java.io.FileNotFoundException;

public class BarcodePrinterController extends SubWindow {

    @FXML
    private Spinner<Integer> amountSpinner;

    private String barcode;

    public void initialize(){
        changeReturnBtnStyle();
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,200, 1);
        this.amountSpinner.setValueFactory(spinnerValueFactory);
    }

    private boolean validateAmountInput() {
        return amountSpinner.getValue() > 0;
    }

    private void showErrorPane(String input) {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setContentText(input);
        DialogPane dialogPane = al.getDialogPane();
        dialogPane.getStylesheets().add("/style/css/flatterfx.css");

        al.showAndWait();
    }

    private void printBarcode() {
        try {
            int amount = this.amountSpinner.getValue();
            BarCode128Generator bcg = new BarCode128Generator("A4", barcode, amount);
            bcg.generateBarCode128();
        } catch (FileNotFoundException e) {
            throw new KassaException("Unable to Find pdf File", e);
        } catch (DocumentException e) {
            throw new KassaException("An error occurred with the Document", e);
        }
    }

    @FXML
    void confirm(ActionEvent event) {
        if (validateAmountInput()) {
            printBarcode();
        } else {
            showErrorPane("foute input.");
        }
    }

    @FXML
    void returnToMain(ActionEvent event) {
        try {
            returnToMainScene();
        } catch (Exception e) {
            throw new KassaException("Unable to go back to main scene", e);
        }
    }
}
