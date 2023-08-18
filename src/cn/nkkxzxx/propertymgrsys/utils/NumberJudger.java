package cn.nkkxzxx.propertymgrsys.utils;

public class NumberJudger {
    public static boolean isNumeric(String str) {
        for (int i = str.length();--i>=0;){
            if (str.charAt(0) != '-' && !Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
