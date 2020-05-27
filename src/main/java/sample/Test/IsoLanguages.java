package sample.Test;

import java.util.*;
import sun.rmi.runtime.Log;

import java.util.Locale;

public class IsoLanguages {
    public static void main(String[] args) {
        Hashtable<String,String> my_dict = new Hashtable<String,String>();
        for (Locale locale : Locale.getAvailableLocales()) {

            my_dict.put(locale.getLanguage(),locale.getDisplayName());


        }
        System.out.println(my_dict.get("es"));
        }
    }





