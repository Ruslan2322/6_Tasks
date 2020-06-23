package Lesson_06.Ex_01.book;

import java.io.*;
import java.util.*;

public class BookDatabase {

    private Map<Integer,List<Book>> catalog=new HashMap<Integer, List<Book>>();
    private static int page=1;
    private static int currPage=1;
    private File file;
    private final String deffoltPath="src\\main\\resources\\Database.txt";

    public BookDatabase(){
        file=new File(deffoltPath);
        createFile();
    }

    public BookDatabase(String pathToFile){
        if(pathToFile!=null && !pathToFile.isEmpty()){
            file=new File(pathToFile);
        }else{
            file=new File(deffoltPath);
        }
        createFile();
    }

    public void addBook(Book book){
        if(book!=null) {
            if (!catalog.containsKey(page)) {
                catalog.put(page, new ArrayList<Book>());
                catalog.get(page).add(book);
            } else {
                if (catalog.get(page).size() > 9) {
                    page++;
                    catalog.put(page, new ArrayList<Book>());
                }
                catalog.get(page).add(book);
            }
            currPage = page;
            addToFile(book.toString() + "\n");
        }
    }

    public void addBook(String author,String name,int pages){
        addBook(new Book(author, name, pages));
    }

    public void addElectronicBook(String author,String name,int pages,String resourse){
        ElectronicBooks eBook=new ElectronicBooks(author, name, pages);
        eBook.setResource(resourse);
        addBook(eBook);
    }

    public void removeBook(Book book){
        int removePage = -1;
        for(Integer i:catalog.keySet()){
            if(catalog.get(i).contains(book)){
                removePage=i;
                break;
            }
        }
        if(removePage!=-1) {
            currPage=removePage;
            catalog.get(removePage).remove(book);
            while (catalog.containsKey(removePage + 1)) {
                catalog.get(removePage).add(catalog.get(removePage + 1).get(0));
                catalog.get(removePage + 1).remove(0);
                removePage++;
            }
            writeToFile();
        }
    }

    public void removeAll(Book book){
        for(Integer i:catalog.keySet()){
            while(catalog.get(i).contains(book)){
                removeBook(book);
            }
        }
    }

    public List<Book> findBook(String author){
        List<Book> authorsBooks=new ArrayList<Book>();
        if(catalog.size()>0) {
            for (Integer i : catalog.keySet()) {
                for (Book book : catalog.get(i)) {
                    if (book.getAuthor().equals(author)) {
                        authorsBooks.add(book);
                    }
                }
            }
        }
        return authorsBooks;
    }

    public List<Book> findBook(Book findingBook){
        List<Book> books=new ArrayList<Book>();
        if(catalog.size()>0) {
            for (Integer i : catalog.keySet()) {
                for (Book book : catalog.get(i)) {
                    if (book.equals(findingBook)) {
                        books.add(book);
                    }
                }
            }
        }
        return books;
    }


    public void setDescription(int bookNum,String description){
        if(catalog.size()>0 && bookNum>0 && bookNum<=catalog.get(currPage).size()){
            catalog.get(currPage).get(bookNum-1).addDescription(description);
        }
    }

    public String getDescription(int bookNum){
        if(catalog.size()>0 && bookNum>0 && bookNum<=catalog.get(currPage).size()){
            return catalog.get(currPage).get(bookNum-1).getDescription();
        }else{
            return "Wrong book number!";
        }
    }

    public Book getNthBook(int bookNum){
        if(catalog.size()>0 && bookNum>0 && bookNum<=catalog.get(currPage).size()){
            return catalog.get(currPage).get(bookNum-1);
        }else{
            return null;
        }
    }

    public void print(int page){
        if(catalog.containsKey(page)){
            System.out.println("--------------------------"+page+"---------------------------");
            for(int i=0;i<catalog.get(page).size();i++){
                System.out.println((i+1)+"\t\t"+catalog.get(page).get(i).toString());
            }
            System.out.println("--------------------------"+page+"---------------------------");
        }
    }

    public void print(){
        print(currPage);
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
            for(Integer i:catalog.keySet()){
                for(Book book:catalog.get(i)){
                    writer.write(book.toString()+"\n");
                }
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
