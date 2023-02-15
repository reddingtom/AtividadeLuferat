package trecos.exercicios;

public class MultiTable {

    public static String table(int n1) {
        for (int i = 0; i <= 10; i++) {
            return i + " x " + n1 + " = " + (i * n1);
        }
        return null;
    }

    public static String tableAll() {
        for (int i = 0; i <= 10; i++) {
            for (int ii = 0; ii <= 10; ii++) {
                return i + " x " + ii + " = " + (i * ii);
            }
        }
        return null;
    }
}
