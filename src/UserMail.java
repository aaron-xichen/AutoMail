/**
 * Created by cxlyc007 on 3/11/15.
 * structure to store the info of the user mail
 */

public class UserMail {
    static String user;
    static String password;
    static String pop3Receiver;
    static String smtpSender;

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        UserMail.user = user;
    }

    public static String getPop3Receiver() {
        return pop3Receiver;
    }

    public static void setPop3Receiver(String pop3Receiver) {
        UserMail.pop3Receiver = pop3Receiver;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserMail.password = password;
    }

    public static String getSmtpSender() {
        return smtpSender;
    }

    public static void setSmtpSender(String smtpSender) {
        UserMail.smtpSender = smtpSender;
    }


}
