public class PizzaShop {
    public static void main(String[] args) {
        
        System.out.println("--- Order 1: Peperoni ---");
        Pizza myPizza = new PizzaPeperoni();
        myPizza.doPizza();

        System.out.println(); 

        System.out.println("--- Order 2: Veggy ---");
        Pizza yourPizza = new PizzaVeggy();
        yourPizza.doPizza();
    }
}