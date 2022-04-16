package codeInterView.collections;

public class GenericCls {

    public static void main(String[] args) {
        Box<Integer> intBox = new Box<Integer>();
        intBox.setType(new Integer(10));

        Box<String> strBox = new Box<String>();
        strBox.setType("Str Box");

        System.out.println(" Int Box value ::" + intBox.getType());
        System.out.println(" Str Box value ::" + strBox.getType());
    }
}

class Box<Type> {

    private Type t;

    public Type getType() {
        return t;
    }

    public void setType(Type t) {
        this.t = t;
    }
}
