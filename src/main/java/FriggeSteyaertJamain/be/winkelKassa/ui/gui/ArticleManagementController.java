package FriggeSteyaertJamain.be.winkelKassa.ui.gui;


import FriggeSteyaertJamain.be.winkelKassa.data.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class ArticleManagementController extends SubWindow {

    @FXML
    private ListView<Product> productList;

    @FXML
    private Button returnBtn;

    @FXML
    private TextField nameInput;

    @FXML
    private Spinner<Double> priceSpinner;

    @FXML
    private TextArea descriptionInput;

    @FXML
    private TextField locationInput;

    @FXML
    private TextField storeInput;

    @FXML
    private TextField barcodeInput;

    @FXML
    private ComboBox<ProductCategory> categoryComboBx;

    @FXML
    private ComboBox<Integer> btwComboBx;

    @FXML
    private GridPane productInputs;

    private ObservableList<Product> observableList;

    @FXML
    void returnToMain(ActionEvent event) {
        try {
            returnToMainScene();
        } catch (Exception e) {
            throw new KassaException("Unable to go back to main scene", e);
        }
    }

    public void initialize(){
        changeReturnBtnStyle();
        SpinnerValueFactory<Double> spinnerValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00,2000.00);
        this.priceSpinner.setValueFactory(spinnerValueFactory);
        fillProductList();

        this.productList.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> fillProductValues(newValue));
    }
    private void fillProductList(){
        List<Product> products = Repositories.getInstance().getProductRepository().getAllProducts();
        this.observableList = FXCollections.observableList(products);
        this.productList.setItems(this.observableList);
    }

    private void fillProductValues(Product product){
        this.nameInput.setText(product.getName());
        this.priceSpinner.getValueFactory().setValue(product.getPrice());
        this.descriptionInput.clear();
        this.descriptionInput.appendText(product.getDescription());
        this.locationInput.setText(product.getLocation());
        this.storeInput.setText(product.getStore());
        this.barcodeInput.setText(product.getBarcode());
    }

    @FXML
    private void runBarcodePrinter(){
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/"));
        Scene scene = new Scene(root);
        window.hide();
        window.setScene(scene);
        window.setMaximized(maximized);
        window.show();
    }


}

