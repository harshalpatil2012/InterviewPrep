package codeInterView.string;

public class StrRev {

    public static void main(String[] args) {

        String str = "abcde";
        char strArr[] = str.toCharArray();
        char temp;
        for (int i = 0, j = strArr.length - 1; i <= (strArr.length / 2); i++, j--) {
            temp = strArr[i];
            strArr[i] = strArr[j];
            strArr[j] = temp;
            System.out.println(" str i =>" + strArr[i]);
        }
        System.out.println("rev str =>" + new String(strArr));
    }
}
