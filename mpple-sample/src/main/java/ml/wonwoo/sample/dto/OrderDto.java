package ml.wonwoo.sample.dto;

import java.util.Date;
import java.util.List;

public class OrderDto {

    private Date date;

    private List<OrderItemDto> items;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
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
