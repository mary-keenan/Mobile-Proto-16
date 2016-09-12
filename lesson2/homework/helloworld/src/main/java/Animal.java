/**
 * Created by mary on 9/12/16.
 */

import java.util.ArrayList;
import java.util.Random;

public abstract class Animal {

    protected int legs;
    protected String name;
    protected String color;
    protected String species;
    protected double weight;

    public Animal(int n_legs, String n_name, String n_color, String n_species, double n_weight){
        legs = n_legs;
        name = n_name;
        color = n_color;
        species = n_species;
        weight = n_weight;
    }
//Got dem setters
    public void setLegs(int value){
        legs = value;
    }
    public void setName(String value){
        name = value;
    }
    public void setColor(String value){
        color = value;
    }
    public void setSpecies(String value){
        species = value;
    }
    public void setWeight(int value){
        weight = value;
    }
//Got dem getters
    public int getLegs(){
        return legs;
    }
    public String getName(){
        return name;
    }
    public String getColor(){
        return color;
    }
    public String getSpecies(){
        return species;
    }
    public double getWeight(){
        return weight;
    }
//Got dem abstract methods
    abstract void grow();
}

//Got dem subclasses
class Cat extends Animal {

    public Cat(String n_name,String n_color){
        super(4,n_name,n_color,"rare",20);
        Random num = new Random();
        double weight = num.nextInt((25 - 0) + 1) + 0;
    }
    public void grow() {
        weight *= 3;
    }
}

class Dog extends Animal {
    public Dog(String n_name,String n_color){
        super(4,n_name,n_color,"rare",20);
        Random num = new Random();
        double weight = num.nextInt((25 - 0) + 1) + 0;
    }
    public void grow() {
        weight *= 1.5;
    }
}

class Cow extends Animal {
    public Cow(String n_name, String n_color) {
        super(4, n_name, n_color, "rare", 150);
        Random num = new Random();
        double weight = num.nextInt((200 - 100) + 1) + 100;
    }

    public void grow() {
        weight *= 5;
    }
}


public class Farm {

    public Farm(){
        ArrayList animals = new ArrayList();
    }
    public void addAnimal(Object n_animal){
        animals.add(n_animal);
    }
    public Object getAnimal(int index){
        return animals(index);
    }
    public ??? getHeaviestAnimals(){

    }

    public static void main(String[] args) {
        Cat c = new Cat("Meowth", "black");
        Dog d = new Dog("Puppy", "brown");
        Cow cow = new Cow("Mooer", "white");
        System.out.println("Test 1 Passed: " + (c.getWeight() >= 0 && c.getWeight() <= 25));
        System.out.println("Test 2 Passed: " + (d.getWeight() >= 0 && d.getWeight() <= 25));
        System.out.println("Test 3 Passed: " + (cow.getWeight() >= 100 && cow.getWeight() <= 200));

        double old_weight_cat = c.getWeight();
        double old_weight_dog = d.getWeight();
        double old_weight_cow = cow.getWeight();
        c.grow();
        d.grow();
        cow.grow();
        System.out.println("Test 4 Passed: " + (c.getWeight() / old_weight_cat == 3));
        System.out.println("Test 5 Passed: " +
                (Math.abs(d.getWeight() / old_weight_dog - 1.5) < 0.01));
        System.out.println("Test 6 Passed: " + (cow.getWeight() / old_weight_cow == 5));

        Farm farm = new Farm();
        farm.addAnimal(c);
        farm.addAnimal(d);
        farm.addAnimal(cow);

        ArrayList<Animal> sorted = farm.getHeaviestAnimals();
        for (int i = 0; i < sorted.size() - 1; i++) {
            System.out.println("Test " + (i + 7) + " Passed: " + (sorted.get(i).getWeight() > sorted.get(i + 1).getWeight()));
        }
        System.out.println("Test 9 Passed: " + (farm.getAnimal(0) == c));
        System.out.println("Test 10 Passed: " + (farm.getAnimal(1) == d));
        System.out.println("Test 11 Passed: " + (farm.getAnimal(2) == cow));
        c.setLeg(7);  // lol 7 legged cat
        System.out.println("Test 12 Passed: " + (farm.averageLegs() == 5));

        System.out.println("Printing 'Meowth'...");
        farm.printCatNames();
    }
}