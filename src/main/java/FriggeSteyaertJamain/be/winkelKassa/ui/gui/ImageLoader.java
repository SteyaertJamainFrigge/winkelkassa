package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    private String folder;
    private Window owner;
    private BufferedImage image;
    private File f;

    ImageLoader(String folder, Window stageOwner) {
        this.folder = folder;
        this.owner = stageOwner;
        this.image = null;
    }

    public String getFolder() {
        return folder;
    }

    /**
     * @return String path where the image is saved
     */
    public String getImage() {
        openExplorer();
        readFile();
        writeFile();
        return f.getPath();
    }

    private void openExplorer() {
        Stage stage = new Stage();
        stage.initOwner(owner);
        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().clear();
        chooser.getExtensionFilters().add(imageFilter);
        chooser.setTitle("Open File");
        f = chooser.showOpenDialog(stage);
    }

    private void readFile() {
        int width = 963;    //width of the image
        int height = 640;   //height of the image
        try {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(f);
            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    private void writeFile() {
        try {
            f = new File(folder+f.getName());  //output file path
            ImageIO.write(image, "jpg", f);
            System.out.println("Writing complete.");
        } catch (IOException e) {
            throw new KassaException("couldn't write file");
        }
    }
}
