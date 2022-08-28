package com.example.assign_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SaveDataPreference {


        private static final String LIST_KEY = "list_key";

        public static void writeListInPref(Context context, List<PersonalDetails> list) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(list);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(LIST_KEY, jsonString);
            editor.apply();


        }

        public static List<PersonalDetails> readListFromPref(Context context) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            String jsonString = pref.getString(LIST_KEY,"");
            Gson gson = new Gson();

            Type type = new TypeToken<ArrayList<PersonalDetails>>() {}.getType();

            List<PersonalDetails> list = gson.fromJson(jsonString, type);
            return list;

        }


}
