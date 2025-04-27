package Model;

public record Result(Boolean isSuccessful, String message) {
    @Override
    public String toString() {
        return message;
    }
}
