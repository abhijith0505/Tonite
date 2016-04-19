package wolfhounds.tonite;

/**
 * Created by ravikiran on 16/4/16.
 */
public class GridItem {

    private String text;
    private int imageResId;

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return imageResId;
    }
}
