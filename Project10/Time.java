import java.util.Scanner;

public class Time implements Comparable<Time>, Cloneable{
    private long elaTime;
    public Time(){
        this.elaTime = System.currentTimeMillis()/1000;
    }
    public Time(int h, int m, int s){
        elaTime = (h * 60 * 60) + (m * 60) + s;
    }
    public Time(long elaTime){
        this.elaTime = elaTime;
    }
    public int getHour(){
        int hour = ((int)(elaTime/60/60))%24;
        return hour;
    }
    public int getMinute(){
        int minute = ((int)(elaTime/60%60));
        return minute;
    }
    public int getSecond(){
        int second = (int)(elaTime%60);
        return second;
    }
    public long getSeconds() {
        return elaTime;
    }
    public String toString(){
        String str = getHour() + " hour" + (getHour() > 1 ? "s " : " ") + getMinute() + " minute" + (getMinute() > 1 ? "s " : " ") + getSecond() + " second" + (getSecond() > 1 ? "s" : "");
        return str;
    }

    @Override
    public int compareTo(Time o) {
        return (int) (this.elaTime - o.getSeconds());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner input = new Scanner(System.in);
        int h, m, s;
        long eTime;
        System.out.print("Enter time1 (hour minute second): ");
            try {
                h = input.nextInt();
                m = input.nextInt();
                s = input.nextInt();
                Time t1 = new Time(h, m, s);
                System.out.println("\n" + t1.toString());
                System.out.println("\nElapsed seconds in time1: " + t1.getSeconds());
                System.out.print("\nEnter time2 (elapsed time in seconds): ");
                eTime = input.nextLong();
                Time t2 = new Time(eTime);
                System.out.println("\n" + t2.toString());
                System.out.println("\nElapsed seconds in time2: " + t2.getSeconds());
                System.out.println("\ntime1.compareTo(time2)? " + t1.compareTo(t2));
                Time t3 = (Time) t1.clone();
                System.out.println("\ntime3 is created as a clone of time1");
                System.out.println("time1.compareTo(time3)? " + t1.compareTo(t3));
            } catch (Exception e) {
                System.out.println("Invalid Input. Run Again");
            }
    }
}
