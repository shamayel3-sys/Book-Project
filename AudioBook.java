import java.util.*;
import java.io.*;
//Inheritance: AudioBook inherits from the abstract class Book (keyword: extends) 
public class AudioBook extends Book {
    private int numMinutes = 0;
    private String type;
    //Encapsulation because these instance variables are defined as private

    public AudioBook(String title, String author, String genre, int cost, int length, String type) {
        super(title, author, genre, cost, length, type);
    }
    //Constructor inherited from Book: Inheritance

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

    public int getNumMinutes() {
        return super.getSize();
    }
    //These five methods are inherited from Book: Inheritance
    //Abstraction because AudioBook defines abstract method getCost() from abstract class Book
    //Polymorphism: overrides getCost() method in class Book
    public double getCost() {
        if(super.getTheCost() == 0)
            return 5 * super.getSize();
        else
            return super.getTheCost();
    }

    public void totalNumMinutes(ArrayList<Book> list) {
        for (Book b : list) {
            if (b.getType().equals("audioBook")) {
                numMinutes += b.getSize();
            }
        }
    }
    public int getMinutes(){
        return numMinutes;
    }

    //Polymorphism: PrintedBook overrides method from the class Book
    public boolean isPrinted() {
        return false;
    }
    //Polymorphism: PrintedBook overrides method from the class Book
    public boolean isAudio() {
        return true;
    }
    //Polymorphism because it overrides method in class Book
    public void printDetails(){
        System.out.println(this.getTitle() + "," + this.getAuthor() + "," + this.getGenre() + "," + this.getTheCost()
                + ","  + this.getNumMinutes() + ",N/A," + this.getType());
    }

    public double avgNumMinutes(ArrayList<Book> list) {
        double numAudio = 0;
        int numMinutes = 0;
        for (Book b : list) {
            if (b.getType().equals("audioBook")) {
                AudioBook audio = (AudioBook) b;
                numMinutes += audio.getNumMinutes();
                numAudio++;
            }
        }
        return numMinutes / numAudio;
    }
} 