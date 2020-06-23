package Lesson_06.Ex_01.User;

public class UserFactory {

    private static String password="123456789";

    public User getUser(String parole,String login){
        if(password.equals(parole)){
            return new Admin(login,parole);
        }else{
            return new User(login,parole);
        }
    }

    protected static void changePassword(String oldPassword,String newPassword){
        if(oldPassword.equals(password) && newPassword!=null && !newPassword.isEmpty()){
            password=newPassword;
        }
    }
}
