package Lesson_06.Ex_01.book;

import java.util.regex.*;

public class ElectronicBooks extends Book {

    private String resource="http://elib.bsu.by/";

    public ElectronicBooks(String author, String name, int pages){
        super(author, name, pages);
    }

    public void setResource(String resource){
        Pattern urlPattern=Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher urlMatcher=urlPattern.matcher(resource);
        if(urlMatcher.find()){
            this.resource=resource;
        }
    }

    public String getResource(){
        return resource;
    }

    @Override
    public String toString(){
        return super.toString()+"\t"+resource;
    }
}
