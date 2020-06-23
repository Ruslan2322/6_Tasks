package Lesson_06.Ex_01.User;

import Lesson_06.Ex_01.book.Book;
import Lesson_06.Ex_01.book.ElectronicBooks;

import java.io.*;
import java.util.*;

public class BooksAccount {

    private List<User> users = new ArrayList<User>();
    private static UserFactory factory=new UserFactory();
    private File file;
    private final String deffoltPath="src\\main\\resources\\User.txt";

    public BooksAccount(){
        file=new File(deffoltPath);
        createFile();
    }

    public BooksAccount(String pathToFile){
        if(pathToFile!=null && !pathToFile.isEmpty()){
            file=new File(pathToFile);
        }else{
            file=new File(deffoltPath);
        }
        createFile();
    }

    public void addUser(String login,String password){
        if(isValidLogin(login)) {
            User toAdd=factory.getUser(password, login);
            users.add(toAdd);
            addToFile(toAdd.toString()+"\n");
        }
    }

    public boolean login(String login,String password){
        if(!isValidLogin(login)){
            int index=0;
            for(int i=0;i<users.size();i++){
                if(users.get(i).getLogin().equals(login)){
                    index=i;
                    break;
                }
            }
            if(password.equals(users.get(index).getPassword())) {
                users.add(users.size(), users.get(index));
                users.remove(index);
                return true;
            }else{
                System.out.println("Entered wrong password!");
                return false;
            }
        }else{
            System.out.println("User "+login+" not found!");
            return false;
        }
    }

    public void removeUser(User user){
        users.remove(user);
        writeToFile();
    }

    public void menu(){
        int choice=1;
        Scanner scanner=new Scanner(System.in);
        try {
            while (choice != 0) {
                System.out.println("Enter 0, for exit from application");
                System.out.println("Enter 1, for login");
                System.out.println("Enter 2, for sign up in database");
                choice = scanner.nextInt();

                if(choice==1 || choice==2) {
                    boolean isLogged = false;

                    while (!isLogged) {
                        switch (choice) {
                            case 1: {
                                System.out.println("\nEnter login and password:");
                                isLogged = login(scanner.next(), scanner.next());
                                break;
                            }

                            case 2: {
                                System.out.println("\nEnter login and password:");
                                addUser(scanner.next(), scanner.next());
                                isLogged = true;
                                break;
                            }
                        }

                    }

                    if (users.get(users.size() - 1).isAdmin()) {
                        adminMenu();
                    } else {
                        deffoltUserMenu();
                    }
                }
            }
        }catch (InputMismatchException ex){
            System.out.println("Error ! Wrong opiration!");
            menu();
        }
    }

    private void deffoltUserMenu(){
        int choice=1;
        Scanner scanner=new Scanner(System.in);
        try {
            while (choice != 0) {
                System.out.println("Enter 0, for out");
                System.out.println("Enter 3, for get list of book on this page");
                System.out.println("Enter 4, for get of list on certain page");
                System.out.println("Enter 5, for find a book");
                System.out.println("Enter 6, for find all books");
                System.out.println("Enter 7, for check post");
                System.out.println("Enter 8, for recomend book");
                System.out.println("Enter 9, for get description of book");
                choice = scanner.nextInt();
                switch (choice) {
                    case 3: {
                        users.get(users.size() - 1).print();
                        break;
                    }

                    case 4: {
                        System.out.println("\nEnter number of page :");
                        users.get(users.size() - 1).print(scanner.nextInt());
                        break;
                    }

                    case 5: {
                        System.out.println("\nEnter author, book name, number of page");
                        users.get(users.size() - 1).findBook(new Book(scanner.next(), scanner.next(), scanner.nextInt()));
                        break;
                    }

                    case 6: {
                        System.out.println("\nEnter author");
                        users.get(users.size() - 1).findBook(scanner.next());
                        break;
                    }

                    case 7: {
                        users.get(users.size() - 1).checkMail();
                        break;
                    }

                    case 8: {
                        System.out.println("\nEnter author, book name, number of page");
                        users.get(users.size() - 1).adviceAdmin(scanner.next() + " " + scanner.next() + " " + scanner.nextInt());
                        break;
                    }

                    case 9: {
                        System.out.println("\nEnter serial number of book and description on this page");
                        users.get(users.size() - 1).getDescription(scanner.nextInt());
                        break;
                    }
                }
            }
        } catch (InputMismatchException ex){
            System.out.println("Error! wrong opperation!");
            deffoltUserMenu();
        }
    }

