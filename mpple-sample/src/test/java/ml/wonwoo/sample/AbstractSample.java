package ml.wonwoo.sample;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import ml.wonwoo.sample.domain.Address;
import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.domain.Order;
import ml.wonwoo.sample.domain.OrderItem;
import ml.wonwoo.sample.dto.CustomerDto;
import ml.wonwoo.sample.dto.OrderItemDto;

abstract class AbstractSample {

    Customer createCustomer() {

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
        return customer;
    }

    void assertThatCustomerDto(CustomerDto customerDto) {

        assertThat(customerDto.getName()).isEqualTo("wonwoo");
        assertThat(customerDto.getAddress().getCity()).isEqualTo("성남");
        assertThat(customerDto.getAddress().getStreet()).isEqualTo("어디어디");
        assertThat(customerDto.getAddress().getZipcode()).isEqualTo("1111-122");
        assertThat(customerDto.getOrder().getDate()).isNotNull();
        assertThat(customerDto.getOrder().getItems()).hasSize(2);

        Iterator<OrderItemDto> iterator = customerDto.getOrder().getItems().iterator();
        OrderItemDto orderItemDto = iterator.next();
        assertThat(orderItemDto.getName()).isEqualTo("iphone");
        assertThat(orderItemDto.getPrice()).isEqualTo(9999999);
        OrderItemDto orderItemDto1 = iterator.next();
        assertThat(orderItemDto1.getName()).isEqualTo("ipad");
        assertThat(orderItemDto1.getPrice()).isEqualTo(100000000);
    }
}
