package com.example.appleworksltd.twickr.MysqlDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class TicketPackager {
    String name,  venue,location,date,classe,promoter;
    int s;
    String username,code,image;
    public TicketPackager(int s, String name, String venue,String location, String date, String classe, String promoter,String username,String code,String image) {
        this.s = s;
        this.name = name;
        this.venue = venue;
        this.location = location;
        this.date = date;
        this.classe = classe;
        this.promoter = promoter;
        this.username = username;
        this.code = code;
        this.image = image;
    }

    public String packData(){
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();

        try {

            jo.put("Username",name);
            jo.put("Promoter",promoter);
            jo.put("Name",username);
            jo.put("Venue",venue);
            jo.put("Location",location);
            jo.put("TDate",date);
            jo.put("Class",classe);
            jo.put("Price",s);
            jo.put("Code",code);
            jo.put("ImageUrl",image);

            Boolean firstValue = true;

            Iterator iterator = jo.keys();

            do {
                String key = iterator.next().toString();
                String  value = jo.get(key).toString();

                if(firstValue){
                    firstValue = false;
                }else
                {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key,"UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(value,"UTF-8"));

            }while (iterator.hasNext());
            return sb.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
