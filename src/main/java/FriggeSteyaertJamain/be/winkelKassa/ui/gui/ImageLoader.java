package FriggeSteyaertJamain.be.winkelKassa.ui.gui;

import FriggeSteyaertJamain.be.winkelKassa.data.db.AfbeeldingRepository;
import FriggeSteyaertJamain.be.winkelKassa.data.db.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.util.KassaException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageLoader {

    private Window owner;
    private File file;

    ImageLoader(Window stageOwner) {
        this.owner = stageOwner;
    }

    /**
     * @return File chosen image
     */
    public HashMap<String, Image> getImage() {
        openExplorer();
        if (file != null) {
            Image image = readFile();
            writeFile();
            HashMap<String, Image> map = new HashMap<>();
            map.put(file.getName(), image);
            return map;
        } else return null;
    }

    private Image readFile() {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException e) {
            throw new KassaException("couldn't read image file");
        }
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
        file = chooser.showOpenDialog(stage);
    }

    private void writeFile() {
        AfbeeldingRepository repo = Repositories.getInstance().getAfbeeldingRepository();
        repo.addImage(file.getName(), file);
    }
}
