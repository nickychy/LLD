
interface Coffee{
    int cost();
    String description();
}
class Espresso implements Coffee{
    @Override
    public int cost(){
        return 100;
    }
    @Override
    public String description(){
        return "You have ordered Espresso coffee";
    }
}
class Cappuccino implements Coffee{
    @Override
    public int cost(){
        return 200;
    }
    @Override
    public String description(){
        return "You have ordered Cappuccino coffee";
    }
}

//Decoraters::
 abstract class BaseDecorater implements Coffee{
    Coffee coffee;
    public BaseDecorater(Coffee c){
        this.coffee=c;
    }
}
class MilkDecorator extends BaseDecorater{
    public MilkDecorator(Coffee c){
        super(c);
    }
    @Override
    public int cost(){
        return coffee.cost()+30;
    }
    @Override
 public String description(){
        return coffee.description()+" ,extra Milk";
    }
}
class SugarDecorator extends BaseDecorater{
    public SugarDecorator(Coffee c){
        super(c);
    }
    @Override
    public int cost(){
        return coffee.cost()+30;
    }
    @Override
 public String description(){
        return coffee.description()+" ,extra Sugar";
    }
}
public class Coffe_Machine {
    public static void main (String[] args){
        Coffee coffee=new Cappuccino();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.description()+" for price "+coffee.cost());
        coffee = new MilkDecorator(coffee);
                System.out.println(coffee.description()+" for price "+coffee.cost());

    }
}
