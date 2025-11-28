import java.util.*;
//Inheritance is demonstrated because PrintedBook inherits from the abstract class Book (keyword: extends) 
public class PrintedBook extends Book{
    private int numPages = 0;
    private String type;
    //Encapsulation when making instance variables private

    public PrintedBook(String title, String author, String genre, int cost, int pages, String type) {
        super(title, author, genre, cost, pages, type);
    }
    //Constructor is inherited from Book: Inheritance

    public String getTitle() {
        return super.getTitle();
    }
    public String getAuthor() {
        return super.getAuthor();
    }
    public String getGenre() {
        return super.getGenre();
    }
    public String getType() {
        return super.getType();
    }
    public int getNumPages() {
        return super.getSize();
    }

    //These five methods inherit the methods from Book: Inheritance
    //Abstraction because PrintedBook defines abstract method getCost() from abstract class Book
    //Polymorphism because it overrides method from the class Book
    public double getCost() {
        if(super.getTheCost() == 0){
            return 10 * super.getSize();
        }
        else
            return super.getTheCost();
    }
    //Inheritance because the super keyword is used to apply these methods

    public void totalNumPages(ArrayList<Book> list) {
        for(Book b: list){
            if(b.getType().equals("printedBook")){
                numPages += b.getSize();
            }
        }
    }
    public int getPages() {
        return numPages;
    }

    //Polymorphism: PrintedBook overrides method from the class Book
    public void printDetails(){
        System.out.println(this.getTitle() + "," + this.getAuthor() + "," + this.getGenre() + "," + this.getTheCost()
                + ",N/A,"  + this.getNumPages() + "," + this.getType());
    }
    //Polymorphism: PrintedBook overrides method from the class Book
    public boolean isPrinted() {
        return true;
    }
    //Polymorphism: PrintedBook overrides method from the class Book
    public boolean isAudio() {
        return false;
    }

    public double avgNumPages(ArrayList<Book> list){
        double numPages = 0;
        int numPrinted = 0;
        for(Book b: list){
            if(b.getType().equals("printedBook")){
                PrintedBook pb = (PrintedBook) b;
                numPages += pb.getNumPages();
                numPrinted++;
            }
        }
        return numPages / numPrinted;
    }
}