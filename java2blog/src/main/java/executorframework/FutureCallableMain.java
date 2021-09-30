package executorframework;

import java.util.concurrent.*;

public class FutureCallableMain {


    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(4);
        System.out.println("Start : ");
        Future powerFuture20;

        powerFuture20 = es.submit(new PowerCalc(20));
        Future factorialFuture20;
        Future powerFuture25;
        Future factorialFuture25;

        factorialFuture25 = es.submit(new FactorialCalc(25));
        factorialFuture20 = es.submit(new FactorialCalc(20));
        powerFuture25 = es.submit(new PowerCalc(25));
        try {
            System.out.println("Square of " + 25 + " : " + powerFuture25.get());
            System.out.println("Square of " + 20 + " : " + powerFuture20.get());
            System.out.println("Factorial of " + 20 + " : " + factorialFuture20.get());
            System.out.println("Factorial of " + 25 + " : " + factorialFuture25.get());
            es.shutdown();
        } catch (InterruptedException | ExecutionException e) {

            e.printStackTrace();
        }
        System.out.println("Done");
    }

}

class PowerCalc implements Callable {

    private double number;

    PowerCalc(double number) {
        this.number = number;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("Calculating Square with " + Thread.currentThread().getName());
        Thread.sleep(2000);
        return Math.pow(number, number);
    }
}

class FactorialCalc implements Callable {

    private double number;

    FactorialCalc(double number) {
        this.number = number;

    }

    @Override
    public Double call() throws Exception {
        System.out.println("Calculating factorial with " + Thread.currentThread().getName());
        Thread.sleep(5000);
        double fact = 1;
        for (int i = 2; i <= number; i++) {
            fact *= i;
        }
        return fact;
    }
}