public interface OrderFulfillment {
    float getProcessTime();

    void locateAndPrepareItems();

    void placeInBox();

    void attachLabelAndOrderInfo();

    void shipBox();
}