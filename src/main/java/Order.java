import java.util.List;
import java.util.stream.Collectors;

class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }
}

class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0),
                new Order("PC", 9000.0),
                new Order("TV", 3000.0)
        );

        orders.stream().collect(Collectors.groupingBy(Order::getProduct,Collectors.summingDouble(Order::getCost))).entrySet().stream() // группировка в мапу
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue())).limit(3) // сортировка по убыванию, оставление топ 3
                .forEach(entry ->  System.out.println("Pos: " + entry.getKey() + ", Summary cost: " + entry.getValue())); // вывод
    }
}
