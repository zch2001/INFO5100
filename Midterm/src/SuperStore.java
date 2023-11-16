// Implementation for SuperStores
public class SuperStore implements OrderFulfillment {

    private float processTime = 0.0f;

    @Override
    public float getProcessTime() {
        return processTime;
    }

    @Override
    public void locateAndPrepareItems() {
        // a little bit more time than RegularStore
        processTime += 2.5f;
    }

    @Override
    public void placeInBox() {
        processTime += 1.0f;
    }

    @Override
    public void attachLabelAndOrderInfo() {
        processTime += 0.5f;
    }

    @Override
    public void shipBox() {
        processTime += 1.0f;
    }
}