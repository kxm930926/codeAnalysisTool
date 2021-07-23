import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner reader = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("读取表达式异常，请重新输入");
        }
    }
}
