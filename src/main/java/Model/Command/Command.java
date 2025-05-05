package Model.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {

     String getPattern();

     default Matcher getMatcher(String input){
          Matcher matcher = Pattern.compile(this.getPattern()).matcher(input);
          if (matcher.matches()) {
               return matcher;
          }
          return null;
     }

     default boolean isMatch (String input) {
          return getMatcher(input).matches();
     }
}