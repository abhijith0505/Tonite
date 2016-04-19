package wolfhounds.tonite;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class CalendarEvents extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_events);

        listView = (ListView) findViewById(R.id.listView);
        SharedPreferences sharedPreferences=getSharedPreferences("calEvents", Context.MODE_PRIVATE);

        ArrayList<String> events=new ArrayList();
        int c=0;
        while(sharedPreferences.getString(c+"",null)!=null)
        {
            events.add(sharedPreferences.getString(c+"",""));
            Log.d("spref",sharedPreferences.getString(c+"",""));
            c++;
        }
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,events);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String event = arrayAdapter.getItem(position).toString();
                int c = event.indexOf("(");
                event = event.toString().substring(0,c-1);
                async newtask=new async("topics",CalendarEvents.this);
                newtask.execute(event);

            }
        });
    }


}
