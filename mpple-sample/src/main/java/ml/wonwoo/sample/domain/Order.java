package ml.wonwoo.sample.domain;

import java.util.Date;
import java.util.List;

public class Order {

    private Date date;

    private List<OrderItem> items;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
