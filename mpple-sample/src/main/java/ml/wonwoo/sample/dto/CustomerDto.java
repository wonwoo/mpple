package ml.wonwoo.sample.dto;

public class CustomerDto {

    private String name;

    private AddressDto address;

    private OrderDto order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
            "name='" + name + '\'' +
            ", address=" + address +
            ", order=" + order +
            '}';
    }
}
