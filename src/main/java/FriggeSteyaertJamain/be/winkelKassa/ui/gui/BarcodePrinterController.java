package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.data.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import FriggeSteyaertJamain.be.winkelKassa.domain.barcode.BarCode128Generator;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class BarcodePrinterController extends SubWindow {

    @FXML
    private Spinner<Integer> amountSpinner;

    private String barcode;

    private int productid;

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public void initialize(){
        changeReturnBtnStyle();
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,200, 1);
        this.amountSpinner.setValueFactory(spinnerValueFactory);
    }

    private boolean validateAmountInput() {
        return amountSpinner.getValue() > 0;
    }

    private boolean validateProductInput(){
        Repositories.getInstance().getProductRepository().getProduct(productid);
        return true;
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
            bcg.run();
        } catch (FileNotFoundException e) {
            throw new KassaException("Unable to Find pdf File", e);
        } catch (DocumentException e) {
            throw new KassaException("An error occurred with the Document", e);
        }
    }

    @FXML
    void confirm(ActionEvent event) {
        if (!validateProductInput()){
            showErrorPane("product bestaat niet");
        }else {
            if (!validateAmountInput()) {
                showErrorPane("aantal niet aanvaard input.");
            } else {
                printBarcode();
            }
        }
    }

    @FXML
    void returnToMain(ActionEvent event) {
        try {
            Stage stage = (Stage) returnBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            throw new KassaException("Unable to go back to main scene", e);
        }
    }
}
