package FriggeSteyaertJamain.be.winkelKassa.data.db;

import javafx.scene.image.Image;
import java.io.File;
import java.util.Map;

public interface AfbeeldingRepository {
    void addImage(String name, File image);
    Map<String, Image> getImage(int id);
    void updateImage(int id, String name, File image);
    int getImageIdByName(String name);
}
