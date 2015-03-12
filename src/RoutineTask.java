import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

/**
 * Created by cxlyc007 on 3/12/15.
 */
public class RoutineTask extends TimerTask {
    @Override
    public void run() {
        try {
            Set<String> toWhom = Config.readSendingSet();
            Record one = Config.pickOneRamdonly();

            if (null == one) {
                System.out.println("problems set is empty");
                return;
            }

            // print info
            System.out.println("Sending message:");
            System.out.println("---------------");
            System.out.println(one.format());
            System.out.println("---------------");
            System.out.println("To:");
            System.out.println(toWhom.toString());
            System.out.println("---------------");


            String eTitle = "New LeetCode problem for today";
            String eBody = one.format();

            List<String> userMailConfig = Config.readAccountConfig();

            UserMail.setUser(userMailConfig.get(0));
            UserMail.setPassword(userMailConfig.get(1));
            UserMail.setSmtpSender(userMailConfig.get(2));

            Mail mail = new Mail();
            boolean isSuccessful = mail.SendMail(toWhom, eTitle, eBody);
            // boolean isSuccessful = true;
            if (isSuccessful) {
                System.out.println("Success!");
            } else {
                System.out.println("Fail!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
