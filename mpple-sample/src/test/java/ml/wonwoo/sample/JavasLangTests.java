package ml.wonwoo.sample;

import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.collection.List;
import java.util.Date;
import java.util.Iterator;
import ml.wonwoo.Mpple;
import ml.wonwoo.sample.domain.JavasCustomer;
import ml.wonwoo.sample.domain.OrderItem;
import ml.wonwoo.sample.dto.JavasCustomerDto;
import ml.wonwoo.sample.dto.OrderItemDto;
import ml.wonwoo.sample.mapper.CustomerMapper;
import org.junit.Test;

public class JavasLangTests {

    @Test
    public void default_sample() {
        CustomerMapper mapper = Mpple.builder()
            .target(CustomerMapper.class);
        JavasCustomer customer = createCustomer();
        JavasCustomerDto javasCustomerDto = mapper.javasCustomerDto(customer);
        assertThatCustomerDto(javasCustomerDto);
    }

    private JavasCustomer createCustomer() {

        OrderItem orderItem = new OrderItem();
        orderItem.setName("iphone");
        orderItem.setPrice(9999999);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setName("ipad");
        orderItem1.setPrice(100000000);

        JavasCustomer javasCustomer = new JavasCustomer();
        javasCustomer.setDate(new Date());
        javasCustomer.setItems(List.of(orderItem, orderItem1));
        return javasCustomer;
    }

    private void assertThatCustomerDto(JavasCustomerDto customerDto) {
        Iterator<OrderItemDto> iterator = customerDto.getItems().iterator();
        OrderItemDto orderItemDto = iterator.next();
        assertThat(orderItemDto.getName()).isEqualTo("iphone");
        assertThat(orderItemDto.getPrice()).isEqualTo(9999999);
        OrderItemDto orderItemDto1 = iterator.next();
        assertThat(orderItemDto1.getName()).isEqualTo("ipad");
        assertThat(orderItemDto1.getPrice()).isEqualTo(100000000);
    }
}
