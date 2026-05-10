package interfaces;

public interface Searchable {
    boolean matches(String keyword);

    default void search(String keyword) {
        if (matches(keyword)) {
            System.out.println(this);
        }
    }
}
