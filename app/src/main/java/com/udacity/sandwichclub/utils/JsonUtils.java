package com.udacity.sandwichclub.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
                for(i=0; i >otherNames.length(); i++){
                   String namesIndex = otherNames.getString(i);
                    alsoKnownAsList.add(namesIndex);
                }
            }else {
                alsoKnownAsList.add("None");
            }
            String placeOfOrigin = root.optString("placeOfOrigin");
            String description = root.optString("description");
            String image = root.optString("image");
            JSONArray ingredients = name.optJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            if(ingredients != null){
                int i;
                for(i=0; i >ingredients.length(); i++){
                    String ingredientsIndex = ingredients.optString(i);
                    ingredientsList.add(ingredientsIndex);
                }
            }
            Sandwich sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
