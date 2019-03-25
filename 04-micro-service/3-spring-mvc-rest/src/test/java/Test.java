import java.util.Arrays;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
//        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0] + t[1]})
//                .limit(20)
//                .map(t -> t[0])
//                .forEach(System.out::println);
//        System.out.println("==============");
//        jdk7(new int[]{0, 1});
        for (int i = 1; i<=20; i++) {
            System.out.println(duigui(i));
        }
    }

    private static void jdk7(int[] original){

        System.out.println(original[0]);
        System.out.println(original[1]);
        for (int i =  0; i < 20; i++) {
            original = new int[]{original[1], original[0] + original[1]};
            System.out.println(original[1]);

        }
    }

    private static int duigui(int i) {

        if (i == 1) {
            return 0;
        } else if (i == 2) {
            return 1;
        } else {
            return duigui(i-2) + duigui(i-1);
        }
    }
}
