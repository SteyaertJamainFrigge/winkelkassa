package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.data.db.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Btw;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ArticleManagementController extends SubWindow {

    @FXML
    private Label gridPosition;
    @FXML
    private HBox barcodeHbox;
    @FXML
    private GridPane inputGrid;
    @FXML
    private ComboBox unitComboBx;
    @FXML
    private Button editBtn;
    @FXML
    private TextField locationInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private Button PhotoBtn;
    @FXML
    private Button zoomInBtn;
    @FXML
    private ImageView articlePreviewImage;
    @FXML
    private Button databaseBtn;
    @FXML
    private TextField filterInput;
    @FXML
    private Button okBtn;
    @FXML
    private Button plusBtn;
    @FXML
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
    private TextField imageLocationInput;
    @FXML
    private TextField packageCodeInput;
    @FXML
    private Button openExplorerBtn;
    @FXML
    private Button barcodePrintBtn;

    public void initialize() {
        initializeSpinner();
        fillCategories();
        fillProductList();
        fillBtw();
        addProductListListener();
        this.productList.getSelectionModel().selectFirst();
    }

    private void initializeSpinner() {
        SpinnerValueFactory<Double> spinnerValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 2000.00);
        this.priceSpinner.setValueFactory(spinnerValueFactory);
    }

    private void addProductListListener() {
        this.productList.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        fillProductValues(newValue);
                    }
                });
    }

    private void fillProductList() {
        List<Product> products = Repositories.getInstance().getProductRepository().getAllProducts();
        ObservableList<Product> data = FXCollections.observableArrayList();
        data.addAll(products);
        FilteredList<Product> productFilteredList = new FilteredList<>(data, s -> true);
        filterInput.textProperty().addListener(obs -> {
            String filter = filterInput.getText();
            if (filter == null || filter.length() == 0) {
                productFilteredList.setPredicate(s -> true);
            } else {
                productFilteredList.setPredicate(s -> s.getName().contains(filter));
            }
        });
        this.productList.setItems(productFilteredList);
    }

    private void fillBtw() {
        List<Btw> btwTarifs = Repositories.getInstance().getBtwRepository().getAllBtw();
        this.btwComboBx.setItems(FXCollections.observableList(btwTarifs));
    }

    private void fillCategories() {
        List<ProductCategory> categories = Repositories.getInstance().getCategoryRepository().getAllCategories();
        this.categories = categories;
        this.categories.add(0,new ProductCategory(0, "zonder categorie"));
        this.categoryComboBx.setItems(FXCollections.observableList(categories));
    }

    private void fillProductValues(Product product) {
        enableDisableInputFields(false);
        this.nameInput.setText(product.getName());
        this.priceSpinner.getValueFactory().setValue(product.getPrice());
        this.descriptionInput.clear();
        this.descriptionInput.appendText(product.getDescription());
        this.locationInput.setText(product.getLocation());
        this.storeInput.setText(product.getStore());
        this.barcodeInput.setText(product.getBarcode());
        this.categoryComboBx.getSelectionModel().select(product.getCategory());
        this.btwComboBx.setValue(product.getBtw());
    }

    private ProductCategory findCategoryById(int id) {
        ProductCategory category = null;
        for (ProductCategory item :
                categories) {
            if (item.getId() == id) {
                category = item;
            }
        }
        return category;
    }

    private void clearProductValues() {
        enableDisableInputFields(true);
        this.nameInput.clear();
        this.priceSpinner.getValueFactory().setValue(0.00);
        this.descriptionInput.clear();
        this.locationInput.clear();
        this.storeInput.clear();
        this.barcodeInput.clear();
        this.categoryComboBx.getSelectionModel().selectFirst();
        this.btwComboBx.getSelectionModel().selectFirst();
    }

    private void saveChangesToObject(Product product) {
        product.setName(this.nameInput.getText());
        product.setPrice(this.priceSpinner.getValue());
        product.setBarcode(this.barcodeInput.getText());
        product.setCategory(this.categoryComboBx.getValue().getId());
        product.setDescription(this.descriptionInput.getText());
        product.setLocation(this.locationInput.getText());
        product.setStore(this.storeInput.getText());
        product.setBtw(this.btwComboBx.getValue());
    }

    private void savechangedObjectToDB(Product productToSave) {
        Repositories.getInstance().getProductRepository().updateProduct(productToSave);
    }

    private void addNewObjectToDb(Product productToSave) {
        Repositories.getInstance().getProductRepository().addProduct(productToSave);
    }

    @FXML
    private void save() {
        Product productToSave = this.productList.getSelectionModel().getSelectedItem();
        if (productToSave == null) {
            addNewObjectToDb(getNewProduct());
            fillProductList();
        } else {
            saveChangesToObject(productToSave);
            savechangedObjectToDB(productToSave);
        }
        enableDisableInputFields(false);
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

    @FXML
    private void enableInputFields() {
        enableDisableInputFields(true);
    }

    private void enableDisableInputFields(boolean bool) {
        ObservableList<Node> inputFields = this.inputGrid.getChildren();
        inputFields.forEach(node -> {
            if (node == this.barcodeHbox) {
                keepPrintBarcodeBtnEnabled(node, bool);
            } else {
                node.setDisable(!bool);
            }
        });

    }

    private void keepPrintBarcodeBtnEnabled(Node node, boolean bool) {
        HBox box = (HBox) node;
        box.getChildren().get(0).setDisable(!bool);
    }

    @FXML
    private void createProduct() {
        this.productList.getSelectionModel().clearSelection();
        clearProductValues();
    }

    private Product getNewProduct() {
        return new Product(
                0,
                this.nameInput.getText(),
                this.priceSpinner.getValue(),
                this.btwComboBx.getValue(),
                this.descriptionInput.getText(),
                this.locationInput.getText(),
                this.storeInput.getText(),
                this.barcodeInput.getText(),
                this.categoryComboBx.getValue().getId()
        );
    }
}

