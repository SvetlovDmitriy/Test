package entity;

import java.util.Objects;

/**
 * The {@code Machine} class describes machine tools (field of application - mechanical engineering).
 * Contain fields describing the characteristics of the machine.
 */
public abstract class Machine {
    /** The variable describing power of machine. */
    private int power;

    /** The variable describing weight of machine. */
    private int weight;

    /** The variable describing model of machine. */
    private String model;

    /**
     * Initializes a newly created {@code Machine} object.
     *
     * @param power power of machine
     * @param weight weight of machine
     * @param model model of machine
     */
    public Machine(int power, int weight, String model) {
        this.power = power;
        this.weight = weight;
        this.model = model;
    }

    public Machine() {
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return  "power=" + power +
                ", weight=" + weight +
                ", model='" + model + '\'';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Machine) {
            Machine ob = (Machine) obj;
            return ( (weight == ob.weight) && (power == ob.power) &&
                    (model.equals(ob.model)));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, weight, model);
    }
}
