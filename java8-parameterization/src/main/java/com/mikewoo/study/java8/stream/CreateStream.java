package com.mikewoo.study.java8.stream;

import com.mikewoo.study.java8.domain.Dish;
import com.mikewoo.study.java8.lambda.DishSuppiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Eric Gui
 * @date 2018/7/26
 */
public class CreateStream {


    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hyman", "Eric", "Phantom", "Skye");
        createStreamFromCollections(list).forEach(System.out::println);
        System.out.println("======================");
        createStreamFromValues("Hyman", "Eric", "Phantom", "Skye").forEach(System.out::println);

        System.out.println("======================");
        String[] strings = {"Hyman", "Eric", "Phantom", "Skye"};
        createStreamFromArrays(strings).forEach(System.out::println);

        System.out.println("======================");
        String filePath = "F:\\Programs\\github\\java8-in-action\\java8-parameterization\\src\\main\\java\\com\\mikewoo\\study\\java8\\stream\\SimpleStream.java";
        Stream<String> stringStream = null;
        try {
            stringStream = createStreamFromFile(filePath);
            stringStream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("======================");
        createStreamFromIterator().forEach(System.out::println);

        System.out.println("======================");
        createStreamFromGenerate().forEach(System.out::println);

        System.out.println("======================");
        createDishStreamFromGenerate().forEach(System.out::println);
    }

    /**
     * create strem from list
     *
     * @param collection
     * @param <T>
     * @return
     */
    private static <T> Stream<T> createStreamFromCollections(Collection<T> collection) {
        return collection.stream();
    }

    /**
     * create stream from values
     *
     * @param values
     * @param <T>
     * @return
     */
    private static <T> Stream<T> createStreamFromValues(T... values) {
        return Stream.of(values);
    }

    /**
     * create stream from arrays
     *
     * @param t
     * @param <T>
     * @return
     */
    private static <T> Stream<T> createStreamFromArrays(T[] t) {
        return Arrays.stream(t);
    }

    /**
     * create strem from file
     *
     * @param filePath
     * @return
     */
    private static Stream<String> createStreamFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.lines(path);
    }

    /**
     * create stream from iterator
     * @return
     */
    private static Stream<Integer> createStreamFromIterator() {
        Stream<Integer> iterate = Stream.iterate(0, n -> n + 2).limit(10);
        return iterate;
    }

    /**
     * create stream from generate
     * @return
     */
    private static Stream<Double> createStreamFromGenerate() {
        Stream<Double> doubleStream = Stream.generate(Math::random).limit(10);
        return doubleStream;
    }

    /**
     * create stream from generate
     * @return
     */
    private static Stream<Dish> createDishStreamFromGenerate() {
        Stream<Dish> dishStream = Stream.generate(new DishSuppiler()).limit(10);
        return dishStream;
    }
}
