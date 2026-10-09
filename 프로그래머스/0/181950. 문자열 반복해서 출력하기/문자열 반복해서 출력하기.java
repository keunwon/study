import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int n = sc.nextInt();
        
        var sb = new StringBuilder(str.length() * n);
        sb.repeat(str, n);
        
        System.out.println(sb.toString());
    }
}