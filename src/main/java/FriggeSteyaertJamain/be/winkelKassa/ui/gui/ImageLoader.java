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

public class ImageLoader {

    private String folder;
    private Window owner;
    private File f;

    ImageLoader(String folder, Window stageOwner) {
        this.folder = folder;
        this.owner = stageOwner;
    }

    public String getFolder() {
        return folder;
    }

    /**
     * @return File chosen image
     */
    public Image getImage() {
        openExplorer();
        //writeFile();
        Image image;
        try {
            BufferedImage bufferedImage = null;
            bufferedImage = ImageIO.read(f);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException e) {
            throw new KassaException("couldn't read image file");
        }
        return image;
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

    private void writeFile() {

        AfbeeldingRepository repo = Repositories.getInstance().getAfbeeldingRepository();
        repo.addImage(f.getName(), f);


        /*try {
            File dir = new File(folder);
            boolean folderresult = dir.mkdirs();
            System.out.println("made folder? "+ folderresult);
            f = new File(dir, f.getName());  //output file path
            boolean fileresult =  f.createNewFile();
            System.out.println("made file? "+ fileresult);
            ImageIO.write(image, "jpg", f);
        } catch (IOException e) {
            throw new KassaException("couldn't write file");
        }

         */
    }
}
