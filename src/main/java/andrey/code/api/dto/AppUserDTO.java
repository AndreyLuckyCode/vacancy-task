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
public class AppUserDTO {

    Long id;

    String name;

    String email;

    @Builder.Default
    @JsonProperty("date_of_creation")
    Instant dateOfCreation = Instant.now();

}
