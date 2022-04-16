package codeInterView.coreJava;

import java.lang.ref.WeakReference;

public class ReferenceTest {
    public static void main(String[] args) throws InterruptedException {

        WeakReference r = new WeakReference(new String("I'm here"));
        WeakReference sr = new WeakReference("I'm here static");
        WeakReference sr2 = new WeakReference(1);
        System.out.println("before gc: r=" + r.get() + ", static=" + sr.get());
        System.gc();
        Thread.sleep(100);

        // only r.get() becomes null
        System.out.println("after gc: new string r=" + r.get() + ", static=" + sr.get() + ", arraylist=" + sr2.get());

    }
}
