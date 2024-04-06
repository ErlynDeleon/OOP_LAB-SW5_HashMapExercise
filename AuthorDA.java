import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class AuthorDA {
    private HashMap<String, Author> authorsMap;

    public AuthorDA() {
        this.authorsMap = new HashMap<>();

        try (Scanner authorFileReader = new Scanner(new File("Author.csv"))) {
            while (authorFileReader.hasNextLine()) {
                String authorLineData = authorFileReader.nextLine();
                String[] authorData = authorLineData.split(",");
                if (authorData.length >= 2) {
                    String authorName = authorData[0].trim();
                    String bio = authorData[1].trim();

                    Author author = new Author();
                    author.setName(authorName);
                    author.setBio(bio);
                    addAuthor(authorName, author);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAuthor(String authorName, Author author) {
        authorsMap.put(authorName, author);
    }

    public Author getAuthor(String authorName) {
        return authorsMap.get(authorName);
    }

    public HashMap<String, Author> getAuthorMap() {
        return authorsMap;
    }
}
