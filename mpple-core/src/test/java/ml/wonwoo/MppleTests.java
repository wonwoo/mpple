package ml.wonwoo;

import static org.assertj.core.api.Assertions.assertThat;

import ml.wonwoo.model.Account;
import ml.wonwoo.model.AccountDto;
import ml.wonwoo.model.AccountMapper;
import ml.wonwoo.model.Foo;
import ml.wonwoo.model.FooDto;
import ml.wonwoo.model.FooMapper;
import org.junit.Test;

public class MppleTests {

    @Test
    public void defaultTest() {
        FooMapper fooMapper = Mpple.builder()
            .target(FooMapper.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        assertThat(fooMapper.bar(foo)).isEqualTo("wonwoo");
    }

    @Test
    public void mapperTest() {
        FooMapper fooMapper = Mpple.builder()
            .target(FooMapper.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        FooDto fooDto = fooMapper.foo(foo);
        assertThat(fooDto.getFirstName()).isEqualTo("wonwoo");
        assertThat(fooDto.getLastName()).isEqualTo("lee");
    }

    @Test(expected = IllegalArgumentException.class)
    public void voidReturnTest() {
        FooMapper fooMapper = Mpple.builder()
            .target(FooMapper.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        fooMapper.fooBar(foo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void objectReturnTest() {
        FooMapper fooMapper = Mpple.builder()
            .target(FooMapper.class);
        Foo foo = new Foo();
        foo.setFirstName("wonwoo");
        foo.setLastName("lee");
        Object poo = fooMapper.poo(foo);
        assertThat(poo).isNull();
    }

    @Test
    public void annotation() {
        AccountMapper accountMapper = Mpple.builder()
            .target(AccountMapper.class);
        Account account = new Account();
        account.setFirstName("wonwoo");
        account.setLastName("lee");
        account.setEmail("test@test.com");
        AccountDto accountdto = accountMapper.accountdto(account);
        assertThat(accountdto.getFirst_name()).isEqualTo("wonwoo");
        assertThat(accountdto.getLast_name()).isEqualTo("lee");
        assertThat(accountdto.getEmail_test()).isEqualTo("test@test.com");
    }
}