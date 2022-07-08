
package net.javaguides.springboot.model;

        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import javax.persistence.*;
        import java.time.LocalDateTime;
        import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tableDetails")
public class Employee {

   // @Id
   /* @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;*/
    @Id
    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "flow_name")
    private String flowName;

    @Column(name = "downTimeFrom")
    public LocalDateTime downTimeFrom;

    @Column(name= "downTimeTo")
    public LocalDateTime downTimeTo;
}