package recipe.embeddables;


import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    private String Country;

    public Address() {
    }

    public Address(String country) {
        Country = country;
    }
}
