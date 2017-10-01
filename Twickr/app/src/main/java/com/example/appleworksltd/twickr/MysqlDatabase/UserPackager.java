package com.example.appleworksltd.twickr.MysqlDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by appleworksltd on 20/03/2017.
 */

public class UserPackager {
    String fname,lname,username,password,phone,location;

    public UserPackager(String fname, String lname, String username, String password,String phone,String location) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.location = location;
    }

    public String packData(){
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();

        try {
            jo.put("FName",fname);
            jo.put("LName",lname);
            jo.put("Username",username);
            jo.put("Phone",phone);
            jo.put("Location",location);
            jo.put("Password",password);

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
