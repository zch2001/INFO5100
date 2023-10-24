public class MVCPatternDemo {
    public static void main(String[] args) {
        // 从数据库或其他来源获取数据
        MoneyCoin coin = new MoneyCoin(100.0, "2023-10-06", "owner1234");
        CoinView view = new CoinView();
        FedCoinController controller = new FedCoinController(coin, view);

        controller.updateView();

        // 更新模型数据
        controller.setCoinValue(150.0);
        controller.updateView();
    }
}
