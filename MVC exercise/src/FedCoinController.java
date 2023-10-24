public class FedCoinController {
    private MoneyCoin coin;
    private CoinView view;

    public FedCoinController(MoneyCoin coin, CoinView view){
        this.coin = coin;
        this.view = view;
    }

    public void setCoinValue(double value){
        coin.setValue(value);
    }

    public double getCoinValue(){
        return coin.getValue();
    }

    public void setCoinIssueDate(String issueDate){
        coin.setIssueDate(issueDate);
    }

    public String getCoinIssueDate(){
        return coin.getIssueDate();
    }

    public void setCoinOwnerID(String ownerID){
        coin.setOwnerID(ownerID);
    }

    public String getCoinOwnerID(){
        return coin.getOwnerID();
    }

    public void updateView(){
        view.printCoinDetails(coin.getValue(), coin.getIssueDate(), coin.getOwnerID());
    }
}
