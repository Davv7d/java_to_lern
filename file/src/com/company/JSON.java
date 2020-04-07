package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class JSON {
    public void GetDataFromJSON(String dataJSON){
        JSONObject jsonObject = new JSONObject(dataJSON);
        for (String s:jsonObject.keySet()){
            System.out.println(s);
        }
        JSONArray jsonArray = jsonObject.optJSONArray("post");
        for (int i=0; i<jsonArray.length(); i++){
            JSONObject tempObj = jsonArray.optJSONObject(i);
            System.out.println(tempObj.optString("nick"));
            System.out.println(tempObj.optString("data","zły klucz"));

        }
    }

    public JSONArray SetdateToJSONArray(){
        int index = 0; //twoj numer indeksu
        Scanner input = new Scanner(System.in);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Indeks", index);
        System.out.print("Nick: ");
        String nick = input.nextLine();
        System.out.println("treść postu:");
        String post = input.nextLine();
        jsonObject.put("Nick",nick);
        jsonObject.put("Text",post);

        JSONArray objArray = new JSONArray();
        objArray.put(jsonObject);
        return objArray;


    }
}
