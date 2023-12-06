package andrey.code.api.service;

import andrey.code.api.dto.AckDTO;
import andrey.code.api.dto.QuoteDTO;
import andrey.code.store.entity.QuoteEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface QuoteService {

    public QuoteDTO createQuote(
            @PathVariable("app_user_id") Long id,
            @ModelAttribute QuoteEntity quote);

    public List<QuoteDTO> getAllQuotes();

    public List<QuoteDTO> get10TopQuotes();

    public List<QuoteDTO> get10WorseQuotes();

    public List<QuoteDTO> getRandomQuote();

    public QuoteDTO updateQuoteContent(
            @PathVariable("quote_id") Long id,
            @ModelAttribute QuoteEntity quote);

    public QuoteDTO upvoteQuote(
            @PathVariable("quote_id") Long id);

    public QuoteDTO downvoteQuote(
            @PathVariable("quote_id") Long id);

    public AckDTO deleteQuote(
            @PathVariable("quote_id") Long id);

}
