import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Number number1 = new Number(1);
        Number number2 = new Number(-3);
        Number number3 = new Number(-9);
        Number number4 = new Number(0);
        Number number5 = new Number(892);
        List<Number> numbers = new ArrayList<>();
        numbers.add(number1);
        numbers.add(number2);
        numbers.add(number3);
        numbers.add(number4);
        numbers.add(number5);

        System.out.println("Задание 1");
        System.out.println("P_R_E_D_I_C_A_T_E(anon.class)");

        //Реализация predicate через анонимный класс
        Predicate<Number> predicate = new Predicate<>() {
            @Override
            public boolean test(Number number) {
                return number.getNumber() > 0;
            }
        };

        for (Number number : numbers) {
            System.out.println(predicate.test(number));
        }
        System.out.println("");
        //

        //Реализация predicate через лямбду
        System.out.println("P_R_E_D_I_C_A_T_E(lambda.class)");
        Predicate<Number> predicateL = number -> {
            return number.getNumber() > 0;
        };
        for (Number number : numbers) {
            System.out.println(predicateL.test(number));
        }
        System.out.println("__________________");


        System.out.println("Задание 2");
        System.out.println("C_O_N_S_U_M_E_R(anon.class)");
        Person person1 = new Person("Иван", 19);
        Person person2 = new Person("Алексей", 9);
        Person person3 = new Person("Михаил", 11);
        Person person4 = new Person("Ксения", 42);
        Person person5 = new Person("Алина", 23);

        HashSet<Person> people = new HashSet<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        people.add(person5);

        Consumer<Person> consumer = new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println(person.getName());
            }
        };
        for (Person person : people) {
            consumer.accept(person);
        }
        System.out.println("");
        System.out.println("C_O_N_S_U_M_E_R(lambda.class)");
        Consumer<Person> consumer1 = person -> System.out.println(person.getName());
        for (Person person : people) {
            consumer.accept(person);
        }
        System.out.println("__________________");

        System.out.println("Задание 3");
        System.out.println("F_U_N_C_T_I_O_N(anon.class)");
        DoubleNumber dubNum1 = new DoubleNumber(19435.434D);
        DoubleNumber dubNum2 = new DoubleNumber(2145.13D);
        DoubleNumber dubNum3 = new DoubleNumber(3.1123D);
        DoubleNumber dubNum4 = new DoubleNumber(0.87D);
        DoubleNumber dubNum5 = new DoubleNumber(16.712D);

        HashSet<DoubleNumber> result = new HashSet<>();
        result.add(dubNum1);
        result.add(dubNum2);
        result.add(dubNum3);
        result.add(dubNum4);
        result.add(dubNum5);

        Function<DoubleNumber, Long> converter = new Function<DoubleNumber, Long>() {
            @Override
            public Long apply(DoubleNumber doubleNumber) {
                return doubleNumber.getDoubleNum().longValue();
            }
        };
        for (DoubleNumber doubleNumber : result) {
            System.out.println(converter.apply(doubleNumber));
        }
        System.out.println("");

        System.out.println("F_U_N_C_T_I_O_N(lambda.class)");
        Function<DoubleNumber, Long> converter1 = doubleNumber -> doubleNumber.getDoubleNum().longValue();
        for (DoubleNumber doubleNumber : result) {
            System.out.println(converter1.apply(doubleNumber));
        }
        System.out.println("__________________");

        System.out.println("Задание 4");
        System.out.println("S_U_P_P_L_I_E_R(anon.class)");

        Integer[] nums = new Integer[101];
        for (int i = 0; i < 101; i++)
            nums[i] = i;
        List<Integer> random = Arrays.asList(nums);

        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                int a = nums[0];
                int b = nums[100];
                int randomNumber = a + (int) (Math.random() * b);
                System.out.println(randomNumber);
                return randomNumber;
            }
        };
        supplier.get();
        System.out.println("");
        System.out.println("S_U_P_P_L_I_E_R(anon.class)");
        Supplier<Integer> supplier1 = () -> {
            int a = nums[0];
            int b = nums[100];
            int randomNumber = a + (int) (Math.random() * b);
            System.out.println(randomNumber);
            return randomNumber;
        };
        supplier1.get();
        System.out.println("__________________");

        System.out.println("Задание 5");
        System.out.println("Объединение интерфейсов");
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);

        System.out.println(safeStringLength);
        System.out.println("__________________");
    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
    }
}
