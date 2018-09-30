package ml.wonwoo.sample;

import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.dto.CustomerDto;
import ml.wonwoo.sample.mapper.SpringMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootSample extends AbstractSample {

    @Autowired
    private SpringMapper springMapper;

    @Test
    public void spring_boot_sample() {
        Customer customer = createCustomer();
        CustomerDto customerDto = springMapper.customerDto(customer);
        assertThatCustomerDto(customerDto);
    }
}
