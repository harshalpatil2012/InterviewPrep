package codeInterView.thread;

public class ConcurrencyTest {
    public static void main(String[] args) {
        ConcurrencyTest test = new ConcurrencyTest();
        test.display();
        test.print();
        Exception exc = new Exception();
        exc.getCause();
    }

    public synchronized void display() {

        System.out.println("Inside Sync.display ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("Inside print method ");
    }
}
