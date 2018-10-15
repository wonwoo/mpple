package ml.wonwoo.model;

import ml.wonwoo.core.Mapping;

public class AccountDto {

    @Mapping("firstName")
    private String first_name;

    private String last_name;

    private String email_test;


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Mapping("lastName")
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_test() {
        return email_test;
    }

    @Mapping("email")
    public void setEmail_test(String email_test) {
        this.email_test = email_test;
    }
}
