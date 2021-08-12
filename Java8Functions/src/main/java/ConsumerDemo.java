import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {

    private static final Consumer<Integer> print = ConsumerInterface::print;
    private static final Consumer<List<Integer>> display = ConsumerInterface::display;
    private static final Consumer<List<Integer>> modify = ConsumerInterface::modify;

    public static void main(String[] args) {

        // 1 : basic accept demo
        Consumer<Object> print  = o -> System.out.println(o);
        print.accept("Bangalore");
        print.accept(123);

        // 2 : andThen
        Consumer<List<Integer>> modify = list -> list.stream().forEach( i -> list.set(list.indexOf(i), 2 * i));
        Consumer<List<Integer>> display = list -> list.stream().forEach(i -> System.out.println(i));

        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3);

        modify.andThen(display).accept(list);



        // 3 : andThen and Exception
        Consumer<Integer> square = n -> n = n / 0;
        try {
            square.accept(2);
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        // 4 : refactoring above examples
        ConsumerDemo.print.accept(10);
        ConsumerDemo.modify.andThen(ConsumerDemo.display).accept(list);
    }


}

class ConsumerInterface{

    public static void print(Object o){
        System.out.println(o);
    }

    public static void modify(List<Integer> list){
        list.stream().forEach(i -> list.set(list.indexOf(i), 2 * i));
    }

    public static void display(List<Integer> list){
        list.stream().forEach(i -> System.out.println(i));
    }



}