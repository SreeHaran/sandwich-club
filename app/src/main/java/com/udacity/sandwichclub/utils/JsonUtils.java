package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        if(json==null){
            return null;
        }
        try {
            JSONObject root = new JSONObject(json);
            JSONObject name = root.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray otherNames = name.optJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            if(otherNames != null){
                int i;
                for (i = 0; i < otherNames.length(); i++) {
                   String namesIndex = otherNames.getString(i);
                    alsoKnownAsList.add(namesIndex);
                }
            }
            String placeOfOrigin = root.optString("placeOfOrigin");
            String description = root.optString("description");
            String image = root.optString("image");
            JSONArray ingredients = root.optJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            if(ingredients != null){
                int i;
                for (i = 0; i < ingredients.length(); i++) {
                    String ingredientsIndex = ingredients.optString(i);
                    ingredientsList.add(ingredientsIndex);
                }
            }
            return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
