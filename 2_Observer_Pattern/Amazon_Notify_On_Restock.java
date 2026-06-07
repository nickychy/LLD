import java.util.*;

interface Product{
    
    public void addSubscriber(Subscriber sub);
    public void removeSubscriber(Subscriber sub);
    public void notifySubscriber();
}
interface Subscriber{
    public void update();
}
abstract class ProductImpl implements Product {

    protected String id;
    protected int stockCount;
    protected List<Subscriber> subscribers = new ArrayList<>();

    public ProductImpl(String id, int stockCount) {
        this.id = id;
        this.stockCount = stockCount;
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscriber() {
        for (Subscriber sub : subscribers) {
            sub.update();
        }
    }

    public void stockUpdate(int newStock) {

        if (stockCount == 0 && newStock > 0) {
            System.out.println(id + " is back in stock");
            notifySubscriber();
        }

        stockCount = newStock;
    }
}

class Iphone extends ProductImpl {

    public Iphone(String id, int stockCount) {
        super(id, stockCount);
    }
}

class Watch extends ProductImpl {

    public Watch(String id, int stockCount) {
        super(id, stockCount);
    }
}
class Email_Notification implements Subscriber{
    ProductImpl product;
    // keep a copy of oberservable in observer for fields reference.
    //we could have passed this object in update function as well.
    public Email_Notification(ProductImpl prod){
        this.product=prod;
    }
    @Override
    public void update(){
        System.out.println("Email Notification :: Product "+product.id +" restored");
    }
}
public class Amazon_Notify_On_Restock {
    public static void main(String[] args){
        ProductImpl product1=new Iphone("1",2);
        ProductImpl product2=new Watch("2",4);
        Subscriber sub1=new Email_Notification(product1);
        Subscriber sub2= new Email_Notification(product2);
        product1.stockUpdate(0);
        product1.stockUpdate(2);
        product2.stockUpdate(0);

    }
}
