package ml.wonwoo.sample;

import ml.wonwoo.Mpple;
import ml.wonwoo.beanutils.mapped.BeanUtilsMapped;
import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.dto.CustomerDto;
import ml.wonwoo.sample.mapper.CustomerMapper;
import org.junit.Test;

public class BeanUtilsSample extends AbstractSample {

    //not use

    @Test(expected = Exception.class)
    public void bean_utils_sample() {
        CustomerMapper mapper = Mpple.builder()
            .mapped(new BeanUtilsMapped())
            .target(CustomerMapper.class);
        Customer customer = createCustomer();
        CustomerDto customerDto = mapper.customerDto(customer);
        assertThatCustomerDto(customerDto);
    }
}
