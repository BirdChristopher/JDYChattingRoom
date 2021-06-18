package center;

import java.util.Date;

public class Counter implements Runnable {
    public void run() {
        while(true){
            synchronized (System.out){
                System.out.println(new Date());
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
