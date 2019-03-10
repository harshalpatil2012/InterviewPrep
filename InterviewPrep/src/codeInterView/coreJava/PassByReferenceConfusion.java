package codeInterView.coreJava;

public class PassByReferenceConfusion {

    public static void main(String args[]) {
        Car car = new Car("BMW");
        int a = 3;
        System.out.println("1 Brand of Car Inside main() before: " + car.brand + " a::" + a);
        printBrand(car, a);
        System.out.println("2 Brand of Car Inside main()after: " + car.brand + " a::" + a);

        printBrandAgaian(car);
        System.out.println("printBrandAgaian Brand of Car Inside main()after: " + car.brand);
    }

    public static void printBrand(Car car, int a) {
        car.brand = "Maruti";
        a = 4;
        System.out.println("Brand of Car Inside printBrand(): " + car.brand + " a::" + a);
    }

    public static void printBrandAgaian(Car car3) {
        car3 = new Car("Mercdes");
        System.out.println("Brand of Car Inside printBrandAgain(): " + car3.brand);
    }

    private static class Car {
        private String brand;

        public Car(String brand) {
            this.brand = brand;
        }

    }
}
