package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.data.db.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Btw;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArticleManagementController extends SubWindow {

    @FXML
    private HBox categoryHbx, barcodeHbx;
    @FXML
    private Button addCategoryBtn, editBtn, PhotoBtn, zoomInBtn, databaseBtn, okBtn, plusBtn, openExplorerBtn, barcodePrintBtn;
    @FXML
    private TextField locationInput, filterInput, nameInput, storeInput, barcodeInput, imageLocationInput, packageCodeInput;
    @FXML
    private Label gridPosition;
    @FXML
    private GridPane inputGrid;
    @FXML
    private ComboBox unitComboBx;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private ImageView productPreviewImage;
    @FXML
    private List<ProductCategory> categories;
    @FXML
    private ListView<Product> productList;
    @FXML
    private Spinner<Double> priceSpinner;
    @FXML
    private ComboBox<ProductCategory> categoryComboBx;
    @FXML
    private ComboBox<Btw> btwComboBx;

    public void initialize() {
        initializeSpinner();
        fillCategories();
        fillProductList();
        fillBtw();
        addProductListListener();
        this.productList.getSelectionModel().selectFirst();
    }

    private void initializeSpinner() {
        SpinnerValueFactory<Double> spinnerValueFactory =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 2000.00);
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
        this.categories.add(0, new ProductCategory(0, "zonder categorie"));
        this.categoryComboBx.setItems(FXCollections.observableList(categories));
    }

    private void fillProductValues(Product product) {
        fillProductValues(product, false);
    }

    private void fillProductValues(Product product, boolean enableInputFields){
        enableDisableInputFields(enableInputFields);
        this.nameInput.setText(product.getName());
        this.priceSpinner.getValueFactory().setValue(product.getPrice());
        this.descriptionInput.clear();
        this.descriptionInput.appendText(product.getDescription());
        this.locationInput.setText(product.getLocation());
        this.storeInput.setText(product.getStore());
        this.barcodeInput.setText(product.getBarcode());
        this.categoryComboBx.getSelectionModel().select(findCategoryById(product.getCategory()));
        this.btwComboBx.setValue(product.getBtw());
        this.imageLocationInput.setText(product.getImageName());
        setImagePreview(product.getImage());
    }

    private void setImagePreview(Image imagePreview){
            this.productPreviewImage.setImage(imagePreview);
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
        product.setCategory(this.categoryComboBx.getSelectionModel().getSelectedItem().getId());
        product.setDescription(this.descriptionInput.getText());
        product.setLocation(this.locationInput.getText());
        product.setStore(this.storeInput.getText());
        product.setBtw(this.btwComboBx.getValue());
    }

    private void savechangedProductToDB(Product productToSave) {
        Repositories.getInstance().getProductRepository().updateProduct(productToSave);
    }

    private void addNewProductToDb(Product productToSave) {
        Repositories.getInstance().getProductRepository().addProduct(productToSave);
    }

    @FXML
    private void save() {
        Product productToSave = this.productList.getSelectionModel().getSelectedItem();
        if (productToSave == null) {
            addNewProductToDb(getNewProduct());
            fillProductList();
        } else {
            saveChangesToObject(productToSave);
            savechangedProductToDB(productToSave);
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
            if (node == this.barcodeHbx || node == this.categoryHbx) {
                keepHboxButtonEnabled(node, bool);
            } else {
                node.setDisable(!bool);
            }
        });

    }

    private void keepHboxButtonEnabled(Node node, boolean bool) {
        HBox box = (HBox) node;
        box.getChildren().get(0).setDisable(!bool);
    }

    @FXML
    private void createProduct() {
        this.productList.getSelectionModel().clearSelection();
        clearProductValues();
    }

    private Product getNewProduct() {
        return new Product.ProdcutBuilder(0)
                .setName(this.nameInput.getText())
                .setPrice(this.priceSpinner.getValue())
                .setBarcode(this.barcodeInput.getText())
                .setBtw(this.btwComboBx.getValue())
                .setDescription(this.descriptionInput.getText())
                .setLocation(this.locationInput.getText())
                .setStore(this.storeInput.getText())
                .setCategory(this.categoryComboBx.getValue().getId())
                .setImage(this.productPreviewImage.getImage())
                .setImageName(this.imageLocationInput.getText())
                .build();
    }

    @FXML
    private void chooseImage() {
        ImageLoader imageLoader = new ImageLoader(this.root.getScene().getWindow());
        HashMap<String, Image> result = imageLoader.getImage();
        if(result!=null){
            Map.Entry<String,Image> entry = result.entrySet().iterator().next();
            String key = entry.getKey();
            Image value = entry.getValue();
            addImageDataToProduct(key, value);
            fillProductValues(this.productList.getSelectionModel().getSelectedItem(), true);
        }
    }

    private void addImageDataToProduct(String key, Image value) {
        Product product = productList.getSelectionModel().getSelectedItem();
        product.setImageName(key);
        product.setImage(value);
        product.setImageId(Repositories.getInstance().getAfbeeldingRepository().getImageIdByName(key));
    }

    public void showAddProductCategoryDialog() {
        Dialog<Pair<String, ProductCategory>> dialog = new Dialog<>();
        dialog.setTitle("Nieuwe categorie");
        dialog.setHeaderText("Maak een nieuwe categorie");

        ButtonType confirmButtonType = new ButtonType("Bevestig", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        TextField categoryName = new TextField();
        categoryName.setPromptText("naam");
        ComboBox<ProductCategory> categoryComboBoxCopy = new ComboBox<>(categoryComboBx.getItems());
        categoryComboBoxCopy.getSelectionModel().selectFirst();

        Button button = (Button) dialog.getDialogPane().lookupButton(confirmButtonType);
        button.addEventFilter(ActionEvent.ACTION, event -> {
            if (validateCategory(categoryName.getText())) {
                event.consume();
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setContentText("deze categorienaam bestaat al");
                al.initOwner(this.root.getScene().getWindow());
                al.showAndWait();
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("naam categorie:"), 0, 0);
        grid.add(categoryName, 1, 0);
        grid.add(new Label("behoort tot:"), 0, 1);
        grid.add(categoryComboBoxCopy, 1, 1);

        Node confirmButton = dialog.getDialogPane().lookupButton(confirmButtonType);
        confirmButton.setDisable(true);

        categoryName.textProperty().addListener((observable, oldValue, newValue) ->
                confirmButton.setDisable(newValue.trim().isEmpty()));
        dialog.getDialogPane().setContent(grid);

        Platform.runLater(categoryName::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                return new Pair<>(categoryName.getText(), categoryComboBoxCopy.getSelectionModel().getSelectedItem());
            }
            return null;
        });

        Optional<Pair<String, ProductCategory>> result = dialog.showAndWait();
        result.ifPresent(this::saveProductCategory);
    }

    private void saveProductCategory(Pair<String, ProductCategory> stringProductCategoryPair) {
        ProductCategory pc =
                new ProductCategory(0, stringProductCategoryPair.getKey(), stringProductCategoryPair.getValue());
        addProductCategoryToDb(pc);
        addProductCategoryToComboBx(pc);
    }

    private boolean validateCategory(String categoryName) {
        for (ProductCategory category :
                this.categoryComboBx.getItems()) {
            if (categoryName.equals(category.getName())) {
                return true;
            }
        }
        return false;
    }

    private void addProductCategoryToDb(ProductCategory productCategory) {
        Repositories repo = Repositories.getInstance();
        repo.getCategoryRepository().addCategory(productCategory);
        if (productCategory.getParent().getId() != 0) {
            int parentCategoryId = productCategory.getParent().getId();
            int databaseGeneratedId = repo.getCategoryRepository().getCategoryByName(productCategory.getName());
            repo.getSubCategoryRepository().addSubcategory(parentCategoryId, databaseGeneratedId);
        }
    }

    private void addProductCategoryToComboBx(ProductCategory productCategory) {
        this.categoryComboBx.getItems().add(productCategory);
    }

    public void showLeeggoed() throws IOException {
        Stage stage = new Stage();
        stage.initOwner(this.root.getScene().getWindow());
        //TODO make leeggoed fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TODO"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

