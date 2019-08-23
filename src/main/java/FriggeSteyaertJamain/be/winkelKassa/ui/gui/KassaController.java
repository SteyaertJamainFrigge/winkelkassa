package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.data.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Product;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.ProductCategory;
import FriggeSteyaertJamain.be.winkelKassa.ui.customComponents.CategoryButton;
import FriggeSteyaertJamain.be.winkelKassa.ui.customComponents.ProductButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KassaController {

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

    private ToggleGroup group;

    @FXML
    public void initialize() {
        addButtonIcons();
        createToggleGroup();
        fillProductAndCategoryGrid(null);
    }

    private void fillProductAndCategoryGrid(List<ProductCategory> categories) {
        List<CategoryButton> categoryButtons;
        List<ProductButton> productButtons;
        if (categories == null) {
            List<ProductCategory> baseCategories = Repositories.getInstance().getCategoryRepository().getbaseCategories();
            categoryButtons = makeCategoryButonList(baseCategories);
            productButtons = makeProdcutButtonList(baseCategories);
        } else {
            categoryButtons = makeCategoryButonList(categories);
            productButtons = makeProdcutButtonList(categories);
        }

        addButtonsToCategoryGrid(categoryButtons, productButtons);

        //int lastfilledIndex = addCategoryButtons(categoryButtons);
        //addProductButtons(productButtons, lastfilledIndex);
    }

    private void addButtonsToCategoryGrid(List<CategoryButton> categoryButtons, List<ProductButton> productButtons) {
        int x =0;
        int y =0;
        int categoryIndex = 0;
        int productIndex= 0;

        while(y<6){
            while(x<4) {
                if (categoryIndex < categoryButtons.size()) {
                    categoriesGrid.add(categoryButtons.get(categoryIndex), x, y);
                    categoryIndex++;
                }else if (productIndex < productButtons.size()) {
                    categoriesGrid.add(productButtons.get(productIndex), x, y);
                    productIndex++;
                }
                x++;
            }
            y++;
        }
    }

    private void addProductButtons(List<ProductButton> productButtons, int xIndex, int yIndex) {
        int index = 0;
        outerloop:
        for(int i= index%4; i< 6; i++){
            for(int j= index%6; j<4; j++){
                if(index + index == 34 || index == productButtons.size()){
                    break outerloop;
                }
                categoriesGrid.add(productButtons.get(index), j, i);
                index++;
            }
        }
    }

    private int addCategoryButtons(List<CategoryButton> categoryButtons) {
        int index = 0;
        outerloop:
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (index == 34 || index == categoryButtons.size()) {
                    break outerloop;
                }
                categoriesGrid.add(categoryButtons.get(index), j, i);
                index++;
            }
        }
        return index;
    }

    private List<ProductButton> makeProdcutButtonList(List<ProductCategory> categories) {
        List<ProductButton> buttons = new ArrayList<>();
        for (ProductCategory c: categories) {
            for (Product p :
                    c.getProducts()) {
                ProductButton button = new ProductButton(p);
                button.setMaxWidth(1.79E308);
                button.setMaxHeight(1.79E308);
                button.setMnemonicParsing(false);
                buttons.add(button);
            }
        }
        return buttons;
    }

    private List<CategoryButton> makeCategoryButonList(List<ProductCategory> categories) {
        List<CategoryButton> buttons = new ArrayList<>();
        for (ProductCategory category : categories) {
            CategoryButton button = new CategoryButton(category);
            button.setMaxWidth(1.79E308);
            button.setMaxHeight(1.79E308);
            button.setMnemonicParsing(false);
            buttons.add(button);
        }
        return buttons;
    }


    private void createToggleGroup() {
        group = new ToggleGroup();
        amountBtn.setUserData("amount");
        scaleBtn.setUserData("scale");
        priceBTn.setUserData("price");
        discountBtn.setUserData("discount");
        VATBtn.setUserData("VAT");
        amountBtn.setToggleGroup(group);
        scaleBtn.setToggleGroup(group);
        priceBTn.setToggleGroup(group);
        discountBtn.setToggleGroup(group);
        VATBtn.setToggleGroup(group);
    }

    private void addButtonIcons() {
        addNumpadButtonIcons();
        addMoreButtonIcons();
    }

    private void addNumpadButtonIcons() {
        Button[] buttons = {zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn};
        for (int i = 0; i < 10; i++) {
            String location = "/images/green/48x48/" + i + ".png";
            Image image = new Image(getClass().getResourceAsStream(location));
            Button button = buttons[i];
            button.setGraphic(new ImageView(image));
            button.setText("");
        }
    }

    private void addMoreButtonIcons() {
        Button[][] buttons =
                {{
                        payCashBtn,
                        payDigitalBtn,
                        pauzeTicketBtn,
                        pauzeTicketBtn,
                        recallTicketBtn,
                        openDrawerBtn,
                        printTicketBtn,
                        registerCashBtn,
                        mailTicketBtn
                }, {
                        doubleZeroBtn,
                        confirmBtn,
                        backspaceBtn,
                }};

        String[][] filenames =
                {{"Currency Euro", "Dots", "Player Pause", "Player Play", "Database", "Alarme", "Printer", "Mail", "Go In"}, {"0", "OK", "Arrow2 Left"}};


        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                String color;
                if (i == 0) {
                    color = "orange";
                } else {
                    color = "green";
                }
                Image image = new Image(getClass().getResourceAsStream("/images/" + color + "/48x48/" + filenames[i][j] + ".png"));
                Button button = buttons[i][j];
                button.setGraphic(new ImageView(image));
                button.setText("");
            }
        }
    }

    @FXML
    void Retour(ActionEvent event) {
        previewTxtField.setText("");
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
    void backspace(ActionEvent event) {
        String text = previewTxtField.getText();
        if (text != null && text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
        previewTxtField.setText(text);
    }

    @FXML
    void confirm(ActionEvent event) {
        //TODO confirm the input of the previewTxtfield
    }
}
