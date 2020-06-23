package Lesson_06.Ex_03;

import java.util.Calendar;

/*
    Класс для представления дела студента
    Возможности:
    1) Изменение курса, факультета
    2) Добавление характеристики
    3) Изменение статуса студента
    4) Вывод на консоль
    P.S. Хранит номер дела (=порядковый номер)
    и время создания дела
 */

public class Deed {

    private Student student=new Student();
    private static int counter=0;
    private int numOfDeed=++counter;
    private Calendar dateOfCreating= Calendar.getInstance();
    private String characteristic="У студента отсутствует характиристика";

    public Deed(String name,String faculty,int course,int yearOfEnrolling){
        student=new Student(name, faculty, course, yearOfEnrolling);
        if(course>Student.TRAINING_PERIOD){
            student.setStatus(StudentStatus.GRADUATED);
        }
    }

    public void changeFaculty(String faculty){
        if(student.getStatus()==StudentStatus.ENROLLED) {
            student.setFaculty(faculty);
        }else{
            System.out.println("This student is graduated or expelled! ");
        }
    }

    public boolean incCourse(){
        if(student.getStatus()==StudentStatus.ENROLLED) {
            if (!student.incCourse()) {
                student.setStatus(StudentStatus.GRADUATED);
                return false;
            }else{
                return true;
            }
        }else{
            System.out.println("This student is graduated or expelled! ");
            return false;
        }
    }

    public void changeCourse(int course){
        if(student.getStatus()==StudentStatus.ENROLLED) {
            student.setCourse(course);
        }else{
            System.out.println("This student is graduated or expelled!");
        }
    }

    public void setCharacteristic(String characteristic){
        if(student.getStatus()==StudentStatus.ENROLLED) {
            if (characteristic != null && !characteristic.isEmpty()) {
                this.characteristic = characteristic;
            }
        }else{
            System.out.println("This student is graduated or expelled!");
        }
    }

    public String getCharacteristic(){
        return characteristic;
    }

    public String getName(){
        return student.getName();
    }

    public String getFaculty(){
        return student.getFaculty();
    }

    public int getNumOfDeed(){
        return numOfDeed;
    }

    public int getCourse(){
        return student.getCourse();
    }

    public int getYearOfEnrolling(){
        return student.getYearOfEnrolling();
    }

    public StudentStatus getStatus(){
        return student.getStatus();
    }

    //Отчислить студента
    public void dudicate(){
        if(student.getStatus()==StudentStatus.ENROLLED) {
            student.setStatus(StudentStatus.DEDUCTED);
        }else{
            System.out.println("This student is graduated or expelled!");
        }
    }

    //Вручить студенту диплом об окончании
    public void graduate(){
        if(student.getStatus()==StudentStatus.ENROLLED) {
            student.setStatus(StudentStatus.GRADUATED);
        }else{
            System.out.println("This student is graduated or expelled!");
        }
    }

    //Зачислить студента
    public void enroll(){
        if(student.getStatus()!=StudentStatus.ENROLLED){
            student.setStatus(StudentStatus.ENROLLED);
        }else{
            System.out.println("This student is already enrolled!");
        }
    }

    @Override
    public String toString(){
        String string=new String();
        string+="Created "+dateOfCreating.getTime().toString()+"\n";
        string+="Matter №"+numOfDeed+"\n";
        string+="Name: "+student.getName()+"\n";
        string+="Faculty: "+student.getFaculty()+"\n";
        if(student.getStatus()==StudentStatus.ENROLLED){
            string+="Course: "+student.getCourse()+"\n";
        }
        string+="Year of enrollment: "+student.getYearOfEnrolling()+"\n";
        string+="Status: "+student.getStatus()+"\n\n";
        string+="Characteristic:\n"+characteristic+"\n";
        return string;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }

        if(obj==null || obj.getClass() != this.getClass()){
            return false;
        }

        Deed other=(Deed) obj;

        return student.equals(other.student) && characteristic.equals(other.characteristic);
    }

    @Override
    public int hashCode(){
        final int prime=31;
        int result=1;

        result=prime*result+((characteristic==null)?0:characteristic.hashCode());
        result=prime*result+student.hashCode();

        return result;
    }
}