package trecos.exercicios;

public class Simile {
    
    public String simile(int num) {
        if(num % 2 == 1) return "Ímpar"; else return "Par";
    }
    
        public static void main(String[] args) {
        for(int i = 0; i <= 10; i++) {
            System.out.println(new Simile().simile(i));
        }
    }
}