    //Админ панель
    private void adminMenu(){
        int choice=1;
        Scanner scanner=new Scanner(System.in);
        try {
            while (choice != 0) {
                System.out.println("Enter 0, for out");
                System.out.println("Enter 3, for get list of books this page ");
                System.out.println("Enter 4, for get list of books page on certain page");
                System.out.println("Enter 5, for find a book");
                System.out.println("Enter 6, for find all books of authors");
                System.out.println("Enter 7, for get description of book");
                System.out.println("Enter 8, for add the book");
                System.out.println("Enter 9, for add electronic book");
                System.out.println("Enter 10, for delete the book");
                System.out.println("Enter 11, for answer on recomendation of user");
                System.out.println("Enter 12, for change the password like admin");
                System.out.println("Enter 13, for change the description");
                choice = scanner.nextInt();
                switch (choice) {
                    case 3: {
                        users.get(users.size() - 1).print();
                        break;
                    }

                    case 4: {
                        System.out.println("\nEnter number of page:");
                        users.get(users.size() - 1).print(scanner.nextInt());
                        break;
                    }

                    case 5: {
                        System.out.println("\nEnter author, book name, count of page, new address");
                        users.get(users.size() - 1).findBook(new Book(scanner.next(), scanner.next(), scanner.nextInt()));
                        break;
                    }

                    case 6: {
                        System.out.println("\nEnter author");
                        users.get(users.size() - 1).findBook(scanner.next());
                        break;
                    }

                    case 7: {
                        System.out.println("\nEnter serial number of book and description on this page ");
                        users.get(users.size() - 1).getDescription(scanner.nextInt());
                        break;
                    }

                    case 8: {
                        System.out.println("\nEnter author, book name, count of page");
                        Book book = new Book(scanner.next(), scanner.next(), scanner.nextInt());
                        ((Admin) users.get(users.size() - 1)).addBook(book);
                        messageAllUsers(book.toString()+"\t\tadd catalog\n");
                        break;
                    }

                    case 9: {
                        System.out.println("\nEnter author, book name, count of page, new address ");
                        ElectronicBooks ebook=new ElectronicBooks(scanner.next(), scanner.next(), scanner.nextInt());
                        ebook.setResource(scanner.next());
                        ((Admin) users.get(users.size() - 1)).addBook(ebook);
                        messageAllUsers(ebook.toString()+"\t\tadd catalog\n");
                        break;
                    }

                    case 10: {
                        System.out.println("\nEnter author, book name, amount of pages");
                        ((Admin) users.get(users.size() - 1)).removeAll(new Book(scanner.next(), scanner.next(), scanner.nextInt()));
                        break;
                    }

                    case 11: {
                        users.get(users.size() - 1).checkMail();
                        break;
                    }

                    case 12: {
                        System.out.println("\nEnter password, the enter new passsword");
                        ((Admin) users.get(users.size() - 1)).changePassword(scanner.next(), scanner.next());
                        break;
                    }

                    case 13: {
                        System.out.println("\nEnter serial number of book and description on this page ");
                        int num=scanner.nextInt();
                        ((Admin) users.get(users.size() - 1)).setDescription(num, scanner.next());
                        Book book=users.get(users.size()-1).getNthBook(num);
                        if (book != null) {
                            messageAllUsers(book.toString()+"\t\tChange description \n");
                        }
                        break;
                    }
                }
            }
        } catch (InputMismatchException ex){
            System.out.println("Error! Wrong operation!");
            adminMenu();
        }
    }

    private void messageAllUsers(String string){
        for (User user : users) {
            if (!user.isAdmin()) {
                user.addMessage(string);
            }
        }
    }

    private boolean isValidLogin(String login){
        boolean isInvalid=true;
        for(User user:users){
            if(user.getLogin().equals(login)){
                isInvalid=false;
                break;
            }
        }
        return isInvalid;
    }

    private void addToFile(String content){
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(content);
            writer.flush();
            writer.close();

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void writeToFile(){
        try {
            FileWriter writer = new FileWriter(file);
            for(User user:users){
                writer.write(user.toString()+"\n");
            }
            writer.flush();
            writer.close();

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private boolean createFile(){
        if(!file.exists()){
            try {
                return file.createNewFile();
            }catch (IOException ex){
                return false;
            }
        }else{
            cleanFile();
            return false;
        }
    }

    private void cleanFile(){
        try {
            FileWriter cleaner = new FileWriter(file);
            cleaner.close();
        } catch (IOException ex){
            System.out.println(ex);
        }
    }
}