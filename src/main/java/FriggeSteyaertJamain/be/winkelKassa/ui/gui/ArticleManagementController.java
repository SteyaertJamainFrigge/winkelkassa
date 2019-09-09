package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.data.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Btw;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleManagementController extends SubWindow {

    public TextField locationInput;
    public TextArea descriptionInput;
    public Button PhotoBtn;
    public Button zoomInBtn;
    public ImageView articlePreviewImage;
    public Button databaseBtn;
    public TextField filterInput;
    public Button optionsBtn;
    public Button filterBtn;
    public Button okBtn;
    public Button plusBtn;
    private int id;
    private List<ProductCategory> categories;
    @FXML
    private ListView<Product> productList;
    @FXML
    private TextField nameInput;
    @FXML
    private Spinner<Double> priceSpinner;
    @FXML
    private TextField storeInput;
    @FXML
    private TextField barcodeInput;
    @FXML
    private ComboBox<ProductCategory> categoryComboBx;
    @FXML
    private ComboBox<Btw> btwComboBx;
    @FXML
    private GridPane productInputs;

    @FXML
    void returnToMain() {
        try {
            returnToMainScene();
        } catch (Exception e) {
            throw new KassaException("Unable to go back to main scene", e);
        }
    }

    public void initialize() {
        initializeSpinner();
        fillCategories();
        fillProductList();
        fillBtw();
        addProductListListener();
        this.productList.getSelectionModel().selectFirst();
    }

    private void initializeSpinner(){
        SpinnerValueFactory<Double> spinnerValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00,2000.00);
        this.priceSpinner.setValueFactory(spinnerValueFactory);
    }

    private void addProductListListener(){
        this.productList.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> fillProductValues(newValue));
    }

    private void fillProductList(){
        List<Product> products = Repositories.getInstance().getProductRepository().getAllProducts();
        this.productList.setItems(FXCollections.observableList(products));
    }

    private void fillBtw(){
        List<Btw> btwTarifs = Repositories.getInstance().getBtwRepository().getAllBtw();
        this.btwComboBx.setItems(FXCollections.observableList(btwTarifs));
    }

    private void fillCategories(){
        List<ProductCategory> categories = Repositories.getInstance().getCategoryRepository().getAllCategories();
        this.categories = categories;
        this.categoryComboBx.setItems(FXCollections.observableList(categories));
    }

    private void fillProductValues(Product product){
        this.nameInput.setText(product.getName());
        //this.priceSpinner.getValueFactory().setValue(product.getPrice());
        this.descriptionInput.clear();
        this.descriptionInput.appendText(product.getDescription());
        this.locationInput.setText(product.getLocation());
        this.storeInput.setText(product.getStore());
        this.barcodeInput.setText(product.getBarcode());
        ProductCategory pc = findCategoryById(product.getCategory());
        this.categoryComboBx.setValue(pc);
        this.btwComboBx.setValue(product.getBtw());
        this.id = product.getId();
    }

    private ProductCategory findCategoryById(int id) {
        ProductCategory category = null;
        for (ProductCategory item:
                categories) {
            if(item.getId() == id){
                category = item;
            }
        }
        return category;
    }


    private void saveChangesToObject(Product product){
        product.setName(this.nameInput.getText());
        product.setPrice(this.priceSpinner.getValue());
        product.setBarcode(this.barcodeInput.getText());
        product.setCategory(this.categoryComboBx.getValue().getId());
        product.setDescription(this.descriptionInput.getText());
        product.setLocation(this.locationInput.getText());
        product.setStore(this.storeInput.getText());
        product.setBtw(this.btwComboBx.getValue());
    }

    private void savechangedObjectToDB(Product productToSave){
        Repositories.getInstance().getProductRepository().updateProduct(productToSave);
    }

    @FXML
    private void save(){
        Product productToSave = this.productList.getSelectionModel().getSelectedItem();
        saveChangesToObject(productToSave);
        savechangedObjectToDB(productToSave);
    }

    @FXML
    private void runBarcodePrinter() throws IOException {
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/barcodePrinter.fxml"));
        Parent root = loader.load();
        BarcodePrinterController bpc = loader.getController();
        bpc.iniData(this.productList.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(root);
        window.hide();
        window.setScene(scene);
        window.setMaximized(false);
        window.show();
    }

}

