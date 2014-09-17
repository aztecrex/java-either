package fn.com.msiops.ground.either;

import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Test;

import com.msiops.ground.either.Either;
import com.msiops.ground.either.FunctionX;

public class ContextTests {

    @Test
    public void testCheckedConvergentLift() {

        final FunctionX<Integer, ?, ?> f = x -> x * x;

        final Function<Integer, ?> lf = Either.liftChecked(f);

        /*
         * notice that we only need to catch an exception because we are
         * invoking the fn here. This points out the purpose of the checked
         * lift--no exception handler or throws clause is needed.
         */
        final Object r;
        try {
            r = f.apply(10);
        } catch (final Exception e) {
            throw new AssertionError("invalid test, f should not throw");
        }

        assertEquals(Either.left(r), lf.apply(10));

    }

    @Test
    public void testCheckedDivergentLift() {

        final Exception rightx = new Exception("timmy's down a well!");

        final FunctionX<Object, ?, ?> f = v -> {
            throw rightx;
        };

        final Function<Object, ?> lf = Either.liftChecked(f);

        assertEquals(Either.right(rightx), lf.apply(10));

    }

    @Test
    public void testUncheckedConvergentLift() {

        final Function<Integer, ?> f = x -> x * x;

        final Function<Integer, ?> lf = Either.lift(f);

        assertEquals(Either.left(f.apply(10)), lf.apply(10));

    }

    @Test
    public void testUncheckedDivergentLift() {

        final RuntimeException rightx = new RuntimeException(
                "Red Lectroids from planet 10 by way of the 8th dimension!");

        final Function<Object, ?> f = v -> {
            throw rightx;
        };

        final Function<Object, ?> lf = Either.lift(f);

        assertEquals(Either.right(rightx), lf.apply(10));

    }

}
