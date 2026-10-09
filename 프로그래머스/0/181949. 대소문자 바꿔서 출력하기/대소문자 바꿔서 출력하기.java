import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        var arr = a.toCharArray();
        
        for (var i = 0; i < arr.length; i++) {
            arr[i] = (char) (arr[i] ^ 32);
        }
        System.out.println(new String(arr));
    }
}