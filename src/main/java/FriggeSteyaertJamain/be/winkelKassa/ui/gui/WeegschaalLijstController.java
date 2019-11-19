package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public class WeegschaalLijstController extends SubWindow {

    @FXML
    private AnchorPane imageViewContainer;
    @FXML
    private ListView<?> imageListView;

    @FXML
    private ImageView imagePreview;

    public void initialize() {
        //fillImageListView();
        configureImagePreview();
    }

    private void configureImagePreview(){
        //imagePreview.fitWidthProperty().bind(imageViewContainer.widthProperty());
        Image image = new Image("file:\\Users\\frig\\Desktop\\suposedftpserver\\nausica.jpg");
        imagePreview.setImage(image);
    }

    private void fillImageListView() {
        ObservableList<ImageView> imageViews = getImages();
    }

    private ObservableList<ImageView> getImages() {
       /* final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(null, "jpg");
        final File file = new File("F:\\Users\\frigg\\Desktop\\suposedftpserver");
        ArrayList<ImageView> images = new ArrayList<>();
            for (final File child : Objects.requireNonNull(file.listFiles())) {
                if(extensionFilter.accept(child)) {
                    ImageView image = new ImageView();
                    image.setImage(new Image(child.getPath()));
                    images.add(image);
            }
        }
        return FXCollections.observableArrayList(images);
        */
        return null;
    }

    @FXML
    private void useContainerImageData() {

    }

    @FXML
    private void deleteImage() {

    }
}
