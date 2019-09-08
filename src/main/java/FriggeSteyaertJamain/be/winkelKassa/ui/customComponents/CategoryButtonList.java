package FriggeSteyaertJamain.be.winkelKassa.ui.customComponents;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class CategoryButtonList extends ArrayList {
    private String name;


    public CategoryButtonList(int initialCapacity, String name) {
        super(initialCapacity);
        this.name = name;
    }

    public CategoryButtonList(String name) {
        this.name = name;
    }

    public CategoryButtonList(@NotNull Collection c, String name) {
        super(c);
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
