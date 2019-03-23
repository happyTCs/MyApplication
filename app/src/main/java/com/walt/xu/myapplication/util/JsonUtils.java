package com.walt.xu.myapplication.util;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by walt on 2019/2/15.
 */

public class JsonUtils {
    private static final String TAG = "JsonUtils";
    public static <T> T json2bean(String json ,Class<T> cls) {
        try {
            Gson gson = new Gson();
            T t = gson.fromJson(json, cls);
            return t;
        } catch (JsonSyntaxException e) {
            Log.i(TAG, "json2bean: bean异常");
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T>  jsonArray2list(String jsonListString ,Class<T> cls) {
        try {
            List<T> arrayList = new ArrayList<>();
            Gson gson = new Gson();
//            创建一个json解析器
            JsonParser parser = new JsonParser();
//            通过parser把string类型的list解析为JsonElement对象
            JsonElement element = parser.parse(jsonListString);
            if (element.isJsonArray()) {
                JsonArray asJsonArray = element.getAsJsonArray();
                Iterator<JsonElement> iterator = asJsonArray.iterator();
                while (iterator.hasNext()) {
                    JsonElement jsonElement = iterator.next();
                    String jsonString = jsonElement.toString();
                    T t = gson.fromJson(jsonString, cls);
                    arrayList.add(t);
                }
            }
            return arrayList;
        } catch (JsonSyntaxException e) {
            Log.i(TAG, "jsonArray2list: json解析为list时异常");
            e.printStackTrace();
            return null;
        }
    }

    public static String Object2Json(Object obj) {
        Gson gson = new Gson();
        String s = gson.toJson(obj);
        return s;
    }

    //    创建jsonstring
    public static String creatjsonstring(String key, String value) {
        JSONObject jsonObject = new JSONObject();
        try {
           jsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


}
