package andrey.code.api.service.impl;

import andrey.code.api.dto.AckDTO;
import andrey.code.api.dto.QuoteDTO;
import andrey.code.api.exceptions.BadRequestException;
import andrey.code.api.exceptions.NotFoundException;
import andrey.code.api.factory.QuoteDTOFactory;
import andrey.code.api.service.QuoteService;
import andrey.code.store.entity.AppUserEntity;
import andrey.code.store.entity.QuoteEntity;
import andrey.code.store.repository.AppUserRepository;
import andrey.code.store.repository.QuoteRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {


    QuoteRepository quoteRepository;
    QuoteDTOFactory quoteDTOFactory;
    AppUserRepository appUserRepository;

    String QUOTE_ABSENCE = "Quote with this id doesn't exist";


    @Override
    @Transactional
    public QuoteDTO createQuote(
            @PathVariable("app_user_id") Long id,
            @ModelAttribute QuoteEntity quote) {


        AppUserEntity user = appUserRepository.findById(id).orElseThrow(()
                -> new NotFoundException("AppUser with this id doesn't exist"));

        quote.setAppUser(user);

        if(quote.getContent() == null || quote.getContent().trim().isEmpty()){
            throw new BadRequestException("Quote content can't be empty");
        }

        quoteRepository.saveAndFlush(quote);

        return quoteDTOFactory.createQuoteDTO(quote);
    }

    @Override
    @Transactional
    public List<QuoteDTO> getAllQuotes() {

        List<QuoteEntity> quotes = quoteRepository.findAll();

        if(quotes.isEmpty()){
            throw new NotFoundException("Quotes list is empty");
        }

        return quotes.stream()
                .map(quoteDTOFactory::createQuoteDTO)
                .toList();

    }

    @Override
    @Transactional
    public List<QuoteDTO> get10TopQuotes() {
        List<QuoteEntity> top10Quotes = quoteRepository.findTop10ByOrderByUpvoteDesc();

        if (top10Quotes.isEmpty()) {
            throw new NotFoundException("There are no quotes with upvotes");
        }

        return top10Quotes.stream()
                .map(quoteDTOFactory::createQuoteDTO)
                .toList();
    }

    @Override
    @Transactional
    public List<QuoteDTO> get10WorseQuotes() {

        List<QuoteEntity> worseQuotes = quoteRepository.findTop10ByOrderByUpvoteAsc();

        if(worseQuotes.isEmpty()) {
            throw new NotFoundException("There are no quotes with downvotes");
        }

        return worseQuotes.stream()
                .map(quoteDTOFactory::createQuoteDTO)
                .toList();
    }

    @Override
    @Transactional
    public List<QuoteDTO> getRandomQuote() {

        List<QuoteEntity> allQuotes = quoteRepository.findAll();

        if (allQuotes.isEmpty()) {
            throw new NotFoundException("Quotes list is empty");
        }

        Collections.shuffle(allQuotes);

        QuoteEntity randomQuote = allQuotes.get(0);

        return Collections.singletonList(quoteDTOFactory.createQuoteDTO(randomQuote));
    }

    @Override
    @Transactional
    public QuoteDTO updateQuoteContent(
            @PathVariable("quote_id") Long id,
            @ModelAttribute QuoteEntity quote) {


        QuoteEntity quoteEntity = quoteRepository.findById(id).orElseThrow(()
                -> new NotFoundException(QUOTE_ABSENCE));


        if(quote.getContent() != null && !quote.getContent().trim().isEmpty()){
            quoteEntity.setContent(quote.getContent());
        }

        quoteEntity.setDateOfUpdate(Instant.now());

        quoteRepository.saveAndFlush(quoteEntity);

        return quoteDTOFactory.createQuoteDTO(quoteEntity);
    }

    @Override
    @Transactional
    public QuoteDTO upvoteQuote(
            @PathVariable("quote_id") Long id) {

        QuoteEntity quoteEntity = quoteRepository.findById(id).orElseThrow(()
                -> new NotFoundException(QUOTE_ABSENCE));

        quoteEntity.setUpvote(quoteEntity.getUpvote() +1);

        quoteRepository.saveAndFlush(quoteEntity);

        return quoteDTOFactory.createQuoteDTO(quoteEntity);
    }

    @Override
    @Transactional
    public QuoteDTO downvoteQuote(
            @PathVariable("quote_id") Long id) {

        QuoteEntity quoteEntity = quoteRepository.findById(id).orElseThrow(()
                -> new NotFoundException(QUOTE_ABSENCE));

        quoteEntity.setDownvote(quoteEntity.getDownvote() +1);

        quoteRepository.saveAndFlush(quoteEntity);

        return quoteDTOFactory.createQuoteDTO(quoteEntity);
    }

    @Override
    @Transactional
    public AckDTO deleteQuote(
            @PathVariable("quote_id") Long id) {


        if(quoteRepository.findById(id).isEmpty()){
            throw new NotFoundException("Quote with that ID doesn't exist");
        }

        quoteRepository.deleteById(id);

        return AckDTO.builder().answer(true).build();
    }
}
