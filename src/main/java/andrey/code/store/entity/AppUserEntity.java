package andrey.code.store.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "app_user")
public class AppUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String email;

    String password;

    @Builder.Default
    @JsonProperty("date_of_creation")
    Instant dateOfCreation = Instant.now();

    @OneToMany
    @JoinColumn(name = "app_user_id")
    List<QuoteEntity> quotes;
}
