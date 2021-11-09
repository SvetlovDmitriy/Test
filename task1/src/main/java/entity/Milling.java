package entity;

import java.util.Arrays;
import java.util.Objects;

/**
 * The class {@code Milling} extends class {@code Machine}.
 * This class contains fields describing the characteristics of milling machine.
 */
public abstract class Milling extends Machine{
    /** Maximum spindle speed (rpm). */
    private int maxSpindleSpeed;

    /** Maximum milling speed (mm/m). */
    private int maxMillingSped;

    /** Working area of the table (sq.m.). */
    private int tableArea ;

    /**
     * Initialization new object of {@code Milling}.
     *
     * @param power power of milling machine
     * @param weight weight of milling machine
     * @param model model of milling machine
     * @param maxSpindleSpeed maximum spindle speed of milling machine
     * @param maxMillingSped maximum milling speed of milling machine
     * @param tableArea working area of the table of milling machine
     */
    public Milling(int power, int weight, String model, int maxSpindleSpeed, int maxMillingSped, int tableArea) {
        super(power, weight, model);
        this.maxSpindleSpeed = maxSpindleSpeed;
        this.maxMillingSped = maxMillingSped;
        this.tableArea = tableArea;
    }

    public Milling(){}

    public int getMaxSpindleSpeed() {
        return maxSpindleSpeed;
    }

    public void setMaxSpindleSpeed(int maxSpindleSpeed) {
        this.maxSpindleSpeed = maxSpindleSpeed;
    }

    public int getMaxMillingSped() {
        return maxMillingSped;
    }

    public void setMaxMillingSped(int maxMillingSped) {
        this.maxMillingSped = maxMillingSped;
    }

    public int getTableArea() {
        return tableArea;
    }

    public void setTableArea(int tableArea) {
        this.tableArea = tableArea;
    }

    @Override
    public String toString() {
        return super.toString() +
                "maxSpindleSpeed=" + maxSpindleSpeed +
                ", maxMillingSped=" + maxMillingSped +
                ", tableArea=" + tableArea;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)){
            return false;
        }
        if (obj instanceof Milling) {
            Milling ob = (Milling) obj;
            return (maxSpindleSpeed == ob.maxSpindleSpeed) && (maxMillingSped == ob.maxMillingSped) &&
                    (tableArea == ob.tableArea);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpindleSpeed, maxMillingSped, tableArea);
    }
}
