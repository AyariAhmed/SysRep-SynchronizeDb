import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sale {

    String date;
    String region;
    String product;
    int quantity;
    float cost;
    float amt;
    float tax;
    float total;

    int id;
    int sent;// a flag value to detect if the sale has been sent to HO

    public Sale() {}

    public Sale(String date, String region, String product, int quantity, float cost, float amt, float tax, float total) {
        this.date = date;
        this.region = region;
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
        this.amt = amt;
        this.tax = tax;
        this.total = total;
        this.sent = 0;
    }

    public String[] toStringArray() {

        return new String[]{date, region, product, String.valueOf(quantity), String.valueOf(cost), String.valueOf(amt), String.valueOf(tax), String.valueOf(total)};
    }


    public void initFromResultSet(ResultSet rs) throws SQLException {

        this.date = rs.getString(1);
        this.region = rs.getString(2);
        this.product = rs.getString(3);
        this.quantity = rs.getInt(4);
        this.cost = rs.getFloat(5);
        this.amt = rs.getFloat(6);
        this.tax = rs.getFloat(7);
        this.total = rs.getFloat(8);
        this.id=rs.getInt(9);
        this.sent=rs.getInt(10);

    }

    public int getId() {
        return id;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getRegion() {

        return region;
    }

    public void setRegion(String region) {

        this.region = region;
    }

    public String getProduct() {

        return product;
    }

    public void setProduct(String product) {

        this.product = product;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public float getCost() {

        return cost;
    }

    public void setCost(float cost) {

        this.cost = cost;
    }

    public float getAmt() {

        return amt;
    }

    public void setAmt(float amt) {

        this.amt = amt;
    }

    public float getTax() {

        return tax;
    }

    public void setTax(float tax) {

        this.tax = tax;
    }

    public float getTotal() {

        return total;
    }

    public void setTotal(float total) {

        this.total = total;
    }


    public int getSent() {
        return sent;
    }

    public void setSent(int sent) {
        this.sent = sent;
    }

}
