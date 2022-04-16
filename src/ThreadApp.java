import java.io.IOException;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class ThreadApp {

    static Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread th, Throwable ex) {
            System.out.println("Uncaught exception: " + ex);
        }
    };

    public static void main(String[] args) {
        try {
            Task t = new Task();

            t.start();
            t.run();
        } catch (Exception e) {
            System.out.println("Inside main exception ");
        }
        Task t2 = new Task();
        t2.setUncaughtExceptionHandler(h);
        t2.start();
        t2.run();
    }

}

class Task extends Thread {
    @Override
    public void run() {
        System.out.println("Inside run");
        throw new RuntimeException();
    }

    @Override
    public void start() {
        System.out.println("Inside start");

    }
}