import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class main {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer i : list) {
            System.out.println(i);
            if (i == 2) {
                // Modify the original list
                list.add(4);
            }
        }
        System.out.println(list);
    }
}
