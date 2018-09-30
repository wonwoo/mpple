package ml.wonwoo.sample;

import java.util.Arrays;
import java.util.Date;
import ml.wonwoo.Mpple;
import ml.wonwoo.sample.domain.Address;
import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.domain.Order;
import ml.wonwoo.sample.domain.OrderItem;
import ml.wonwoo.sample.dto.CustomerDto;
import ml.wonwoo.sample.mapper.CustomerMapper;

public class Main {

    public static void main(String[] args) {

        Address address = new Address();
        address.setCity("성남");
        address.setStreet("어디어디");
        address.setZipcode("1111-122");

        OrderItem orderItem = new OrderItem();
        orderItem.setName("iphone");
        orderItem.setPrice(9999999);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setName("ipad");
        orderItem1.setPrice(100000000);

        Order order = new Order();
        order.setDate(new Date());
        order.setItems(Arrays.asList(orderItem, orderItem1));

        Customer customer = new Customer();
        customer.setName("wonwoo");
        customer.setAddress(address);
        customer.setOrder(order);

        CustomerMapper mapper = Mpple.builder()
            .target(CustomerMapper.class);
        CustomerDto customerDto = mapper.customerDto(customer);
        System.out.println(customerDto);

    }
}
