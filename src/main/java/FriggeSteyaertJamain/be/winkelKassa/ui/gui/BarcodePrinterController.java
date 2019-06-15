package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.data.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import FriggeSteyaertJamain.be.winkelKassa.domain.barcode.BarCode128Generator;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BarcodePrinterController extends SubWindow {

    @FXML
    private Spinner<Integer> amountSpinner;

    @FXML
    private Label productName;

    @FXML
    private Label barcodeValue;

    @FXML
    private Button returnBtn;

    private int productid;

    public void initialize(){
        changeReturnBtnStyle();
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,200, 1);
        this.amountSpinner.setValueFactory(spinnerValueFactory);
    }

    void iniData(Product product){
        this.productName.setText(product.getName());
        this.barcodeValue.setText(product.getBarcode());
        this.productid = product.getId();
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
        al.showAndWait();
    }

    private void generateBarcode() {
        try {
            int amount = this.amountSpinner.getValue();
            BarCode128Generator bcg = new BarCode128Generator("A4", this.barcodeValue.getText(), amount);
            bcg.run();
        } catch (FileNotFoundException e) {
            showErrorPane("De pdf is in gebruik door een ander process");
            throw new KassaException("Unable to Find pdf File", e);
        } catch (DocumentException e) {
            throw new KassaException("An error occurred with the Document", e);
        }
    }

    private void openPDF(){
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("temp/pdf/bar128.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                throw new KassaException("can't open pdf", ex);
            }
        }
    }

    @FXML
    void confirm(ActionEvent event) {
        generateBarcode();
        openPDF();
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
