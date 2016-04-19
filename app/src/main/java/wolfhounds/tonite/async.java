package wolfhounds.tonite;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;


public class async extends AsyncTask<String,Void,String> {
    private String type;
    private Context context;
    //private TextView tv;
    public async(String type,Context context){
        this.type=type;
        this.context=context;
      //  this.tv=tv;
    }
    String param=null;

    @Override
    protected String doInBackground(String... params) {
        param=params[0];
        HttpURLConnection urlConnection=null;
        StringBuffer buffer=new StringBuffer();
        try {
            URL url = new URL("http://uclassify.com/browse/uClassify/"+type+"/ClassifyText?readkey=JBNL8LY7ZdvI&text="+params[0].replace(" ","+")+"&output=json&version=1.01");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while((line=reader.readLine())!=null){
                buffer.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }

        return buffer.toString();
    }
    @Override
    protected void onPostExecute(String s) {
        Log.e("abce",s);
        String max=null;
        double value=0;
        try {
            JSONObject object = new JSONObject(s);
            JSONObject cls1=object.getJSONObject("cls1");
            Iterator<String> i=cls1.keys();
            while(i.hasNext()){
                String key=i.next();
                if(Double.parseDouble(cls1.getString(key))>value){
                    value=Double.parseDouble(cls1.getString(key));
                    max=key;
                }
            }
            if(max.equals("Home"))
                new async("home-topics",context).execute(param);
            else {
                Toast.makeText(context, max,Toast.LENGTH_SHORT).show();
                String url = "http://www.myntra.com/";

                if(max.equals("Business")) {
                    url+="blazers";
                }
                else if(max.equals("Entertaining")) {
                    url+="party";
                }
                else if(max.equals("Sports")) {
                    url+="sports+shoes";
                }
                else {
                    url+="tshirt";
                }

                Intent in = new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse(url));
                context.startActivity(in);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("abcde",e.toString());
        }
        super.onPostExecute(s);

    }
}
