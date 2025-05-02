package Model.Enum;

public enum SecurityQuestion {
    One   ("Esme saget/siblinget chie?"),
    Two   ("Aya bad az ghahr o cat aks az pahat midi?"),
    Three ("Where is Abam?"),
    Four  ("Chand ta bache dari?"),
    Five  ("Mamaneto bishtar dost dari ya aghato?"),
    Six   ("Positione morede alaghat?"),
    Seven ("Chikar karD ba delam? (vayyy)"),
    Eight ("Nezhade morede alaghat?"),
    Nine  ("Esme pedaret?"),
    Ten   ("Avalin partnert?");

    private final String question;

    SecurityQuestion(String question) {
        this.question = question;
    }
}
