package ml.wonwoo.sample.domain;

import io.vavr.collection.List;
import java.util.Date;

public class JavasCustomer {

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

    @Override
    public String toString() {
        return "OrderDto{" +
            "date=" + date +
            ", items=" + items +
            '}';
    }
}
