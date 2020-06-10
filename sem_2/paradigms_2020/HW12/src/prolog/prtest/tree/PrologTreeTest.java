package prolog.prtest.tree;


import prolog.prtest.PrologScript;
import prolog.prtest.Value;
import prolog.prtest.map.Entry;
import prolog.prtest.map.PrologMapTest;
import prolog.prtest.map.Settings;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class PrologTreeTest extends PrologMapTest {
    public static String SOLUTION = "/home/mokaruuns/git/education_itmo/sem_2/paradigms_2020/HW12/src/prolog/prtest/tree/tree-map.pl";

    public PrologTreeTest(final boolean updates, final String file) {
        super(updates, file);
    }

    public PrologTreeTest(final boolean updates) {
        this(updates, SOLUTION);
    }

    @Override
    protected PrologMapTest.MapTest<?> test(final Settings settings) {
        return new TreeTest(settings);
    }

    public static void main(final String... args) {
        new PrologTreeTest(hasUpdates(args, PrologTreeTest.class)).test();
    }

    protected static boolean hasUpdates(final String[] args, final Class<? extends PrologTreeTest> type) {
        return mode(args, type, "easy", "hard") == 1;
    }

    protected class TreeTest extends MapTest<NavigableMap<Integer, Value>> {
        public TreeTest(final Settings settings) {
            super(settings, new TreeMap<>());
        }

        protected Value build(final List<Entry> entries) {
            entries.forEach(entry -> expected.put(entry.getKey(), entry.getValue()));
            return prolog.solveOne("map_build", Value.list(entries, Entry::toValue), PrologScript.V);
        }

        @Override
        protected void check(final List<Integer> testKeys) {
            for (final int key : testKeys) {
                get(expected.get(key), key);
            }
        }

        @Override
        protected void putImpl(final int key, final Value value) {
            expected.put(key, value);
        }

        @Override
        protected void removeImpl(final int key) {
            expected.remove(key);
        }
    }
}
