
public class Calculator {
    
    public static int sum(int n1, int n2) {
        return n1 + n2;
    }

    public static int sub(int n1, int n2) {
        return n1 - n2;
    }
    
    public static int div(int n1, int n2) {
        try {
            return n1 / n2;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public static int mul(int n1, int n2) {
        return n1 * n2;
    }
    
    public static int mod(int n1, int n2) {
        return n1 % n2;
    }
}
