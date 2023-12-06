package andrey.code.store.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "quote")
public class QuoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String content;

    @Builder.Default
    @JsonProperty("date_of_creation")
    Instant dateOfCreation = Instant.now();

    @JsonProperty("date_of_update")
    Instant dateOfUpdate;

    @ManyToOne
    AppUserEntity appUser;

    Integer upvote = 0;

    Integer downvote = 0;
}
