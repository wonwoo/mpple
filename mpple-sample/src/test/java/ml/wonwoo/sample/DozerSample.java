package ml.wonwoo.sample;

import ml.wonwoo.Mpple;
import ml.wonwoo.dozer.mapped.DozerMapped;
import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.dto.CustomerDto;
import ml.wonwoo.sample.mapper.CustomerMapper;
import org.junit.Test;

public class DozerSample extends AbstractSample {

    @Test
    public void dozer_sample() {
        CustomerMapper mapper = Mpple.builder()
            .mapped(new DozerMapped())
            .target(CustomerMapper.class);
        Customer customer = createCustomer();
        CustomerDto customerDto = mapper.customerDto(customer);
        assertThatCustomerDto(customerDto);
    }
}
