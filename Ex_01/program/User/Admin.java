package Lesson_06.Ex_01.User;

import Lesson_06.Ex_01.book.Book;
import java.util.*;

public class Admin extends User {

    protected Admin(String login, String password){
        super(login,password);
    }

    public void changePassword(String oldPassword,String newPassword){
        UserFactory.changePassword(oldPassword, newPassword);
    }

    public void addBook(Book book){
        catalog.addBook(book);
    }

    public void addBook(String author,String name,int pages){
        catalog.addBook(author, name, pages);
    }

    public void addElectronicBook(String author,String name,int pages,String resourse){
        catalog.addElectronicBook(author, name, pages, resourse);
    }

    public void removeBook(Book book){
        catalog.removeBook(book);
    }

    public void removeAll(Book book){
        catalog.removeAll(book);
    }

    public void setDescription(int bookNum,String description){
        catalog.setDescription(bookNum, description);
    }

    @Override
    public void checkMail(){
        if(adminEmail!=null && !adminEmail.isEmpty() && !adminEmail.equals(" ")){
            Scanner scanner=new Scanner(adminEmail);
            try{
                addBook(scanner.next(),scanner.next(),scanner.nextInt());
                System.out.println("Suggested of user books that added into database");
                adminEmail=" ";
            }catch (InputMismatchException ex){
                System.out.println("There is spam here!");
            }
        }
    }

    @Override
    public boolean isAdmin(){
        return true;
    }
}