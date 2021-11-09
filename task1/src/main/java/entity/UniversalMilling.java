package entity;

import constant.TypeMillingMachine;
import java.util.Objects;

/**
 * This {@code UniversalMilling} class extends {@code Milling} class and describes universal milling machine.
 */
public class UniversalMilling extends Milling{
    /** Type of milling machine such as VERTICAL, HORIZONTAL, BORING. */
    private TypeMillingMachine typeMilling;

    /** minimum step of the machine (mm). */
    private double degreeLimb;

    /**
     * Initialization new object of {@code UniversalMilling}.
     *
     * @param power power of milling machine
     * @param weight weight of milling machine
     * @param model model of milling machine
     * @param maxSpindleSpeed maximum spindle speed of milling machine
     * @param maxMillingSped maximum milling speed of milling machine
     * @param tableArea working area of the table of milling machine
     * @param typeMilling Type of milling machine
     * @param degreeLimb minimum step of the machine
     */
    public UniversalMilling(int power, int weight, String model, int maxSpindleSpeed, int maxMillingSped,
                            int tableArea, TypeMillingMachine typeMilling, double degreeLimb) {
        super(power, weight, model, maxSpindleSpeed, maxMillingSped, tableArea);
        this.typeMilling = typeMilling;
        this.degreeLimb = degreeLimb;
    }

    public UniversalMilling() {
    }

    public TypeMillingMachine getTypeMilling() {
        return typeMilling;
    }

    public void setTypeMilling(TypeMillingMachine typeMilling) {
        this.typeMilling = typeMilling;
    }

    public double getDegreeLimb() {
        return degreeLimb;
    }

    public void setDegreeLimb(double degreeLimb) {
        this.degreeLimb = degreeLimb;
    }

    /**
     * Pattern builder
     */
    public static class Builder{

        private UniversalMilling newUniversalMilling;

        public Builder() {
            newUniversalMilling = new UniversalMilling();
        }

        public Builder power(int power) {
            newUniversalMilling.setPower(power);
            return this;
        }

        public Builder weight(int weight) {
            newUniversalMilling.setWeight(weight);
            return this;
        }

        public Builder model(String model) {
            newUniversalMilling.setModel(model);
            return this;
        }

        public Builder maxSpindleSpeed(int maxSpindleSpeed) {
            newUniversalMilling.setMaxSpindleSpeed(maxSpindleSpeed);
            return this;
        }

        public Builder maxMillingSped(int maxMillingSped) {
            newUniversalMilling.setMaxMillingSped(maxMillingSped);
            return this;
        }

        public Builder tableArea(int tableArea) {
            newUniversalMilling.setTableArea(tableArea);
            return this;
        }

        public Builder typeMillingM(TypeMillingMachine typeMilling) {
            newUniversalMilling.setTypeMilling(typeMilling);
            return this;
        }

        public Builder degreeLimb(double degreeLimb) {
            newUniversalMilling.setDegreeLimb(degreeLimb);
            return this;
        }

        public UniversalMilling build() {
            return newUniversalMilling;
        }
    }

    @Override
    public String toString() {
        return "UniversalMilling{" +
                super.toString() +
                "typeMilling=" + typeMilling +
                ", degreeLimb (mm)=" + degreeLimb +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)){
            return false;
        }
        if (obj instanceof UniversalMilling) {
            UniversalMilling ob = (UniversalMilling) obj;
            return (typeMilling.equals(ob.typeMilling)) && (degreeLimb == ob.degreeLimb);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), typeMilling, degreeLimb);
    }
}
