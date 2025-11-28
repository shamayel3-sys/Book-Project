import java.io.*;
import java.util.*;
public abstract class Book implements BookInterface {
    /**Abstraction is demonstrated here by making this class abstract and implementing methods from the interface
     * keyword: implements**/

    private String title;
    private String author;
    private String genre;
    private String type;
    private int cost;
    private int size;
    //These six instance variables are made private by "Encapsulation"
    public Book(String title, String author, String genre, int cost, int size, String type) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.cost = cost;
        this.size = size;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }
    public int getTheCost() {
        return cost;
    }
    public int getSize() {
        return size;
    }
    public String getType() {
        return type;
    }

    //Abstraction, because this method is taken from the BookInterface
    public void lastSixBookDetails(ArrayList<Book> list){
        System.out.println("Here are the last 6 books:");
        int start = Math.max(0, list.size() - 6);
        for(int i = list.size()-1; i >= start; i--){
            list.get(i).printDetails();
        }
    }

    //Abstraction because methods are abstract and defined as needed in concrete classes
    public abstract void printDetails();
    public abstract boolean isPrinted();
    public abstract boolean isAudio();

    public static void printLastThree(ArrayList<Book> list, boolean thePrinted){
        int count = 0;
        for(int i = list.size()-1; i >= 0 && count < 3; i--){
            Book b = list.get(i);
            if(thePrinted && b.isPrinted()){
                b.printDetails();
                count++;
            }
            else if(!thePrinted && b.isAudio()){
                b.printDetails();
                count++;
            }
        }
    }

    //Abstraction, because method is taken from BookInterface
    public String numBooksPerGenre(ArrayList<Book> list){
        int numDrama = 0;
        int numRomance = 0;
        int numSciFi = 0;
        int numAnime = 0;
        for(Book b: list){
            if(b.getGenre().equals("Drama")){
                numDrama++;
            }
            if(b.getGenre().equals("Romance")){
                numRomance++;
            }
            if(b.getGenre().equals("Sci-FI")){
                numSciFi++;
            }
            if(b.getGenre().equals("Anime")){
                numAnime++;
            }
        }
        return "Drama: " + numDrama + " book(s)\nRomance: " + numRomance + " book(s)\nSci-FI: " + numSciFi
                 + " book(s)\nAnime: " + numAnime + " book(s)";
    }

    //Abstraction, because method is taken from BookInterface
    public double getTotalCost(ArrayList<Book> list){
        double totalCost = 0;
        for(Book b: list){
            totalCost += b.getCost();
        }
        return totalCost;
    }
    //Abstraction because abstract method allows methods to be defined as needed in the concrete classes
    public abstract double getCost();
}