package spike.com.msiops.ground.either;

import java.util.function.Function;

import com.msiops.ground.either.Either;

public class LiftSpike {

    public static class MyX extends Exception {

        MyX() {
            super("whoa");
        }

        private static final long serialVersionUID = 1L;

    }

    public static void main(String[] args) {

        Function<Integer, Either<Integer, Exception>> lf1 = Either
                .liftXL0(a -> {
                    throw new MyX();
                });

        Function<Integer, Either<Integer, MyX>> lf2 = Either.liftXL0(a -> {
            throw new MyX();
        }, MyX.class);

        lf2.apply(10).swap().map(Exception::getMessage)
                .forEach(System.out::println);

        lf1.apply(10).swap().map(Exception::getMessage)
                .forEach(System.out::println);

    }

}
