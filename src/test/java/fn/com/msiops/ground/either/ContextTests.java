package fn.com.msiops.ground.either;

import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Test;

import com.msiops.ground.either.Either;
import com.msiops.ground.either.FunctionX;

public class ContextTests {

    @Test
    public void testLiftL0CheckedConvergent() {

        final FunctionX<Integer, ?, ?> f = x -> x * x;

        final Function<Integer, ?> lf = Either.liftL0Checked(f);

        final Object r;
        try {
            r = f.apply(10);
        } catch (final Exception e) {
            throw new AssertionError("invalid test, f should not throw");
        }

        assertEquals(Either.left(r), lf.apply(10));

    }

    @Test
    public void testLiftL0CheckedDivergent() {

        final Exception rightx = new Exception("timmy's down a well!");

        final FunctionX<Object, ?, ?> f = v -> {
            throw rightx;
        };

        final Function<Object, ?> lf = Either.liftL0Checked(f);

        assertEquals(Either.right(rightx), lf.apply(10));

    }

    @Test
    public void testLiftL0Convergent() {

        final Function<Integer, ?> f = x -> x * x;

        final Function<Integer, ?> lf = Either.liftL0(f);

        assertEquals(Either.left(f.apply(10)), lf.apply(10));

    }

    @Test
    public void testLiftL0Divergent() {

        final RuntimeException rightx = new RuntimeException(
                "Red Lectroids from planet 10 by way of the 8th dimension!");

        final Function<Object, ?> f = v -> {
            throw rightx;
        };

        final Function<Object, ?> lf = Either.liftL0(f);

        assertEquals(Either.right(rightx), lf.apply(10));

    }

}
