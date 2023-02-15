package trecos.exercicios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isEmail(String email) {
        Pattern regex = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isPassword(String password) {
        Pattern regex = Pattern.compile("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,20}$");
        Matcher matcher = regex.matcher(password);
        return matcher.matches();
    }
    
    public static boolean isPhone(String phone) {
        Pattern regex = Pattern.compile("^[0-9]{2}+ [0-9]{2}+ [0-9]{4}+-[0-9]{5}$");
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }
    
    public static boolean isTel(String tel) {
        Pattern regex = Pattern.compile("^[0-9]{2}+ [0-9]{2}+ [0-9]{4}+-[0-9]{4}$");
        Matcher matcher = regex.matcher(tel);
        return matcher.matches();
    }
    
    public static boolean isURL(String url) {
        Pattern regex = Pattern.compile("^[a-zA-z]{4,5}+://[a-zA-z]{1,}$");
        Matcher matcher = regex.matcher(url);
        return matcher.matches();
    }
    
    public static boolean isCPF(String cpf) {
        Pattern regex = Pattern.compile("^[0-9]{3}+.[0-9]{3}+.[0-9]{3}+-[0-9]{2}$");
        Matcher matcher = regex.matcher(cpf);
        return matcher.matches();
    }
}