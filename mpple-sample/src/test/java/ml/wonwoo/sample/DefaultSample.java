package ml.wonwoo.sample;

import ml.wonwoo.Mpple;
import ml.wonwoo.mapped.DefaultMapped;
import ml.wonwoo.mapped.mapping.MappingInstanceImpl;
import ml.wonwoo.sample.domain.Customer;
import ml.wonwoo.sample.dto.CustomerDto;
import ml.wonwoo.sample.mapper.CustomerMapper;
import org.junit.Test;

public class DefaultSample extends AbstractSample {

    @Test
    public void default_sample() {
        CustomerMapper mapper = Mpple.builder()
            .target(CustomerMapper.class);
        Customer customer = createCustomer();
        CustomerDto customerDto = mapper.customerDto(customer);
        assertThatCustomerDto(customerDto);
    }

    @Test
    public void custom_sample() {
        DefaultMapped mapped = new DefaultMapped();
        MappingInstanceImpl mappingInstance = new MappingInstanceImpl();
        mapped.setMappingInstance(mappingInstance);
        CustomerMapper mapper = Mpple.builder()
            .mapped(mapped)
            .target(CustomerMapper.class);
        Customer customer = createCustomer();
        CustomerDto customerDto = mapper.customerDto(customer);
        assertThatCustomerDto(customerDto);

    }
}
