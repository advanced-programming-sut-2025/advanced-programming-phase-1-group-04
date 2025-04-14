package Model.Enum;

import java.util.regex.Matcher;

public interface Command {
     String getPattern();
     default Matcher getMatch
}