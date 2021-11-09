package entity;

import java.util.Objects;

/**
 * The class {@code Lathe} extends class {@code Machine}.
 * This class contains fields describing the characteristics of lathes.
 */
public class Lathe extends Machine {
    /** Maximum workpiece diameter (mm). */
    private int maxDiameter;

    /** Maximum workpiece length (mm). */
    private int maxLengthDetail;

    /** Abilities cutting thread: {@code true} - it is, {@code false} - it isn't. */
    private boolean thread;

    /**
     * Initializes a newly created {@code Lathe} object.
     *
     * @param power power of machine
     * @param weight weight of machine
     * @param model model of machine
     * @param maxDiameter maximum workpiece diameter
     * @param maxLengthDetail maximum workpiece length
     * @param thread abilities cutting thread
     */
    public Lathe(int power, int weight, String model, int maxDiameter, int maxLengthDetail,
                 boolean thread) {
        super(power, weight, model);
        this.maxDiameter = maxDiameter;
        this.maxLengthDetail = maxLengthDetail;
        this.thread = thread;
    }

    public Lathe() {
    }

    public int getMaxDiameter() {
        return maxDiameter;
    }

    public void setMaxDiameter(int maxDiameter) {
        this.maxDiameter = maxDiameter;
    }

    public int getMaxLengthDetail() {
        return maxLengthDetail;
    }

    public void setMaxLengthDetail(int maxLengthDetail) {
        this.maxLengthDetail = maxLengthDetail;
    }

    public boolean isThread() {
        return thread;
    }

    /**
     * Pattern builder
     */
    public static class Builder{
        private Lathe newLathe;

        public Builder() {
            newLathe = new Lathe();
        }

        public Builder power(int power) {
            newLathe.setPower(power);
            return this;
        }

        public Builder weight(int weight) {
            newLathe.setWeight(weight);
            return this;
        }

        public Builder model(String model) {
            newLathe.setModel(model);
            return this;
        }

        public Builder maxDiameter(int maxDiameter) {
            newLathe.setMaxDiameter(maxDiameter);
            return this;
        }

        public Builder maxLengthDetail(int maxLengthDetail) {
            newLathe.setMaxLengthDetail(maxLengthDetail);
            return this;
        }

        public Builder thread(boolean thread) {
            newLathe.setThread(thread);
            return this;
        }

        public Lathe build() {
            return newLathe;
        }
    }

    /**
     * Set {@code thread} of the lathe.
     *
     * @param thread еhe ability to cut threads
     */
    public void setThread(boolean thread) {
        this.thread = thread;
    }

    @Override
    public String toString() {
        return "Lathe{" +
                super.toString() +
                ", maxDiameter (mm)=" + maxDiameter +
                ", maxLengthDetail (mm)=" + maxLengthDetail +
                ", thread=" + thread +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj instanceof Lathe) {
            Lathe ob = (Lathe) obj;
            return (maxDiameter == ob.maxDiameter) && (maxLengthDetail == ob.maxLengthDetail) && (thread == ob.thread);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxDiameter, maxLengthDetail, thread);
    }
}
