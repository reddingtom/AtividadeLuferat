package com.reddingtom;

import java.text.Normalizer;

/**
 *
 * @author Reddingtom
 */

public class SlugText {
    
    public static String slugText(String text) {
        text = text.replace(" ", "_");
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("[^\\p{ASCII}]", "");
        text = text.toLowerCase();
        return text;
    }
}