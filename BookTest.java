import java.io.*;
import java.util.*;
public class BookTest {
    //Encapsulation: method is declared as private
    private static PrintedBook findPrintedBook(ArrayList<Book> books) {
        for(Book book : books) {
            if(book instanceof PrintedBook) {
                return (PrintedBook) book;
            }
        }
        return null;
    }
    //Encapsulation: method is declared as private
    private static AudioBook findAudioBook(ArrayList<Book> books) {
        for(Book book : books) {
            if(book instanceof AudioBook) {
                return (AudioBook) book;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the file name: ");
        String filename = input.nextLine();

        ArrayList<String[]> bookList = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = br.readLine()) != null){
                String[] book = line.split(",");
                for(int i = 0; i < book.length; i++){
                    book[i] = book[i].replace("\"", "").trim();
                }
                bookList.add(book);
            }
            String[] book;
            for(int i = 0; i < bookList.size(); i++) {
                book = bookList.get(i);

                String title = book[0];
                String author = book[1];
                String genre = book[2];
                int cost = book[3].equals("N/A") ? 0 : Integer.parseInt(book[3]);
                String type = book[6];
                int length = 0;
                int pages = 0;

                length = book[4].equals("N/A") ? 0 : Integer.parseInt(book[4]);
                pages = book[5].equals("N/A") ? 0 : Integer.parseInt(book[5]);

                if(type.equals("audioBook")) {
                    //Polymorphic reference when creating an object of book with AudioBook constructor
                    books.add(new AudioBook(title, author, genre, cost, length, type));
                }
                else if(type.equals("printedBook")) {
                    //Polymorphic reference when creating an object of book with PrintedBook constructor
                    books.add(new PrintedBook(title, author, genre, cost, pages, type));
                }
            }

            int num;
            boolean wantToContinue = true;
            do{
                System.out.println(""" 
                                            BOOK MENU    
                                ============================== 
                                 1 - Add a book 
                                 2 - Remove a book 
                                 3 - Print last 6 books 
                                 4 - Print last 3 Printed Books 
                                 5 - Print last 3 Audio Books 
                                 6 - Number of Books per Genre 
                                 7 - Total Cost of All Books
                                 8 - Average Number of Pages (Printed) 
                                 9 - Average Length (Audio Minutes) 
                                  
                                Please enter a number for the action you would like to take!""");

                num = input.nextInt();
                input.nextLine();
                switch(num){
                    case 1:
                    {
                        System.out.println("Enter the title: ");
                        String title = input.nextLine();
                        System.out.println("Enter the author: ");
                        String author = input.nextLine();
                        System.out.println("Enter the genre: ");
                        String genre = input.nextLine();
                        System.out.println("Enter the cost: ");
                        int cost = input.nextInt();
                        input.nextLine();
                        System.out.println("Enter the length (minutes): ");
                        int length = input.nextInt();
                        input.nextLine();
                        System.out.println("Enter the number of pages: ");
                        int pages = input.nextInt();
                        input.nextLine();
                        System.out.println("Enter the type of book: ");
                        String type = input.nextLine();
                        String info = "";
                        if(type.equals("audioBook")){
                            info = title + "," + author + "," + genre + "," + cost + "," + length +
                                    ",N/A," + type;
                        }
                        else if(type.equals("printedBook")){
                            info = title + "," + author + "," + genre + "," + cost + ",N/A," +
                                    pages + "," + type;
                        }
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))){
                            bw.write(info);
                            bw.newLine();
                        }
                        catch(IOException e){

                            System.out.println("Error adding to file " + e.getMessage());
                        }

                        String[] newBook = info.split(",");
                        title = newBook[0];
                        author = newBook[1];
                        genre = newBook[2];
                        cost = Integer.parseInt(newBook[3]);
                        length = 0;
                        pages = 0;

                        length = newBook[4].equals("N/A") ? 0 : Integer.parseInt(newBook[4]);
                        pages = newBook[5].equals("N/A") ? 0 : Integer.parseInt(newBook[5]);
                        type = newBook[6];

                        if(type.equals("audioBook")) {
                            books.add(new AudioBook(title, author, genre, cost, length, type));
                        }
                        else if(type.equals("printedBook")) {
                            books.add(new PrintedBook(title, author, genre, cost, pages, type));
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Enter the title: ");
                        String title = input.nextLine();
                        System.out.println("Enter the author: ");
                        String author = input.nextLine();
                        System.out.println("Enter the genre: ");
                        String genre = input.nextLine();
                        System.out.println("Enter the cost: ");
                        int cost = input.nextInt();
                        input.nextLine();
                        System.out.println("Enter the length (minutes): ");
                        int length = input.nextInt();
                        input.nextLine();
                        System.out.println("Enter the number of pages: ");
                        int pages = input.nextInt();
                        input.nextLine();
                        System.out.println("Enter the type of book: ");
                        String type = input.nextLine();
                        for(int i = books.size() - 1; i >= 0; i--){
                            Book b = books.get(i);

                            if(b.getTitle().equals(title) && b.getAuthor().equals(author)
                                    && b.getGenre().equals(genre)){
                                books.remove(i);
                            }
                        }
                        try(PrintWriter pw = new PrintWriter(new FileWriter(filename))){
                            System.out.println("\n");
                            for(Book b: books){
                                if(b.getType().equals("audioBook"))
                                {
                                    pw.println(b.getTitle() + "," + b.getAuthor() + "," + b.getGenre() + "," +
                                            b.getTheCost() + "," + b.getSize() + ",N/A," + b.getType());
                                }
                                if(b.getType().equals("printedBook")){
                                    pw.println(b.getTitle() + "," + b.getAuthor() + "," + b.getGenre() +
                                            "," + b.getTheCost() + ",N/A," + b.getSize() + "," + b.getType());
                                }
                            }
                        }catch(IOException e){
                            System.out.println("Error rewriting file " + e.getMessage());
                        }
                        break;
                    }
                    case 3:
                    {
                        books.get(0).lastSixBookDetails(books);
                        break;
                    }
                    case 4:
                    {
                        boolean wantPrinted = true;
                        System.out.println("Here are the last three printed books:");
                        Book.printLastThree(books, wantPrinted);
                        break;
                    }
                    case 5:
                    {
                        boolean wantPrinted = false;
                        System.out.println("Here are the last three audio books:");
                        Book.printLastThree(books, wantPrinted);
                        break;
                    }
                    case 6:
                    {
                        Book theBook = books.get(0);
                        System.out.println(theBook.numBooksPerGenre(books));
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Total cost: " + books.get(0).getTotalCost(books));
                        break;
                    }
                    case 8:
                    {
                        PrintedBook print = findPrintedBook(books);
                        System.out.printf("Average number of pages: %.2f", print.avgNumPages(books));
                        System.out.println();
                        break;
                    }
                    case 9:
                    {
                        AudioBook audio = findAudioBook(books);
                        System.out.printf("Average number of minutes: %.2f", audio.avgNumMinutes(books));
                        System.out.println();
                        break;
                    }
                    default: System.out.println("Invalid input");
                }
                System.out.println("\nWould you like to continue? (y/n): ");
                String answer = input.next();
                if (answer.equalsIgnoreCase("y")) {
                    wantToContinue = true;
                } else {
                    wantToContinue = false;
                    System.out.println("Program shutting down...");
                }
            }while(wantToContinue == true);
        }catch(IOException e){
            System.out.println("Error reading file " + e.getMessage());
        }
    }
}