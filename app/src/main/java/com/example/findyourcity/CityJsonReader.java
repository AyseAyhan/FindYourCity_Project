package com.example.findyourcity;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CityJsonReader {
    public static ArrayList<CityModel> readJsonCity(Context context) {
        ArrayList<CityModel> cityModelList = new ArrayList<>();
        AssetManager manager = context.getAssets();

        try {
          /*  //FILENAME
            Log.e("InputStream","Started0");
            InputStream inputStream = manager.open("cityList.json");
            Reader reader = new InputStreamReader(inputStream);
            Log.e("InputStream","Ended0");
            Gson gson = new GsonBuilder().setLenient().create();
            Data data = gson.fromJson(reader, Data.class);
            Log.e("Data-InputStream","Ended0");

            //bu şekilde okumalı, maindeki for döngüsü ise arayüze yazdırılmalı
            //birindeki log u kaldırabilirsin, iki kez aynı döngüyü yazdırmış oluyor
            Log.e("Reader","Started");
            if (data != null && data.getCities() != null) {
                for (CityModel city : data.getCities()) {
                    if ("TR".equals(city.getCountry())) {
                    //Log.d("CityJsonReader", " City ID: " + city.getId() + ", Name: " + city.getName() + ", Country: " + city.getCountry()+" State "+city.getState()+ " Coordinates{ Lon: "+city.getCoord().getLon()+" Lat: "+city.getCoord().getLat());
                    cityModelList.add(city);*/

            Log.e("InputStream","Started");
            String str=loadJSONFromAsset(context,"cityList");
            Log.e("InputStream","Ended0");
            Gson gson = new GsonBuilder().setLenient().create();
            Data data = gson.fromJson(str, Data.class);
            Log.e("InputStream","Ended0");

            if (data != null && data.getCities() != null) {
                for (CityModel city : data.getCities()) {
                    if ("TR".equals(city.getCountry())) {
                        //Log.d("CityJsonReader", " City ID: " + city.getId() + ", Name: " + city.getName() + ", Country: " + city.getCountry()+" State "+city.getState()+ " Coordinates{ Lon: "+city.getCoord().getLon()+" Lat: "+city.getCoord().getLat());
                        cityModelList.add(city);
                    }
                }
            }

           /* Log.e("Reader","Ended");
            reader.close();
            inputStream.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityModelList;
    }


    public static String loadJSONFromAsset(Context context, String fileName) {
        String json;
        try {
            AssetManager manager = context.getAssets();
            InputStream is = manager.open(fileName + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int result = is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            Log.d("Reader", "loadJSONFromAsset " + result);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
