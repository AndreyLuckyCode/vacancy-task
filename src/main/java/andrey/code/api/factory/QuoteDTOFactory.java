package andrey.code.api.factory;

import andrey.code.api.dto.QuoteDTO;
import andrey.code.store.entity.QuoteEntity;
import org.springframework.stereotype.Component;

@Component
public class QuoteDTOFactory {

    public QuoteDTO createQuoteDTO(QuoteEntity entity){

        return QuoteDTO
                .builder()
                .id(entity.getId())
                .content(entity.getContent())
                .dateOfCreation(entity.getDateOfCreation())
                .dateOfUpdate(entity.getDateOfUpdate())
                .upvote(entity.getUpvote())
                .downvote(entity.getDownvote())
                .build();
    }
}
