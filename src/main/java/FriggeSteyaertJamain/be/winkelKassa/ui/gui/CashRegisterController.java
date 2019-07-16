package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class CashRegisterController extends SubWindow {

    @FXML
    private Button dotBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private Button cBtn;

    @FXML
    private TableView<?> productLst;

    @FXML
    private Button upBtn;

    @FXML
    private Button downBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private Button SearchBtn;

    @FXML
    private GridPane hotkeys;

    @FXML
    private Button amountBtn;

    @FXML
    private Button scaleBtn;

    @FXML
    private Button priceBtn;

    @FXML
    private Button discountBtn;

    @FXML
    private Button btwBtn;

    @FXML
    private Button packageBTn;

    @FXML
    private Button cashBtn;

    @FXML
    private Button digitalBtn;

    @FXML
    private Button onHoldBtn;

    @FXML
    private Button openOnHoldBtn;

    @FXML
    private Button printTicketBtn;

    @FXML
    private Button emailTicket;

    @FXML
    private Button openDrawerBtn;

    @FXML
    void productSelectionDown(ActionEvent event) {

    }

    @FXML
    void productSelectionUp(ActionEvent event) {

    }

    @FXML
    void removeProduct(ActionEvent event) {

    }

    private void printTicket(){

    }

    private String generateTicket(){

        return null;
    }

    private void addProduct(Product p){

    }

    private Product getProduct(String barcode){
        return null;
    }

    private void storeTransaction(){

    }

    private void confirmTransaction(){

    }

}
