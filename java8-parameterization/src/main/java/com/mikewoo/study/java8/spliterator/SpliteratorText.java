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


        // tryAdvance就是顺序处理每个元素，类似Iterator，如果还有元素要处理，则返回true，否则返回false
        @Override
        public boolean tryAdvance(Consumer<? super String> action) {
            if (start <= end) {
                action.accept(SpliteratorText.this.data[start++]);
                return true;
            }
            return false;
        }

        // trySplit，就是为Spliterator专门设计的方法，区分与普通的Iterator，
        // 该方法会把当前元素划分一部分出去创建一个新的Spliterator作为返回，两个Spliterator变会并行执行，如果元素个数小到无法划分则返回null
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

        // estimateSize，该方法用于估算还剩下多少个元素需要遍历
        @Override
        public long estimateSize() {
            return end - start;
        }

        // characteristics，其实就是表示该Spliterator有哪些特性，特性值如下：
        // ORDERED: 表示迭代器需要按照其原始顺序迭代其中元素
        // DISTINCT：迭代器中的元素是没有重复的
        // SORTED：迭代器是按照某种方式排序的顺序迭代其中元素的
        // SIZED：表示迭代器将要迭代的元素的个数是可计数的，有界的
        // NONNULL：迭代器迭代的元素是没有值为`null`的
        // IMMUTABLE：迭代器迭代的元素是不可改变的，也不可以增加、替换和删除
        // CONCURRENT：表示迭代器的数据源是线程安全的
        // SUBSIZED：表示当前迭代器所有的子迭代器（直接的或者间接的），都是`SIZED`和`SUBSIZED`的
        @Override
        public int characteristics() {
            return IMMUTABLE | SIZED | SUBSIZED;
        }
    }


}
