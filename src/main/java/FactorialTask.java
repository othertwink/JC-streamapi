import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class FactorialTask extends RecursiveTask<Long> {
    private final int n;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 5) {
            return countSequential(n);
        }

        FactorialTask subTask1 = new FactorialTask(n / 2);
        FactorialTask subTask2 = new FactorialTask(n - (n / 2));

        subTask1.fork();

        return subTask2.compute() * subTask1.join(); // сначала compute потому что join лочит мейн
    }

    private long countSequential(int num) {
        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}

class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 10; // Вычисление факториала для числа 10

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
}
