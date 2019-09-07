package FriggeSteyaertJamain.be.winkelKassa.domain.register;

public class Purchase {
    private String article;
    private int amount;
    private double price;
    private double discount;
    private int btw;
    private double total;
    private String barcode;

    /*todo constructor corrigeren van purchase
        en functionaliteit toevoegen
    */

    public Purchase(Product product) {
        this.article = product.getName();
        this.amount = 1;
        this.price = product.getPrice();
        this.discount = 0;
        this.btw = product.getBtw().getTariff();
        this.total = amount*price;
        this.barcode = product.getBarcode();
    }

    public String getArticle() {
        return article;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        updateTotal();
        this.amount = amount;
    }

    private void updateTotal() {
        total = amount*price;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public int getBtw() {
        return btw;
    }

    public double getTotal() {
        return total;
    }
}
