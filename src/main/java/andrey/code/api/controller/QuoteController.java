package andrey.code.api.controller;

import andrey.code.api.dto.AckDTO;
import andrey.code.api.dto.QuoteDTO;
import andrey.code.api.service.QuoteService;
import andrey.code.store.entity.QuoteEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class QuoteController {

    QuoteService quoteService;

    private static final String CREATE_QUOTE = "/api/app_users/{app_user_id}/quotes";
    private static final String GET_ALL_QUOTES = "/api/quotes";
    private static final String GET_10TOP_QUOTES = "/api/quotes/top";
    private static final String GET_10WORSE_QUOTES = "/api/quotes/worse";
    private static final String GET_RANDOM_QUOTE = "/api/quotes/random";
    private static final String UPDATE_QUOTE_CONTENT = "/api/quotes/{quote_id}";
    private static final String UPVOTE_QUOTE = "/api/quotes/{quote_id}/upvote";
    private static final String DOWNVOTE_QUOTE = "/api/quotes/{quote_id}/downvote";
    private static final String DELETE_QUOTE = "/api/quotes/{quote_id}";


    @PostMapping(CREATE_QUOTE)
    public QuoteDTO createQuote(
            @PathVariable("app_user_id") Long id,
            @ModelAttribute QuoteEntity quote){

       return quoteService.createQuote(id, quote);
    }

    @GetMapping(GET_ALL_QUOTES)
    public List<QuoteDTO> getAllQuotes(){

        return quoteService.getAllQuotes();
    }

    @GetMapping(GET_10TOP_QUOTES)
    public List<QuoteDTO> get10TopQuotes(){

        return quoteService.get10TopQuotes();
    }

    @GetMapping(GET_10WORSE_QUOTES)
    public List<QuoteDTO> get10WorseQuotes(){

        return quoteService.get10WorseQuotes();
    }

    @GetMapping(GET_RANDOM_QUOTE)
    public List<QuoteDTO> getRandomQuote(){

        return quoteService.getRandomQuote();
    }

    @PatchMapping(UPDATE_QUOTE_CONTENT)
    public QuoteDTO updateQuoteContent(
            @PathVariable("quote_id") Long id,
            @ModelAttribute QuoteEntity quote){

        return quoteService.updateQuoteContent(id, quote);
    }

    @PatchMapping(UPVOTE_QUOTE)
    public QuoteDTO upvoteQuote(
            @PathVariable("quote_id") Long id){

        return quoteService.upvoteQuote(id);
    }

    @PatchMapping(DOWNVOTE_QUOTE)
    public QuoteDTO downvoteQuote(
            @PathVariable("quote_id") Long id){

        return quoteService.downvoteQuote(id);
    }

    @DeleteMapping(DELETE_QUOTE)
    public AckDTO deleteQuote(
            @PathVariable("quote_id") Long id){

        return quoteService.deleteQuote(id);
    }
}
