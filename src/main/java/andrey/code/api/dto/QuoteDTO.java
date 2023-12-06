package andrey.code.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuoteDTO {

    Long id;
    String content;

    @Builder.Default
    @JsonProperty("date_of_creation")
    Instant dateOfCreation = Instant.now();

    @JsonProperty("date_of_update")
    Instant dateOfUpdate;

    Integer upvote = 0;

    Integer downvote = 0;
}
