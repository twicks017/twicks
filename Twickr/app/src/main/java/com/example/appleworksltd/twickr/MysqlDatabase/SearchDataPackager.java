package com.example.appleworksltd.twickr.MysqlDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by appleworksltd on 03/05/2017.
 */

public class SearchDataPackager {

    String query,cat;

    public SearchDataPackager(String query,String cat){
        this.query = query;
        this.cat =cat;
    }

    public String packager(){
        JSONObject jo = new JSONObject();
        StringBuffer stringBuffer = new StringBuffer();

        try {
            jo.put("Query",query);
            jo.put("Category",cat);


            Boolean firstvalue = true;
            Iterator it = jo.keys();

            do {
                String key = it.next().toString();
                String value = jo.get(key).toString();

                if (firstvalue){
                    firstvalue = false;
                }else {
                    stringBuffer.append('&');
                }

                stringBuffer.append(URLEncoder.encode(key,"UTF-8"));
                stringBuffer.append("=");
                stringBuffer.append(URLEncoder.encode(value,"UTF-8"));
            }while (it.hasNext());
            return stringBuffer.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }
}
