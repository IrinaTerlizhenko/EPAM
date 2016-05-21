package by.bsu.xml.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 03.04.16
 * Time: 8:32
 * To change this template use File | Settings | File Templates.
 */
public class Candy {
    public static class Ingredient {
        private int water;
        private int sugar;
        private int fructose;
        private int vanillin;

        public Ingredient() {
        }

        public void setFructose(int fructose) {
            this.fructose = fructose;
        }

        public void setSugar(int sugar) {
            this.sugar = sugar;
        }

        public void setWater(int water) {
            this.water = water;
        }

        public void setVanillin(int vanillin) {
            this.vanillin = vanillin;
        }

        @Override
        public String toString() {
            return "ingredients: [water=" + water + " sugar=" + sugar + " fructose=" + fructose + " vanillin=" + vanillin + "]";
        }
    }

    public static class Value {
        private int proteins;
        private int fats;
        private int carbohydrates;

        public Value() {
        }

        public void setCarbohydrates(int carbohydrates) {
            this.carbohydrates = carbohydrates;
        }

        public void setFats(int fats) {
            this.fats = fats;
        }

        public void setProteins(int proteins) {
            this.proteins = proteins;
        }

        @Override
        public String toString() {
            return "value: [proteins=" + proteins + " fats=" + fats + " carbohydrates=" + carbohydrates + "]";
        }
    }

    private String id;
    private String name;
    private int energy;
    private Ingredient ingredient = new Ingredient();
    private Value value = new Value();
    private String production;
    private CandyType type;

    public Candy() {
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public Value getValue() {
        return value;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public void setType(CandyType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ID=" + id + " name=" + name + " energy=" + energy + " " + ingredient + " " + value + " production="
                + production + " type=" + type;
    }
}
