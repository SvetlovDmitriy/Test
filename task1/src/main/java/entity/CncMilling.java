package entity;

import java.util.Objects;

/**
 * This {@code CncMilling} class extends {@code Milling} class and describes cnc milling machine.
 */
public class CncMilling extends Milling {
    /** Software name */
    String softwareName;

    /** Number of axles */
    int numberAix;

    /**
     * Initializes a newly created {@code CncMilling} object.
     *
     * @param power power of milling machine
     * @param weight weight of milling machine
     * @param model model of milling machine
     * @param maxSpindleSpeed maximum spindle speed of milling machine
     * @param maxMillingSped maximum milling speed of milling machine
     * @param tableArea working area of the table of milling machine
     * @param softwareName software name
     * @param numberAix number of axles
     */
    public CncMilling(int power, int weight, String model, int maxSpindleSpeed,
                      int maxMillingSped, int tableArea, String softwareName, int numberAix) {
        super(power, weight, model, maxSpindleSpeed, maxMillingSped, tableArea);
        this.softwareName = softwareName;
        this.numberAix = numberAix;
    }

    public CncMilling() {
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public int getNumberAix() {
        return numberAix;
    }

    public void setNumberAix(int numberAix) {
        this.numberAix = numberAix;
    }

    /**
     * Pattern builder
     */
    public static class Builder{

        private CncMilling newCncMilling;

        public Builder() {
            newCncMilling = new CncMilling();
        }

        public Builder power(int power) {
            newCncMilling.setPower(power);
            return this;
        }

        public Builder weight(int weight) {
            newCncMilling.setWeight(weight);
            return this;
        }

        public Builder model(String model) {
            newCncMilling.setModel(model);
            return this;
        }

        public Builder maxSpindleSpeed(int maxSpindleSpeed) {
            newCncMilling.setMaxSpindleSpeed(maxSpindleSpeed);
            return this;
        }

        public Builder maxMillingSped(int maxMillingSped) {
            newCncMilling.setMaxMillingSped(maxMillingSped);
            return this;
        }

        public Builder tableArea(int tableArea) {
            newCncMilling.setTableArea(tableArea);
            return this;
        }

        public Builder softwareName(String softwareName) {
            newCncMilling.setSoftwareName(softwareName);
            return this;
        }

        public Builder numberAix(int numberAix) {
            newCncMilling.setNumberAix(numberAix);
            return this;
        }

        public CncMilling build() {
            return newCncMilling;
        }
    }

    @Override
    public String toString() {
        return "CncMilling{" +
                super.toString() +
                "nameProgramme='" + softwareName + '\'' +
                ", numberAix=" + numberAix +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)){
            return false;
        }
        if (obj instanceof CncMilling) {
            CncMilling ob = (CncMilling) obj;
            return (softwareName.equals(ob.softwareName)) && (numberAix == ob.numberAix);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), softwareName, numberAix);
    }
}
