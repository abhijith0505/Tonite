package wolfhounds.tonite;

import android.graphics.Bitmap;

/**
 * Created by ravikiran on 16/4/16.
 *
 */

public class ShelfGridItem {

    private int category; //0-casual   1-formal   2-party   3-accessories
    private Bitmap file;

    public int getCategory() {
        return category;
    }

    public Bitmap getFile() {
        return file;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setFile(Bitmap file) {
        this.file = file;
    }
}
