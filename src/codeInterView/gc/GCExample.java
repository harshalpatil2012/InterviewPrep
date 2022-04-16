package codeInterView.gc;

public class GCExample {

    public static void main(String[] args) {
        String str = "ABC";
        GCExample gc = new GCExample();
        System.gc();

        gc = null;
        System.out.println("call GC 2nd time");
        System.gc();
        str = null;
        System.out.println("Inside main::" + str);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Inside Finalize");
    }
}
