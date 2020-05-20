package main.goods;

public class Solid extends Substance{
    /**
     * Creates a pallet from given values
     *
     * @param description - describes the content
     * @param size        - edge length of the pallet in cm
     * @param cooling     - true, if the pallet requires cooling
     * @param duration    - estimated duration of stocking in days
     * @param density     - density in gram per liter
     * @param price       - price per liter
     */
    public Solid(String description, int size, boolean cooling, int duration, double density, int price) {
        super(description, size, size, size, cooling, duration, density, price);
    }

    @Override
    public int getWeight() {
        return (int) (((getWidth() * getHeight() * getDepth()) / 1000) * getDensity());
    }

    @Override
    public int getValue() {
        return ((getWidth() * getHeight() * getDepth()) / 1000) * getPrice();
    }
}
