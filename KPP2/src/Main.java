import java.util.*;
import java.math.BigDecimal;

class Product {
    private String name;
    private String unit;
    private int quantity;
    private BigDecimal pricePerUnit;
    private Date arrivalDate;
    private Map<String, String> properties;

    public Product(String name, String unit, int quantity, BigDecimal pricePerUnit, Date arrivalDate) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.arrivalDate = arrivalDate;
        this.properties = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}

class ProductContainer<T extends Product> {
    private LinkedList<T> products;

    public ProductContainer() {
        products = new LinkedList<>();
    }

    public void add(T item) {
        products.addLast(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(products);
    }
}

public class Main {
    public static <T extends Product> List<T> sortByName(ProductContainer<T> container) {
        List<T> sortedList = container.getAll();
        sortedList.sort(Comparator.comparing(Product::getName));
        return sortedList;
    }

    public static <T extends Product> List<T> sortByPrice(ProductContainer<T> container) {
        List<T> sortedList = container.getAll();
        sortedList.sort(Comparator.comparing(Product::getPricePerUnit));
        return sortedList;
    }

    public static <T extends Product> List<T> sortByArrivalDate(ProductContainer<T> container) {
        List<T> sortedList = container.getAll();
        sortedList.sort(Comparator.comparing(Product::getArrivalDate));
        return sortedList;
    }

    public static void main(String[] args) {
        ProductContainer<Product> productContainer = new ProductContainer<>();

        productContainer.add(new Product("Product A", "kg", 10, new BigDecimal("5.99"), new Date()));
        productContainer.add(new Product("Product B", "piece", 50, new BigDecimal("1.49"), new Date(new Date().getTime() - 5 * 24 * 60 * 60 * 1000)));
        productContainer.add(new Product("Product C", "liter", 20, new BigDecimal("2.99"), new Date(new Date().getTime() - 2 * 24 * 60 * 60 * 1000)));

        System.out.println("Сортування за найменуванням:");
        for (Product product : sortByName(productContainer)) {
            System.out.println(product.getName() + ", " + product.getQuantity() + " " + product.getUnit() + ", $" + product.getPricePerUnit());
        }

        System.out.println("\nСортування за ціною:");
        for (Product product : sortByPrice(productContainer)) {
            System.out.println(product.getName() + ", " + product.getQuantity() + " " + product.getUnit() + ", $" + product.getPricePerUnit());
        }

        System.out.println("\nСортування за датою надходження:");
        for (Product product : sortByArrivalDate(productContainer)) {
            System.out.println(product.getName() + ", " + product.getQuantity() + " " + product.getUnit() + ", $" + product.getPricePerUnit() + ", " + product.getArrivalDate());
        }
    }
}
