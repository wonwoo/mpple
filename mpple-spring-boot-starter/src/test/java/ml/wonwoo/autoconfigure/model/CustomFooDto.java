package ml.wonwoo.autoconfigure.model;

import java.util.Optional;

public class CustomFooDto {

    private Optional<String> name;

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

}
