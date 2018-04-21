package com.example.ferdi.perpustakaanku.Helper;

import com.example.ferdi.perpustakaanku.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperValidation {
    private static Pattern pattern;
    private static Matcher matcher;

    private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

    public HelperValidation() {
        pattern = Pattern.compile(HEX_PATTERN);
    }

    public static boolean validate(final String hexColorCode) {
        pattern = Pattern.compile(HEX_PATTERN);
        matcher = pattern.matcher(hexColorCode);
        return matcher.matches();
    }

    public static String checkGenre(int id){
        String temp="";

        if(id== R.id.rb_buku_agama){
            temp="agama";
        }
        else if(id==R.id.rb_buku_komputer){
            temp="komputer";
        }
        else if(id==R.id.rb_buku_novel){
            temp="novel";
        }
        else if(id==R.id.rb_buku_lain){
            temp="lain - lain";
        }

        return temp;
    }
}
