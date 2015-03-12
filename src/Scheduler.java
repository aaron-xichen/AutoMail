import sun.util.calendar.CalendarDate;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by cxlyc007 on 3/12/15.
 */
public class Scheduler {
    private Timer timer;
    private Date dateFirst;
    private long period;

    public Scheduler(Date dateFirst, long period) {
        this.timer = new Timer();
        this.dateFirst = dateFirst;
        this.period = period;
    }

    public void active() {
        this.timer.schedule(new RoutineTask(), this.dateFirst, this.period);
    }

    public static Date getDate(int hour, int min, int second) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min);
        c.set(Calendar.SECOND, second);
        return c.getTime();
    }

    public static void printUsage() {
        System.out.println("-----------------------------------------------");
        System.out.println("Usage: Scheduler [hour:min:second] [period]   |");
        System.out.println("Default Value:                                |");
        System.out.println("           hour:min:second=23:59:59           |");
        System.out.println("           peroid=86400000                    |");
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("Routine is running...");
        Date dateFirst = Scheduler.getDate(23, 59, 59); // send every mid-night 23:59:59
        long period = 86400000;

        // check the usage
        if (0 != args.length) {
            if (1 == args.length || 2 == args.length) {
                String[] fields = args[0].split(":");
                if (3 == fields.length) {
                    int hour = Integer.parseInt(fields[0]);
                    int min = Integer.parseInt(fields[1]);
                    int second = Integer.parseInt(fields[2]);
                    dateFirst = Scheduler.getDate(hour, min, second);
                } else {
                    Scheduler.printUsage();
                    System.out.println("Abort");
                    return;
                }

                if (2 == args.length) {
                    period = Integer.parseInt(args[1]);
                }
            } else {
                Scheduler.printUsage();
                System.out.println("Abort");
                return;
            }
        }

        // begin to work
        Scheduler scheduler = new Scheduler(dateFirst, period);
        scheduler.active();
    }
}
