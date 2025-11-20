abstract class Pizza {

    final void doPizza() {
        prepareDough();     
        addIngredients(); 
        bakePizza();        
        cutPizza();         
    }

    void prepareDough() {
        System.out.println("1. Knead dough...");
    }

    void bakePizza() {
        System.out.println("3. Bake pizza at 200 degrees for 15 minutes.");
    }

    void cutPizza() {
        System.out.println("4. Enjoy!");
    }

    void addIngredients() {
        throw new UnsupportedOperationException("Unimplemented method 'addIngredients'");
    }
}