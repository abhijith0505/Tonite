package wolfhounds.tonite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.FileHandler;

public class ShelfActivity extends AppCompatActivity {

    ArrayList<ShelfGridItem> shelfGridData;
    ShelfGridViewAdapter shelfGridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] fileNames = new String[0];
        shelfGridData = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();

        File path = new File(Environment.getExternalStorageDirectory(), bundle.get("Category").toString());
        Log.d("DEBUG", Environment.getExternalStorageDirectory()+"/"+bundle.get("Category").toString());
        if(path.exists())
            fileNames = path.list();

        for(int i=0; i<fileNames.length; ++i) {
            Bitmap bitmap = BitmapFactory.decodeFile(path.getPath()+"/"+fileNames[i]);
            ShelfGridItem shelfGridItem = new ShelfGridItem();
            shelfGridItem.setFile(bitmap);
            Log.d("DEBUG", bitmap.toString());
            shelfGridData.add(shelfGridItem);
        }

        GridView gridView = (GridView) findViewById(R.id.shelfGridView);
        shelfGridViewAdapter = new ShelfGridViewAdapter(this, R.layout.shelf_grid_item, shelfGridData);
        shelfGridViewAdapter.setShelfGridData(shelfGridData);
        gridView.setAdapter(shelfGridViewAdapter);


    }

}
