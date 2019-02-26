package recipe.embeddables;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    @AttributeOverride(name="state",
            column=@Column(name="ADDR_STATE"))
    @Embedded private Address address;

    public User() {

    }

    public User(String name,Address address) {
        this.name  = name;
        this.address = address;
    }
}
