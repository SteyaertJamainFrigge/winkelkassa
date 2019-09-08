package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.data.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Purchase;
import FriggeSteyaertJamain.be.winkelKassa.ui.customComponents.CategoryButton;
import FriggeSteyaertJamain.be.winkelKassa.ui.customComponents.CategoryButtonList;
import FriggeSteyaertJamain.be.winkelKassa.ui.customComponents.ProductButton;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class KassaController {

    @FXML
    public Button scrollUpBtn;
    @FXML
    public Button scrollDownBTn;
    @FXML
    public Button homeBtn;
    @FXML
    public Button prevPageBtn;
    @FXML
    public Button findBtn;
    @FXML
    public Button nextPageBtn;
    @FXML
    public CategoryButton backBtn;
    @FXML
    private Button dotBtn;
    @FXML
    private Button backspaceBtn;
    @FXML
    private ToggleButton retourBtn;
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
    private ToggleGroup group;

    private ObservableList<Purchase> shoppingList;

    @FXML
    public void initialize() {
        addButtonIcons();
        createToggleGroup();
        createShoppingListTableColumns();
        shoppingList = FXCollections.observableArrayList();
        this.shoppingListTable.setItems(shoppingList);
        this.categoriesGrid.setGridLinesVisible(false);
        List<ProductCategory> categories = Repositories.getInstance().getCategoryRepository().getbaseCategories();
        List<Product> products = Repositories.getInstance().getProductRepository().getBaseProducts();
        fillProductAndCategoryGrid(categories, products, "base");
    }

    private void createShoppingListTableColumns() {
        TableColumn<Purchase, String> column1 = new TableColumn<>("Artikel");
        column1.setMinWidth(100);
        column1.setCellValueFactory(new PropertyValueFactory<>("article"));
        TableColumn<Purchase, Integer> column2 = new TableColumn<>("Aantal");
        column2.setMinWidth(100);
        column2.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn<Purchase, Double> column3 = new TableColumn<>("Prijs");
        column3.setMinWidth(100);
        column3.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Purchase, Double> column4 = new TableColumn<>("Korting");
        column4.setMinWidth(100);
        column4.setCellValueFactory(new PropertyValueFactory<>("discount"));
        TableColumn<Purchase, Integer> column5 = new TableColumn<>("BTW");
        column5.setMinWidth(100);
        column5.setCellValueFactory(new PropertyValueFactory<>("btw"));
        TableColumn<Purchase, Double> column6 = new TableColumn<>("Bedrag");
        column6.setMinWidth(100);
        column6.setCellValueFactory(new PropertyValueFactory<>("total"));
        this.shoppingListTable.getColumns().add(column1);
        this.shoppingListTable.getColumns().add(column2);
        this.shoppingListTable.getColumns().add(column3);
        this.shoppingListTable.getColumns().add(column4);
        this.shoppingListTable.getColumns().add(column5);
        this.shoppingListTable.getColumns().add(column6);
    }

    private void fillProductAndCategoryGrid(List<ProductCategory> categories, List<Product> products, String name) {

        List<CategoryButton> categoryButtons;
        List<ProductButton> productButtons;
        if(categories!=null){
            categoryButtons = makeCategoryButonList(categories, name);
        }else {
            categoryButtons = new ArrayList<>();
        }
        if(products!=null){
            productButtons = makeProdcutButtonList(products);
        }else {
            productButtons = new ArrayList<>();
        }
        addButtonsToCategoryGrid(categoryButtons, productButtons);
    }

    private void fillProductAndCategoryGrid(ProductCategory parent){
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
            button.setOnAction(this::addProductToShoppingListTable);
            buttons.add(button);
        }
        return buttons;
    }

    private void addProductToShoppingListTable(ActionEvent event) {
        ProductButton button = (ProductButton) event.getSource();
        Product product = button.getProduct();
        Integer index = containsProduct(product);
        if(index != null ){
            Purchase purchase = this.shoppingList.get(index);
            purchase.increment();
            this.shoppingList.set(index, purchase);
        }else {
            Purchase purchase = new Purchase(product);
            this.shoppingList.add(purchase);
        }
    }

    private Integer containsProduct(Product product){
        if(this.shoppingList.isEmpty()){
            return null;
        }
        for(int i = 0; i< this.shoppingList.size(); i++){
            Purchase purchase = this.shoppingList.get(i);
            if(purchase.getBarcode().equals(product.getBarcode())){
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


    private void createToggleGroup() {
        this.group = new ToggleGroup();
        this.amountBtn.setUserData("amount");
        this.scaleBtn.setUserData("scale");
        this.priceBTn.setUserData("price");
        this.discountBtn.setUserData("discount");
        this.VATBtn.setUserData("VAT");
        this.amountBtn.setToggleGroup(this.group);
        this.scaleBtn.setToggleGroup(this.group);
        this.priceBTn.setToggleGroup(this.group);
        this.discountBtn.setToggleGroup(this.group);
        this.VATBtn.setToggleGroup(this.group);
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

    private void add24x24ButtonIcons(){
        Button[][] buttons =
                {{this.deleteBtn},
                {}};
        String [][] filenames = {{"Trash"},{}};
        setButtonGraphics(buttons, filenames, "24");
    }

    private void add48x48ButtonIcons() {
        Button[][] buttons =
                {{this.payCashBtn,
                        this.payDigitalBtn,
                        this.pauzeTicketBtn,
                        this.pauzeTicketBtn,
                        this.recallTicketBtn,
                        this.openDrawerBtn,
                        this.printTicketBtn,
                        this.registerCashBtn,
                        this.mailTicketBtn,
                        this.scrollDownBTn,
                        this.scrollUpBtn,
                },{this.doubleZeroBtn,
                        this.confirmBtn,
                        this.backspaceBtn}};
        String[][] filenames =
                {{"Currency Euro", "Dots", "Player Pause", "Player Play", "Database", "Alarme", "Printer", "Mail", "Go In", "Arrow3 Down", "Arrow3 Up"}, {"0", "OK", "Arrow2 Left"}};

        setButtonGraphics(buttons, filenames, "48");
    }

    private void setButtonGraphics(Button[][] buttons, String[][] filenames, String res) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                String color;
                if (i == 0) {
                    color = "orange";
                } else {
                    color = "green";
                }
                Image image = new Image(getClass().getResourceAsStream("/images/" + color + "/"+res+"x"+res+"/" + filenames[i][j] + ".png"));
                Button button = buttons[i][j];
                button.setGraphic(new ImageView(image));
                button.setText("");
            }
        }
    }


    @FXML
    void Retour() {
        this.previewTxtField.setText("");
    }

    @FXML
    void addInteger(ActionEvent event) {
        String source = event.getSource().toString();
        int indexOf = source.indexOf("=");
        int lastIndexOf = source.indexOf(",");
        String id = source.substring(indexOf + 1, lastIndexOf);
        String text = previewTxtField.getText();
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
    void backspace() {
        String text = this.previewTxtField.getText();
        if (text != null && text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
        this.previewTxtField.setText(text);
    }

    @FXML
    void confirm(ActionEvent event) {
        //TODO confirm the input of the previewTxtfield
    }

    @FXML
    public void deletePurchase() {
        try{
            int index = this.shoppingListTable.getSelectionModel().getSelectedIndex();
            Purchase purchase = this.shoppingList.get(index);
            if(purchase.getAmount()==1){
                this.shoppingList.remove(index);
            }else {
                purchase.decrement();
                this.shoppingList.set(index, purchase);
            }
        }catch (KassaException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText(ex.getMessage());
            al.showAndWait();
        }
    }

    public void scrollDown(ActionEvent event) {
        this.shoppingListTable.getSelectionModel().selectNext();
    }

    public void scrollUP(ActionEvent event) {
        this.shoppingListTable.getSelectionModel().selectPrevious();
    }

    @FXML
    public void resetCategoryAndProductGrid() {
        List<ProductCategory> categories = Repositories.getInstance().getCategoryRepository().getbaseCategories();
        List<Product> products = Repositories.getInstance().getProductRepository().getBaseProducts();
        fillProductAndCategoryGrid(categories, products, "base");
        this.backBtn.setDisable(true);
    }

    @FXML
    public void showPreviousPage() {
        //TODO scroll up through the category grid
    }

    @FXML
    public void findCategoryOrProduct() {
        //TODO find any category or product out of all the categories and products and show that button in the grid
    }

    @FXML
    public void showNextPage() {
        //TODO scroll down through the category grid
    }

    @FXML
    public void ShowParentCategoryAndProductGrid(ActionEvent event) {
        CategoryButton clickedButton = (CategoryButton) event.getSource();
        ProductCategory parent = clickedButton.getCategory();
        if(parent == null){
            resetCategoryAndProductGrid();
        }else {
            clickedButton.setCategory(parent.getParent());
            fillProductAndCategoryGrid(parent);
        }
    }
}
