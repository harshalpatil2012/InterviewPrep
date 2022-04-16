
package codeInterView.dataStruture.sorting;

public class BubbleSort {

    public static void main(String[] args) {
        int arr[] = { 3, 44, 76, 9, 88, 55, 4 };
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {

                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(" arr:" + arr[i]);

        }
    }
}
