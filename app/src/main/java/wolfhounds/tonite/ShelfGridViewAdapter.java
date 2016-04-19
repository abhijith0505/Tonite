package wolfhounds.tonite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ravikiran on 16/4/16.
 */
public class ShelfGridViewAdapter extends ArrayAdapter<ShelfGridItem> {

    View row;
    Context context;
    int layoutResource;
    private ArrayList<ShelfGridItem> shelfGridData = new ArrayList<>();

    public ShelfGridViewAdapter(Context context, int layoutResource, ArrayList<ShelfGridItem> shelfGridData) {
        super(context, layoutResource, shelfGridData);
        this.context = context;
        this.shelfGridData = shelfGridData;
        this.layoutResource = layoutResource;
    }

    public void setShelfGridData(ArrayList<ShelfGridItem> shelfGridData) {
        this.shelfGridData = shelfGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*return super.getView(position, convertView, parent);*/

        row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResource, parent, false);
        }

        final ShelfGridItem shelfGridItem = shelfGridData.get(position);

        /*TextView textView = (TextView)(row.findViewById(R.id.catTextView));
        ImageView imageView = (ImageView)(row.findViewById(R.id.catImageView));
        textView.setText(gridItem.getText());
        imageView.setImageResource(gridItem.getImage());*/

        ImageView imageView = (ImageView)(row.findViewById(R.id.shelfImageView));
        imageView.setImageBitmap(shelfGridItem.getFile());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return row;
    }
}
