import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class TIME {
    int n;

    public TIME(int n) {
        this.n = n;
    }

    public TIME(TIME time){
        this.n = time.n;
    }


    public static int diff(TIME time1, TIME time2){
        return Math.abs(time1.n- time2.n);
    }

    public static JPanel showTime(int x, int y, int width, int height){
        JPanel timeInfo = new JPanel();
        timeInfo.setBounds(x,y,width,height);
        JLabel showDate = new JLabel();
        Thread t1 = new Thread() {
            public void run() {
                try {
                    for(;;) {
                        Calendar cal = Calendar.getInstance();
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        int minute = cal.get(Calendar.MINUTE);
                        int second = cal.get(Calendar.SECOND);
                        showDate.setText("Date: "+cal.get(Calendar.YEAR)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.DATE)+
                                "                                                                          " +
                                "                                                         " +
                                "Time: "+hour + ":" + minute + ":" + second);
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };t1.start();
        showDate.setBackground(null);
        showDate.setFont(new Font(Font.SERIF,Font.ITALIC,20));
        timeInfo.add(showDate);
        timeInfo.setBorder(BorderFactory.createLineBorder(Color.black,5));
        timeInfo.setBackground(new Color(200,255,200));
        return timeInfo;
    }
}
