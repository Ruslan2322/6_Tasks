package Lesson_06.Ex_02.program;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Note implements Comparator<Note> {
    private String theme;
    private Date date;
    private String email;
    private String mesage;

    public Note(String theme, Date date, String email, String mesage) {
        this.theme = theme;
        this.date = date;
        this.email = email;
        this.mesage = mesage;
    }

    public Note() {
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        return "theme=" + theme + "|" + ", date=" + strDate + "|" + ", email=" + email + "|" + ", mesage=" + mesage
                + "|";
    }

    @Override
    public int compare(Note o1, Note o2) {

        int strInt = o1.getTheme().compareTo(o2.getTheme());

        if (strInt != 0) {
            return strInt;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Note note = (Note) o;

        if (theme != null ? !theme.equals(note.theme) : note.theme != null)
            return false;
        if (date != null ? !date.equals(note.date) : note.date != null)
            return false;
        if (email != null ? !email.equals(note.email) : note.email != null)
            return false;
        return mesage != null ? mesage.equals(note.mesage) : note.mesage == null;
    }

    @Override
    public int hashCode() {
        int result = theme != null ? theme.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mesage != null ? mesage.hashCode() : 0);
        return result;
    }
}
