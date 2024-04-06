import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class BookDA {

    private HashMap<String, Book> booksMap;
    private HashMap<String, String> authorsMap;
    private AuthorDA authorDA;

    public BookDA() {
        this.booksMap = new HashMap<>();
        this.authorsMap = new HashMap<>();
        this.authorDA = new AuthorDA();

        try (Scanner bookFileReader = new Scanner(new File("Book.csv"))) {
            while (bookFileReader.hasNextLine()) {
                String line = bookFileReader.nextLine();
                String[] bookData = line.split(",");
                if (bookData.length >= 3) {
                    String isbn = bookData[0].trim();
                    String title = bookData[1].trim();
                    String authorName = bookData[2].trim();

                    Book book = new Book();
                    book.setIsbn(isbn);
                    book.setTitle(title);
                    addBook(book);
                    setAuthor(isbn, authorName);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBook(Book book) {
        booksMap.put(book.getIsbn(), book);
    }

    public Book getBook(String isbn) {
        return booksMap.get(isbn);
    }

    public void setAuthor(String isbn, String authorName) {
        authorsMap.put(isbn, authorName);
    }

    public String getAuthor(String isbn) {
        return authorsMap.get(isbn);
    }

    public void displayReport() {
        HashMap<String, Author> authorMap = authorDA.getAuthorMap();
        for (String isbn : booksMap.keySet()) {
            Book book = booksMap.get(isbn);
            String title = book.getTitle();
            String authorName = getAuthor(isbn);
            Author author = authorMap.get(authorName);
            String bio = author != null ? author.getBio() : "";
    
            System.out.println(isbn + " " + title);
            System.out.println("\t" + authorName + " - " + bio);
            System.out.println();
        }
    }
}
