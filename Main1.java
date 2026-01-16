
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class Shop {

    private ConcurrentHashMap<String, AtomicInteger> salesCount = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> priceProduct = new ConcurrentHashMap<>();
    private AtomicInteger totalSales = new AtomicInteger(0);
    private int totalPrice = 0;

    public void addSale(String name, int price) {
        salesCount.putIfAbsent(name, new AtomicInteger(0));
        salesCount.get(name).incrementAndGet();
        priceProduct.putIfAbsent(name, price);

        totalPrice += price;
        totalSales.incrementAndGet(); //увеличиваем на единичку
    }

    public void printAll() {
        List<Map.Entry<String, AtomicInteger>> sortedList = new ArrayList<>(salesCount.entrySet());
        Collections.sort(sortedList, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));

        for (Map.Entry<String, AtomicInteger> sale: sortedList) {
            System.out.println(sale.getKey() + ":" + sale.getValue());
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getPopularProduct() {
        if (salesCount.isEmpty()) {
            return "Продаж нет";
        }

        List<Map.Entry<String, AtomicInteger>> sortSales = new ArrayList<>(salesCount.entrySet());
        Collections.sort(sortSales, (o1, o2) -> Integer.compare(o2.getValue().get(), o1.getValue().get()));

        Map.Entry<String, AtomicInteger> mostPopular = sortSales.get(0);
        return mostPopular.getKey() + ": " + mostPopular.getValue(); 
    }

}

public class Main1 {
    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.addSale("Porshe 911", 32000000);
        shop.addSale("BMW X5", 12000000);
        shop.addSale("Tesla Cybertruck", 8000000);
        shop.addSale("Mercedes-Benz V-Class", 13000000);
        shop.addSale("Mercedes-Benz V-Class", 13000000);
        shop.addSale("Mercedes-Benz V-Class", 13000000);
        shop.printAll();
        System.out.println(shop.getTotalPrice());
        System.out.println(shop.getPopularProduct());
    }
}