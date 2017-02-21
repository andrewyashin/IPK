package sample;

import java.util.List;

/**
 * Created by andrew_yashin on 12/8/16.
 */
public class Tools {

    public static String addQuotes(String string){
        return "\"" + string + "\"";
    }

    public static String createString(List<String> list){
        if(list==null) return "()";

        String result = "(";

        for (String word : list) {
            if (word.equals(list.get(list.size() - 1))){
                result += word + ")";
            } else {
                result += word + ",";
            }
        }

        return result;
    }
}
