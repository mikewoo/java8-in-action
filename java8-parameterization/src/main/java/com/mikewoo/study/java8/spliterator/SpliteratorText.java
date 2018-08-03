package com.mikewoo.study.java8.spliterator;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Eric Gui
 * @date 2018/8/3
 */
public class SpliteratorText {

    private final String[] data;


    public SpliteratorText(String data) {
        Objects.requireNonNull(data, "data can not be null");
        this.data = data.split("\n");
    }

    public Stream<String> stream() {
        return StreamSupport.stream(new TextSpliterator(), false);
    }

    public Stream<String> parallelStream() {
        return StreamSupport.stream(new TextSpliterator(), true);
    }

    public Spliterator<String> spliterator() {
        return new TextSpliterator(0, this.data.length - 1);
    }

    private final class TextSpliterator implements Spliterator<String> {

        private int start, end;

        public TextSpliterator() {
            this.start = 0;
            this.end = SpliteratorText.this.data.length - 1;
        }

        public TextSpliterator(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        public boolean tryAdvance(Consumer<? super String> action) {
            if (start <= end) {
                action.accept(SpliteratorText.this.data[start++]);
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<String> trySplit() {
            int middle = (end - start) / 2;
            if (middle <=  1) {
                return null;
            }

            int left = start;
            int right = start + middle;
            start = start + middle + 1;
            return new TextSpliterator(left, right);
        }

        @Override
        public long estimateSize() {
            return end - start;
        }

        @Override
        public int characteristics() {
            return IMMUTABLE | SIZED | SUBSIZED;
        }
    }


}
