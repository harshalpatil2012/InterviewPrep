package codeInterView.dataStruture;

public class SecondLargest {

    public static void main(String[] args) {

        int arr[] = { 14, 46, 47, 86, 92, 52, 48, 36, 66, 102, 101, 102 };
        int largest = arr[0];
        int secondLargest = arr[0];

        System.out.println("The given array is:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];

            } else if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];

            }
        }

        System.out.println("\nSecond largest number is:" + secondLargest);

    }
}
