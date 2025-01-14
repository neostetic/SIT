package cz.polacek.uvodOOP;

public class Woman {

    String name;
    String ig;
    int age;
    String eyeColor;

    public Woman(String name, String ig, int age, String eyeColor) {
        this.name = name;
        this.ig = ig;
        this.age = age;
        this.eyeColor = eyeColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIg() {
        return ig;
    }

    public void setIg(String ig) {
        this.ig = ig;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Woman() {}

    void cook() {
        System.out.println(name + " is cooking...");
    }

    String cook(String food) {
        return "Cooking" + food;
    }

    void seznamitSeZenou() {
        System.out.println(
                "Ahoj, já jsem " + this.name + ", a můj Instagram, pod společností Facebook Incorporated\n" +
                "(později Meta Incorporated) je " + this.ig + ". Ale jestli nechceš otravovat můj " + this.age + "ti\n" +
                "letý účet, tak tě naučím anglicky barvu mých očí. To je: " + this.eyeColor + ", zbytek si vyřeš\n" +
                "sám. Úchyle!11!1!!11"
        );
    }

    @Override
    public String toString() {
        return "Woman{" +
                "name='" + name + '\'' +
                ", ig='" + ig + '\'' +
                ", age=" + age +
                ", eyeColor='" + eyeColor + '\'' +
                '}';
    }
}
