import java.util.Scanner;

 // if we need to add new payment method, we can simply create a new class that implements PaymentStrategy and add it to the factory method without changing the existing code.

 //strategy pattern
interface PaymentStrategy{
    void pay(double amount);
}
class CreditCardStrategy implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}
class UpiStrategy implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Processing UPI payment of $" + amount);
    }
}
class CashStrategy implements PaymentStrategy{
    @Override
    public void pay(double amount){
        System.out.println("Processing Cash payment of $" + amount);
    }
}
class PaymentService{

    PaymentStrategy strategy;
    PaymentService(PaymentStrategy strategy){
        this.strategy=strategy;
    }
    public void pay(double amount){
        strategy.pay(amount);
    }

}

//factory pattern to create payment strategy objects based on user input
class PaymentStrategyFactory{
    public static PaymentStrategy getPaymentStrategy(String paymentMethod){
        switch(paymentMethod.toLowerCase()){
            case "credit":
                return new CreditCardStrategy();
            case "upi":
                return new UpiStrategy();
            case "cash":           
                 return new CashStrategy();
            default:     
                       throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }
}
class  Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the payment method (credit/upi/cash): ");
        String paymentMethod= sc.nextLine();

        System.out.println("Enter the amount to pay: ");
        Double amount= sc.nextDouble();
        // to choose the strategy based on user input,we will use factory method to get the appropriate strategy object.
        PaymentStrategy strategy=PaymentStrategyFactory.getPaymentStrategy(paymentMethod);
        PaymentService paymentService=new PaymentService((strategy));
        paymentService.pay(amount);

    }
} 