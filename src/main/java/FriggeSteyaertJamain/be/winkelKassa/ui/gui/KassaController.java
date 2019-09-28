package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.data.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Purchase;
import FriggeSteyaertJamain.be.winkelKassa.ui.customComponents.CategoryButton;
import FriggeSteyaertJamain.be.winkelKassa.ui.customComponents.CategoryButtonList;
import FriggeSteyaertJamain.be.winkelKassa.ui.customComponents.ProductButton;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class KassaController extends SubWindow {

    @FXML
    private ToggleGroup numpadAction;
    @FXML
    private GridPane numpadGrid;
    @FXML
    private Button scrollUpBtn;
    @FXML
    private Button scrollDownBTn;
    @FXML
    private Button homeBtn;
    @FXML
    private Button prevPageBtn;
    @FXML
    private Button findBtn;
    @FXML
    private Button nextPageBtn;
    @FXML
    public CategoryButton backBtn;
    @FXML
    private Label totalLbl;
    @FXML
    private Label cashLbl;
    @FXML
    private Label changeLbl;
    @FXML
    private Button returnBtn;
    @FXML
    private Button retourBtn;
    @FXML
    private Button dotBtn;
    @FXML
    private Button backspaceBtn;
    @FXML
    private Button zeroBtn;
    @FXML
    private Button threeBtn;
    @FXML
    private Button twoBtn;
    @FXML
    private Button oneBtn;
    @FXML
    private Button fourBtn;
    @FXML
    private Button fiveBtn;
    @FXML
    private Button sixBtn;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button nineBtn;
    @FXML
    private Button eightBtn;
    @FXML
    private Button doubleZeroBtn;
    @FXML
    private Button sevenBtn;
    @FXML
    private Button packagingBtn;
    @FXML
    private ToggleButton amountBtn;
    @FXML
    private ToggleButton scaleBtn;
    @FXML
    private ToggleButton priceBTn;
    @FXML
    private ToggleButton discountBtn;
    @FXML
    private ToggleButton VATBtn;
    @FXML
    private TextField previewTxtField;
    @FXML
    private Button payCashBtn;
    @FXML
    private Button payDigitalBtn;
    @FXML
    private Button pauzeTicketBtn;
    @FXML
    private Button resumeTicketBtn;
    @FXML
    private Button printTicketBtn;
    @FXML
    private Button openDrawerBtn;
    @FXML
    private Button recallTicketBtn;
    @FXML
    private Button registerCashBtn;
    @FXML
    private Button mailTicketBtn;
    @FXML
    private GridPane categoriesGrid;
    @FXML
    private TableView<Purchase> shoppingListTable;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button searchClientBtn;
    @FXML
    private Button gridEditorBtn;

    private ObservableList<Purchase> shoppingList;
    private boolean scaleCanWriteToPreview = false;
    private ObservableList<Purchase> pauzedShoppingList;

    @FXML
    private void initialize() {
        this.totalLbl.setText("0,00");
        setupSerialPort();
        addButtonIcons();
        createShoppingListTableColumns();
        initializeShoppnigList();
        removeCategoryGridLines();
        addBaseProductAndCategoryGrid();
        setupNumpadToggleGroup();
    }

    private void setupNumpadToggleGroup() {
        this.numpadAction = new ToggleGroup();
        this.amountBtn.setUserData("amount");
        this.discountBtn.setUserData("discount");
        this.scaleBtn.setUserData("scale");
        this.priceBTn.setUserData("price");
        this.numpadAction.getToggles().addAll(this.amountBtn, this.scaleBtn, this.discountBtn, this.priceBTn);
    }

    private void removeCategoryGridLines() {
        this.categoriesGrid.setGridLinesVisible(false);
    }

    private void initializeShoppnigList() {
        shoppingList = FXCollections.observableArrayList();
        this.shoppingListTable.setItems(shoppingList);
    }

    private void addBaseProductAndCategoryGrid() {
        List<ProductCategory> categories = Repositories.getInstance().getCategoryRepository().getbaseCategories();
        List<Product> products = Repositories.getInstance().getProductRepository().getBaseProducts();
        fillProductAndCategoryGrid(categories, products, "base");
    }

    private void setupSerialPort() {
        if(SerialPort.getCommPorts().length > 1){
            SerialPort commPort = SerialPort.getCommPorts()[1];
            commPort.openPort();
            commPort.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
                }

                @Override
                public void serialEvent(SerialPortEvent event) {
                    byte[] newData = event.getReceivedData();
                    if (newData.length == 20) {
                        StringBuilder stringData = new StringBuilder();
                        for (byte b : newData) {
                            char c = (char) b;
                            if(Character.isDigit(c)){
                                stringData.append(c);
                            }
                        }
                        if(scaleCanWriteToPreview) previewTxtField.setText(stringData.toString());
                    }
                }
            });
        }
    }

    private void createShoppingListTableColumns() {
        String[] columnNames = {"Artikel", "Aantal", "Prijs", "Korting", "BTW", "Bedrag"};
        String[] cellValue = {"article", "amount", "price", "discount", "btw", "total"};
        for(int i = 0; i<columnNames.length; i++){
            TableColumn<Purchase, String> column = new TableColumn<>(columnNames[i]);
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<>(cellValue[i]));
            this.shoppingListTable.getColumns().add(column);
        }
    }

    private void fillProductAndCategoryGrid(List<ProductCategory> categories, List<Product> products, String name) {
        List<CategoryButton> categoryButtons;
        List<ProductButton> productButtons;
        if (categories != null) {
            categoryButtons = makeCategoryButonList(categories, name);
        } else {
            categoryButtons = new ArrayList<>();
        }
        if (products != null) {
            productButtons = makeProdcutButtonList(products);
        } else {
            productButtons = new ArrayList<>();
        }
        addButtonsToCategoryGrid(categoryButtons, productButtons);
    }

    private void fillProductAndCategoryGrid(ProductCategory parent) {
        fillProductAndCategoryGrid(parent.getSubCategories(), parent.getProducts(), parent.getName());
    }

    private void addButtonsToCategoryGrid(List<CategoryButton> categoryButtons, List<ProductButton> productButtons) {
        int y = 0;
        int categoryIndex = 0;
        int productIndex = 0;
        while (y < 6) {
            int x = 0;
            while (x < 4) {
                if (categoryIndex < categoryButtons.size()) {
                    this.categoriesGrid.add(categoryButtons.get(categoryIndex), x, y);
                    categoryIndex++;
                } else {
                    if (productIndex < productButtons.size()) {
                        this.categoriesGrid.add(productButtons.get(productIndex), x, y);
                        productIndex++;
                    }
                }
                x++;
            }
            y++;
        }
    }

    private List<ProductButton> makeProdcutButtonList(List<Product> products) {
        List<ProductButton> buttons = new ArrayList<>();
        for (Product product : products) {
            ProductButton button = new ProductButton(product);
            button.setMaxWidth(1.79E308);
            button.setMaxHeight(1.79E308);
            button.setMnemonicParsing(false);
            button.setOnAction(this::addPurchase);
            buttons.add(button);
        }
        return buttons;
    }

    private void addPurchase(ActionEvent event) {
        ProductButton button = (ProductButton) event.getSource();
        Product product = button.getProduct();
        Integer index = containsProduct(product);
        if (index != null) {
            Purchase purchase = this.shoppingList.get(index);
            purchase.increment();
            this.shoppingList.set(index, purchase);
            updateTotalPrice();
        } else {
            Purchase purchase = new Purchase(product);
            this.shoppingList.add(purchase);
            updateTotalPrice();
        }
    }

    private Integer containsProduct(Product product) {
        if (this.shoppingList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < this.shoppingList.size(); i++) {
            Purchase purchase = this.shoppingList.get(i);
            if (purchase.getBarcode().equals(product.getBarcode())) {
                return i;
            }
        }
        return null;
    }

    private List<CategoryButton> makeCategoryButonList(List<ProductCategory> categories, String listName) {
        CategoryButtonList<CategoryButton> buttons = new CategoryButtonList<>(listName);
        for (ProductCategory category : categories) {
            CategoryButton button = new CategoryButton(category);
            button.setMaxWidth(1.79E308);
            button.setMaxHeight(1.79E308);
            button.setMnemonicParsing(false);
            button.setOnAction(this::showSubCategoriesAndProducts);
            buttons.add(button);
        }
        return buttons;
    }

    private void showSubCategoriesAndProducts(ActionEvent event) {
        clearCategoryGrid();
        CategoryButton button = (CategoryButton) event.getSource();
        fillProductAndCategoryGrid(button.getSubCategories(), button.getProducts(), button.getName());
        this.backBtn.setCategory(button.getCategory().getParent());
    }

    private void clearCategoryGrid() {
        this.categoriesGrid.getChildren().clear();
    }

    private void addButtonIcons() {
        addNumpadButtonIcons();
        add48x48ButtonIcons();
        add24x24ButtonIcons();
    }

    private void addNumpadButtonIcons() {
        Button[] buttons = {this.zeroBtn, this.oneBtn, this.twoBtn, this.threeBtn, this.fourBtn, this.fiveBtn, this.sixBtn, this.sevenBtn, this.eightBtn, this.nineBtn};
        for (int i = 0; i < 10; i++) {
            String location = "/images/green/48x48/" + i + ".png";
            Image image = new Image(getClass().getResourceAsStream(location));
            Button button = buttons[i];
            button.setGraphic(new ImageView(image));
            button.setText("");
        }
    }

    private void add24x24ButtonIcons() {
        Map<Button, String> orangeButtonHashMap = Map.ofEntries(
                entry(this.deleteBtn, "Trash"),
                entry(this.gridEditorBtn, "Tool"),
                entry(this.returnBtn, "Cancel")
        );
        setButtonGraphics(orangeButtonHashMap, "24", "orange");
    }

    private void add48x48ButtonIcons() {
        Map<Button, String> orangeButtonHashMap = Map.ofEntries(
                entry(this.payCashBtn,"Currency Euro"),
                entry(this.payDigitalBtn,"Dots"),
                entry(this.pauzeTicketBtn, "Player Pause"),
                entry(this.resumeTicketBtn,"Player Play"),
                entry(this.recallTicketBtn, "Database"),
                entry(this.openDrawerBtn, "Alarme"),
                entry(this.printTicketBtn, "Printer"),
                entry(this.mailTicketBtn, "Mail"),
                entry(this.scrollDownBTn, "Arrow3 Down"),
                entry(this.scrollUpBtn, "Arrow3 Up"),
                entry(this.homeBtn,"Home"),
                entry(this.searchClientBtn, "Search"),
                entry(this.findBtn, "Search"),
                entry(this.backBtn, "Back"),
                entry(this.prevPageBtn, "Arrow3 Up"),
                entry(this.nextPageBtn, "Arrow3 Down")
                );
        Map<Button, String> greenButtonHashMap = Map.ofEntries(
                entry(this.doubleZeroBtn, "0"),
                entry(this.confirmBtn, "OK"),
                entry(this.backspaceBtn, "Arrow2 Left")
        );
        setButtonGraphics(orangeButtonHashMap, "48", "orange");
        setButtonGraphics(greenButtonHashMap, "48", "green");
    }

    private void setButtonGraphics(Map<Button, String> buttonMap, String res, String color){
        buttonMap.forEach((key, value) ->{
            Image image = new Image(getClass().getResourceAsStream("/images/"+ color +"/" + res + "x" + res + "/" + value + ".png"));
            key.setGraphic(new ImageView(image));
            key.setText("");
            key.getStyleClass().add("iconButton");
        });

    }

    @FXML
    private void retour() {
        this.previewTxtField.setText("");
    }

    @FXML
    private void addInteger(ActionEvent event) {
        String source = event.getSource().toString();
        int indexOf = source.indexOf("=");
        int lastIndexOf = source.indexOf(",");
        String id = source.substring(indexOf + 1, lastIndexOf);
        String text = this.previewTxtField.getText();
        switch (id) {
            case "doubleZeroBtn":
                text += "00";
                break;
            case "zeroBtn":
                text += "0";
                break;
            case "oneBtn":
                text += "1";
                break;
            case "twoBtn":
                text += "2";
                break;
            case "threeBtn":
                text += "3";
                break;
            case "fourBtn":
                text += "4";
                break;
            case "fiveBtn":
                text += "5";
                break;
            case "sixBtn":
                text += "6";
                break;
            case "sevenBtn":
                text += "7";
                break;
            case "eightBtn":
                text += "8";
                break;
            case "nineBtn":
                text += "9";
                break;
            case "dotBtn":
                text += ".";
                break;
        }
        previewTxtField.setText(text);
    }

    @FXML
    private void backspace() {
        String text = this.previewTxtField.getText();
        if (text != null && text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
        this.previewTxtField.setText(text);
    }

    @FXML
    void confirm() {
        try {
            String action = (String) this.numpadAction.getSelectedToggle().getUserData();
            switch (action) {
                case "amount":
                    try{
                        int selectedIndex = this.shoppingListTable.getSelectionModel().getSelectedIndex();
                        Purchase purchase = this.shoppingList.get(selectedIndex);
                        purchase.setAmount(Integer.parseInt(this.previewTxtField.getText()));
                        this.shoppingListTable.refresh();
                    }catch (NullPointerException ex){
                        Alert al = new Alert(Alert.AlertType.ERROR);
                        al.setContentText("selecteer een optie");
                        al.initOwner(this.root.getScene().getWindow());
                        al.showAndWait();
                    }
                    break;
                case "scale":

                    break;
                default:
                    //de nothing
                    break;
            }
            updateTotalPrice();
            retour();
        } catch (NullPointerException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("selecteer een optie");
            al.initOwner(this.root.getScene().getWindow());
            al.showAndWait();
        }

    }

    @FXML
    private void deletePurchase() {
        int index = this.shoppingListTable.getSelectionModel().getSelectedIndex();
        try {
            Purchase purchase = this.shoppingList.get(index);
            if (purchase.getAmount() == 1) {
                this.shoppingList.remove(index);
                updateTotalPrice();
            } else {
                purchase.decrement();
                this.shoppingListTable.refresh();
                updateTotalPrice();
            }
        } catch (IndexOutOfBoundsException e) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("selecteer een product om te verwijderen");
            al.initOwner(this.root.getScene().getWindow());
            al.showAndWait();
        }
    }

    @FXML
    private void scrollDown() {
        this.shoppingListTable.getSelectionModel().selectNext();
    }

    @FXML
    private void scrollUP() {
        this.shoppingListTable.getSelectionModel().selectPrevious();
    }

    @FXML
    private void resetCategoryAndProductGrid() {
        List<ProductCategory> categories = Repositories.getInstance().getCategoryRepository().getbaseCategories();
        List<Product> products = Repositories.getInstance().getProductRepository().getBaseProducts();
        fillProductAndCategoryGrid(categories, products, "base");
        this.backBtn.setDisable(true);
    }

    @FXML
    private void showPreviousPage() {
        //TODO scroll up through the category grid
    }

    @FXML
    private void findCategoryOrProduct() {
        //TODO find any category or product out of all the categories and products and show that button in the grid
    }

    @FXML
    private void showNextPage() {
        //TODO scroll down through the category grid
    }

    @FXML
    private void ShowParentCategoryAndProductGrid(ActionEvent event) {
        CategoryButton clickedButton = (CategoryButton) event.getSource();
        ProductCategory parent = clickedButton.getCategory();
        if (parent == null) {
            resetCategoryAndProductGrid();
        } else {
            clickedButton.setCategory(parent.getParent());
            fillProductAndCategoryGrid(parent);
        }
    }

    private void updateTotalPrice() {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        double totalPrice = 00.00;
        for (Purchase purchase :
                this.shoppingList) {
            totalPrice += purchase.getTotal();
        }
        this.totalLbl.setText(df.format(totalPrice));
    }

    @FXML
    private void startClientWindow() throws IOException {
        startWindow("/fxml/KlantFiche.fxml");
    }

    @FXML
    private void startGridregisterWindow() throws IOException {
        startWindow("/fxml/gridkassa.fxml");
    }

    private void startWindow(String resource) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(resource));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/style/css/stylesheet.css");
        stage.setScene(scene);
        stage.initOwner(this.root.getScene().getWindow());
        stage.setFullScreen(false);
        stage.show();
    }

    @FXML
    private void confirmDigitalPayment() {

    }

    @FXML
    private void confirmCashPayment() {

    }

    @FXML
    private void updateCommPort(){
        this.previewTxtField.setText("");
        if(this.scaleBtn.isSelected()){
            this.scaleCanWriteToPreview = true;
            this.numpadGrid.setDisable(true);
        }else {
            this.scaleCanWriteToPreview = false;
            this.numpadGrid.setDisable(false);
        }
    }

    @FXML
    private void pauzeTicket() {
         pauzedShoppingList = FXCollections.observableArrayList(shoppingListTable.getItems());
         shoppingList.clear();
    }

    @FXML
    private void resumeTicket() {
        if(shoppingList.size()>0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Onbetaalde artikelen verwijderen?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                showPauzedTicket();
            }
        }else {
            showPauzedTicket();
        }
    }

    private void showPauzedTicket() {
        shoppingList.clear();
        shoppingListTable.getItems().addAll(pauzedShoppingList);
    }
}
