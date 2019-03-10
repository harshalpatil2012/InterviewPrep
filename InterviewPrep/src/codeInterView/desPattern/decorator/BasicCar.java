package codeInterView.desPattern.decorator;

interface Car {
    public void assemble();
}

public class BasicCar implements Car {

    @Override
    public void assemble() {
        System.out.print("Basic Car.");
    }

}
