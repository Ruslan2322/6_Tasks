package Lesson_06.Ex_01.User;

import Lesson_06.Ex_01.book.Book;
import Lesson_06.Ex_01.book.BookDatabase;

import java.util.*;

public class User {

    protected static BookDatabase catalog=new BookDatabase();
    private String login="New user";
    private String password;
    private String email=" ";
    protected static String adminEmail=" ";

    protected User(String login,String password){
        setLogin(login);
        if(password!=null && !password.isEmpty()){
            this.password=password;
        }
    }

    public void setLogin(String login){
        if(login!=null && !login.isEmpty()){
            this.login=login;
        }
    }

    public String getLogin(){
        return login;
    }

    public List<Book> findBook(Book book){
        return catalog.findBook(book);
    }

    public List<Book> findBook(String author){
        return catalog.findBook(author);
    }

    public boolean isAdmin(){
        return false;
    }

    public void print(int page){
        catalog.print(page);
    }

    public void print(){
        catalog.print();
    }

    public void checkMail(){
        if(email!=null && !email.isEmpty() && !email.equals(" ")) {
            System.out.println(email);
            email=" ";
        }else{
            System.out.println("\nThere is not new massages!");
        }
    }

    public void adviceAdmin(String advice){
        if(advice!=null && !advice.isEmpty()) {
            adminEmail += advice;
        }
    }

    public String getDescription(int bookNum){
        return catalog.getDescription(bookNum);
    }

    public Book getNthBook(int bookNum){
        return catalog.getNthBook(bookNum);
    }

    @Override
    public String toString(){
        return String.format("%20s %15s %15s",login,encryptPassword(),(isAdmin())?"Аdmin":"User");
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }

        if(obj==null || obj.getClass() != this.getClass()){
            return false;
        }

        User other=(User) obj;

        return login.equals(other.login) && isAdmin()==other.isAdmin();
    }

    @Override
    public int hashCode(){
        final int prime=31;
        int result=1;
        result=prime*result+((login==null)?0:login.hashCode());
        result=prime*result+Boolean.hashCode(isAdmin());
        return result;
    }

    protected void addMessage(String message){
        if(message!=null && !message.isEmpty()){
            email+=message;
        }
    }

    protected String getPassword(){
        return password;
    }

    private String encryptPassword(){
        String string=new String();
        for(int i=0;i<password.length();i++){
            string+="*";
        }
        return string;
    }
}
