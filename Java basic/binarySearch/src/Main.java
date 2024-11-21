import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int index = Collections.binarySearch(list, 3);
        System.out.println("Index of 3: " + index);
    }
}
