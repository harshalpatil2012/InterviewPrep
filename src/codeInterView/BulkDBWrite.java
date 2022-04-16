package codeInterView;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BulkDBWrite {

    public static void main(String[] args) throws IOException {

        String fName = "C:\\Users\\harshal\\workspace\\InterviewPrep\\words.txt";
        String thisLine;
        FileInputStream fis = new FileInputStream(fName);
        DataInputStream myInput = new DataInputStream(fis);

        FileInputStream fis2 = new FileInputStream(fName);
        DataInputStream myInput2 = new DataInputStream(fis2);

        ExecutorService pool = Executors.newFixedThreadPool(1000);
        int count = 0; // Concurrent request to Server barrier

        while ((thisLine = myInput.readLine()) != null) {
            if (count > 150) {
                try {
                    Thread.sleep(100);
                    count = 0;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            pool.submit(new MyTask(thisLine));
            count++;
        }
    }
}

class MyTask implements Runnable {
    private String lLine;

    public MyTask(String line) {
        this.lLine = line;

    }

    public void run() {
        // 1) Create Request lLine
        // 2) send the HTTP request out and receive response
    }
}
