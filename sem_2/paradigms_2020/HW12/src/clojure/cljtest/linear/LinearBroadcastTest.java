package clojure.cljtest.linear;

import clojure.cljtest.ClojureScript;
import clojure.lang.IPersistentVector;
import javascript.jstest.Engine;

import static clojure.cljtest.linear.LinearShapelessTest.*;


/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class LinearBroadcastTest extends LinearNaryTest {
    public static final ClojureScript.F<?> B_ADD = anyFunction("b+");
    public static final ClojureScript.F<?> B_SUB = anyFunction("b-");
    public static final ClojureScript.F<?> B_MUL = anyFunction("b*");
    public static final ClojureScript.F<?> B_DIV = anyFunction("bd");

    @Override
    protected void test() {
        super.test();

        testAddSubMul(1, -1, 1, 1, number(1));
        testAddSubMul(3, -1, 2, 0.5, number(1), number(2));
        testAddSubMul(6, -4, 6, 0.167, number(1), number(2), number(3));

        testAddSubMul(v(1, 2), v(-1, -2), v(1, 2), v(1, 0.5), vector(1, 2));
        testAddSubMul(v(4, 6), v(-2, -2), v(3, 8), v(0.333, 0.5), vector(1, 2), vector(3, 4));
        testAddSubMul(v(9, 12), v(-7, -8), v(15, 48), v(0.067, 0.083), vector(1, 2), vector(3, 4), vector(5, 6));

        final Engine.Result<IPersistentVector> m123_456 = matrix(row(1.1, 2.1, 3.1), row(4.1, 5.1, 6.1));
        final Engine.Result<IPersistentVector> m789_012 = matrix(row(7.1, 8.1, 9.1), row(0.1, 1.1, 2.1));
        final Engine.Result<IPersistentVector> m345_678 = matrix(row(3.1, 4.1, 5.1), row(6.1, 7.1, 8.1));
        testAddSubMul(
                v(v(1.1, 2.1, 3.1), v(4.1, 5.1, 6.1)),
                v(v(-1.1, -2.1, -3.1), v(-4.1, -5.1, -6.1)),
                v(v(1.1, 2.1, 3.1), v(4.1, 5.1, 6.1)),
                v(v(0.909, 0.476, 0.323), v(0.244, 0.196, 0.164)),
                m123_456
        );

        testAddSubMul(
                v(v(8.2, 10.2, 12.2), v(4.2, 6.2, 8.2)),
                v(v(-6.0, -6.0, -6.0), v(4, 4, 4)),
                v(v(7.81, 17.01, 28.21), v(0.41, 5.61, 12.81)),
                v(v(0.155, 0.259, 0.341), v(41.0, 4.636, 2.905)),
                m123_456, m789_012
        );

        testAddSubMul(
                v(v(11.3, 14.3, 17.3), v(10.3, 13.3, 16.3)),
                v(v(-9.1, -10.1, -11.1), v(-2.1, -3.1, -4.1)),
                v(v(24.211, 69.741, 143.871), v(2.501, 39.831, 103.761)),
                v(v(0.05, 0.063, 0.067), v(6.721, 0.653, 0.359)),
                m123_456, m789_012, m345_678
        );

        final Engine.Result<IPersistentVector> t1 = vector(m123_456, m789_012);
        final Engine.Result<IPersistentVector> t2 = vector(m789_012, m345_678);
        final Engine.Result<IPersistentVector> t3 = vector(m345_678, m123_456);
        testAddSubMul(
                v(v(v(1.1, 2.1, 3.1), v(4.1, 5.1, 6.1)), v(v(7.1, 8.1, 9.1), v(0.1, 1.1, 2.1))),
                v(v(v(-1.1, -2.1, -3.1), v(-4.1, -5.1, -6.1)), v(v(-7.1, -8.1, -9.1), v(-0.1, -1.1, -2.1))),
                v(v(v(1.1, 2.1, 3.1), v(4.1, 5.1, 6.1)), v(v(7.1, 8.1, 9.1), v(0.1, 1.1, 2.1))),
                v(v(v(0.909, 0.476, 0.323), v(0.244, 0.196, 0.164)), v(v(0.141, 0.123, 0.11), v(10.0, 0.909, 0.476))),
                t1
        );
        testAddSubMul(
                v(v(v(8.2, 10.2, 12.2), v(4.2, 6.2, 8.2)), v(v(10.2, 12.2, 14.2), v(6.2, 8.2, 10.2))),
                v(v(v(-6.0, -6.0, -6.0), v(4, 4, 4)), v(v(4, 4.0, 4.0), v(-6.0, -6.0, -6.0))),
                v(v(v(7.81, 17.01, 28.21), v(0.41, 5.61, 12.81)), v(v(22.01, 33.21, 46.41), v(0.61, 7.81, 17.01))),
                v(v(v(0.155, 0.259, 0.341), v(41.0, 4.636, 2.905)), v(v(2.29, 1.976, 1.784), v(0.016, 0.155, 0.259))),
                t1, t2
        );
        testAddSubMul(
                v(v(v(11.3, 14.3, 17.3), v(10.3, 13.3, 16.3)), v(v(11.3, 14.3, 17.3), v(10.3, 13.3, 16.3))),
                v(v(v(-9.1, -10.1, -11.1), v(-2.1, -3.1, -4.1)), v(v(2.9, 1.9, 0.9), v(-10.1, -11.1, -12.1))),
                v(v(v(24.211, 69.741, 143.871), v(2.501, 39.831, 103.761)), v(v(24.211, 69.741, 143.871), v(2.501, 39.831, 103.761))),
                v(v(v(0.05, 0.063, 0.067), v(6.721, 0.653, 0.359)), v(v(2.082, 0.941, 0.576), v(0.004, 0.03, 0.043))),
                t1, t2, t3
        );

        testAddSubMul(v(11, 12), v(9, 8), v(10, 20), v(10.0, 5.0), number(10), vector(1, 2));
        testAddSubMul(v(11, 12), v(-9, -8), v(10, 20), v(0.1, 0.2), vector(1, 2), number(10));

        testAddSubMul(
                v(v(11.1, 12.1, 13.1), v(14.1, 15.1, 16.1)),
                v(v(8.9, 7.9, 6.9), v(5.9, 4.9, 3.9)),
                v(v(11.0, 21.0, 31.0), v(41.0, 51.0, 61.0)),
                v(v(9.091, 4.762, 3.226), v(2.439, 1.961, 1.639)),
                number(10), m123_456
        );
        testAddSubMul(
                v(v(17.1, 18.1, 19.1), v(10.1, 11.1, 12.1)),
                v(v(-2.9, -1.9, -0.9), v(-9.9, -8.9, -7.9)),
                v(v(71.0, 81.0, 91.0), v(1.0, 11.0, 21.0)),
                v(v(0.71, 0.81, 0.91), v(0.01, 0.11, 0.21)),
                m789_012, number(10)
        );
        testAddSubMul(
                v(v(13.1, 24.1, 35.1), v(16.1, 27.1, 38.1)),
                v(v(6.9, 15.9, 24.9), v(3.9, 12.9, 21.9)),
                v(v(31.0, 82.0, 153.0), v(61.0, 142.0, 243.0)),
                v(v(3.226, 4.878, 5.882), v(1.639, 2.817, 3.704)),
                vector(10, 20, 30), m345_678
        );
        testAddSubMul(
                v(v(11.1, 22.1, 33.1), v(14.1, 25.1, 36.1)),
                v(v(-8.9, -17.9, -26.9), v(-5.9, -14.9, -23.9)),
                v(v(11.0, 42.0, 93.0), v(41.0, 102.0, 183.0)),
                v(v(0.11, 0.105, 0.103), v(0.41, 0.255, 0.203)),
                m123_456, vector(10, 20, 30)
        );

        testAddSubMul(
                v(v(v(11.1, 12.1, 13.1), v(14.1, 15.1, 16.1)), v(v(17.1, 18.1, 19.1), v(10.1, 11.1, 12.1))),
                v(v(v(8.9, 7.9, 6.9), v(5.9, 4.9, 3.9)), v(v(2.9, 1.9, 0.9), v(9.9, 8.9, 7.9))),
                v(v(v(11.0, 21.0, 31.0), v(41.0, 51.0, 61.0)), v(v(71.0, 81.0, 91.0), v(1.0, 11.0, 21.0))),
                v(v(v(9.091, 4.762, 3.226), v(2.439, 1.961, 1.639)), v(v(1.408, 1.235, 1.099), v(100.0, 9.091, 4.762))),
                number(10), t1
        );
        testAddSubMul(
                v(v(v(27.1, 28.1, 29.1), v(20.1, 21.1, 22.1)), v(v(23.1, 24.1, 25.1), v(26.1, 27.1, 28.1))),
                v(v(v(-12.9, -11.9, -10.9), v(-19.9, -18.9, -17.9)), v(v(-16.9, -15.9, -14.9), v(-13.9, -12.9, -11.9))),
                v(v(v(142.0, 162.0, 182.0), v(2.0, 22.0, 42.0)), v(v(62.0, 82.0, 102.0), v(122.0, 142.0, 162.0))),
                v(v(v(0.355, 0.405, 0.455), v(0.005, 0.055, 0.105)), v(v(0.155, 0.205, 0.255), v(0.305, 0.355, 0.405))),
                t2, number(20)
        );
        testAddSubMul(
                v(v(v(13.1, 24.1, 35.1), v(16.1, 27.1, 38.1)), v(v(11.1, 22.1, 33.1), v(14.1, 25.1, 36.1))),
                v(v(v(6.9, 15.9, 24.9), v(3.9, 12.9, 21.9)), v(v(8.9, 17.9, 26.9), v(5.9, 14.9, 23.9))),
                v(v(v(31.0, 82.0, 153.0), v(61.0, 142.0, 243.0)), v(v(11.0, 42.0, 93.0), v(41.0, 102.0, 183.0))),
                v(v(v(3.226, 4.878, 5.882), v(1.639, 2.817, 3.704)), v(v(9.091, 9.524, 9.677), v(2.439, 3.922, 4.918))),
                vector(10, 20, 30), t3
        );
        testAddSubMul(
                v(v(v(11.1, 22.1, 33.1), v(14.1, 25.1, 36.1)), v(v(17.1, 28.1, 39.1), v(10.1, 21.1, 32.1))),
                v(v(v(-8.9, -17.9, -26.9), v(-5.9, -14.9, -23.9)), v(v(-2.9, -11.9, -20.9), v(-9.9, -18.9, -27.9))),
                v(v(v(11.0, 42.0, 93.0), v(41.0, 102.0, 183.0)), v(v(71.0, 162.0, 273.0), v(1.0, 22.0, 63.0))),
                v(v(v(0.11, 0.105, 0.103), v(0.41, 0.255, 0.203)), v(v(0.71, 0.405, 0.303), v(0.01, 0.055, 0.07))),
                t1, vector(10, 20, 30)
        );
        testAddSubMul(
                v(v(v(8.2, 10.2, 12.2), v(4.2, 6.2, 8.2)), v(v(4.2, 6.2, 8.2), v(10.2, 12.2, 14.2))),
                v(v(v(-6.0, -6.0, -6.0), v(4, 4, 4)), v(v(-2.0, -2, -2), v(-2.0, -2.0, -2.0))),
                v(v(v(7.81, 17.01, 28.21), v(0.41, 5.61, 12.81)), v(v(3.41, 8.61, 15.81), v(25.01, 36.21, 49.41))),
                v(v(v(0.155, 0.259, 0.341), v(41.0, 4.636, 2.905)), v(v(0.355, 0.512, 0.608), v(0.672, 0.718, 0.753))),
                m123_456, t2
        );
        testAddSubMul(
                v(v(v(6.2, 8.2, 10.2), v(12.2, 14.2, 16.2)), v(v(4.2, 6.2, 8.2), v(10.2, 12.2, 14.2))),
                v(v(v(0.0, 0.0, 0.0), v(0.0, 0.0, 0.0)), v(v(-2.0, -2, -2), v(-2.0, -2.0, -2.0))),
                v(v(v(9.61, 16.81, 26.01), v(37.21, 50.41, 65.61)), v(v(3.41, 8.61, 15.81), v(25.01, 36.21, 49.41))),
                v(v(v(1.0, 1.0, 1.0), v(1.0, 1.0, 1.0)), v(v(0.355, 0.512, 0.608), v(0.672, 0.718, 0.753))),
                t3, m345_678
        );

        testAddSubMul(
                v(v(111, 221, 331), v(141, 251, 361)),
                v(v(-109, -219, -329), v(-139, -249, -359)),
                v(v(1000, 4000, 9000), v(4000, 10000, 18000)),
                v(v(0.001, 0.0, 0.0), v(0.0, 0.0, 0.0)),
                number(1),
                matrix(row(10, 20, 30), row(40, 50, 60)),
                vector(100, 200, 300)
        );

        testAddSubMul(
                v(v(v(33.5, 47.6, 61.7), v(35.5, 49.6, 63.7)), v(v(27.5, 41.6, 55.7), v(39.5, 53.6, 67.7))),
                v(v(v(-27.5, -41.6, -55.7), v(-29.5, -43.6, -57.7)), v(v(-21.5, -35.6, -49.7), v(-33.5, -47.6, -61.7))),
                v(v(v(1350.9738, 20587.5432, 118866.2202), v(274.6098, 20361.6072, 136155.1842)), v(v(209.3058, 5337.5112, 40492.8882), v(11259.0018, 94403.8152, 395498.3922))),
                v(v(v(0.007, 0.0, 0.0), v(0.033, 0.0, 0.0)), v(v(0.043, 0.002, 0.0), v(0.001, 0.0, 0.0))),
                number(3), m123_456, vector(10, 20, 30), t2,
                number(6), m345_678, vector(0.1, 0.2, 0.3), t3
        );
    }

    private void testAddSubMul(final Object add, final Object sub, final Object mul, final Object div, final Engine.Result<?>... args) {
        assertTensor(B_ADD, add, args);
        assertTensor(B_SUB, sub, args);
        assertTensor(B_MUL, mul, args);
        assertTensor(B_DIV, div, args);
    }

    private void assertTensor(final ClojureScript.F<?> f, final Object expected, final Engine.Result<?>[] args) {
        final Engine.Result<?> actual = f.call(args);
        testing(actual);
        if (expected != null) {
            assertShapeless(actual.context, actual.value, expected);
        } else {
            result(actual);
        }
        counter.passed();
    }

    private static void result(final Engine.Result<?> result) {
        System.out.println("   " + result.value.toString().replace("[", "v(").replace("]", ")").replace(" ", ", "));
    }


    public static void main(final String... args) {
        new LinearBroadcastTest().run();
    }
}
