package Lesson_06.Ex_02.program;

import java.util.Comparator;
import java.util.Date;

public class ComporatorNotesByEmailData implements Comparator<Note> {
    @Override
    public int compare(Note o1, Note o2) {
        Date date1 = o1.getDate();
        Date date2 = o2.getDate();
        int strInt = o1.getEmail().compareTo(o2.getEmail());
        int rez = date1.compareTo(date2);
        if (strInt != 0) {
            return strInt;
        }
        if (rez != 0) {
            return rez;
        }
        return 0;
    }
}
