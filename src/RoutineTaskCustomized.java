import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.Date;

/**
 * Created by cxlyc007 on 3/12/15.
 * pick the problems according to the date
 * Monday, Tusday, Wednesday - Easy Problem
 * Thursday, Friday - Medium Probem
 * Saturday, Sunday - Hard Problem
 */
public class RoutineTaskCustomized extends TimerTask {
    @Override
    public void run() {
        try {
            Date exeTime = new Date(this.scheduledExecutionTime());
            Scheduler.DayOfWeek day = Scheduler.getWeek(exeTime);

            // read sending_list.txt
            Set<String> toWhom = Config.readSendingSet();

            // pick a problem according to the date
            String diffculty;
            if (Scheduler.DayOfWeek.MON == day || Scheduler.DayOfWeek.TUE == day
                    || Scheduler.DayOfWeek.WED == day) {
                diffculty = "easy";
            } else if (Scheduler.DayOfWeek.THU == day ||
                       Scheduler.DayOfWeek.FRI == day) {
                diffculty = "medium";
            } else if (Scheduler.DayOfWeek.SAT == day ||
                       Scheduler.DayOfWeek.SUN == day) {
                diffculty = "hard";
            } else {
                System.out.println("date: " + day + " is out of range");
                return;
            }

            Record one = Config.pickOneByDifficulty(diffculty);

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
            // boolean isSuccessful = mail.SendMail(toWhom, eTitle, eBody);
            boolean isSuccessful = true;
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
