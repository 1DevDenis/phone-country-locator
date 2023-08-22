package com.phoneсountry.locator.utility;

import com.phoneсountry.locator.exception.CanNotCreateConnectionException;
import com.phoneсountry.locator.exception.RowsNotFoundException;
import com.phoneсountry.locator.model.CountryCallingCodeEntity;
import com.phoneсountry.locator.repository.CountryCallingCodeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class WikipediaCountryCallingCodeTaker {

    private static final String CAN_NOT_CREATE_CONNECTION_TO_WIKIPEDIA = "Can't create connection to Wikipedia: ";
    private static final String ROWS_DO_NOT_EXIT = "Rows don't exit";
    private static final String TR = "tr";
    private static final String TD = "td";
    private static final String WIKI_TABLE = "table.wikitable";
    private static final String COMMA = ",";
    private static final String BRACKET = "(";
    private static final String REGEX = "\\d+";

    @Value("${wikipedia.url}")
    private String wikipediaUrl;

    private final CountryCallingCodeRepository countryCallingCodeRepository;

    @PostConstruct
    public void takeCountryCallingCode() {
        Document wikiDoc;

        try {
            wikiDoc = Jsoup.connect(wikipediaUrl).get();
        } catch (IOException e) {
            throw new CanNotCreateConnectionException(CAN_NOT_CREATE_CONNECTION_TO_WIKIPEDIA + e);
        }

        Element wikiTable = wikiDoc.select(WIKI_TABLE).get(1);

        if (wikiTable == null) {
            throw new RowsNotFoundException(ROWS_DO_NOT_EXIT);
        }
        Elements wikiRows = wikiTable.select(TR);

        for (Element wikiRow : wikiRows) {
            Elements wikiColumns = wikiRow.select(TD);
            if (wikiColumns.size() >= 2) {
                String countryCallingCode = wikiColumns.get(1).text();
                String country = wikiColumns.get(0).text();

                storeCountryCallingCode(countryCallingCode, country);
            }
        }
    }

    private void storeCountryCallingCode(String countryCallingCode, String country) {
        List<CountryCallingCodeEntity> countryCallingCodeEntities = new ArrayList<>();

        if (countryCallingCode.contains(COMMA) || countryCallingCode.contains(BRACKET)) {
            List<String> numbers = cleanCountryCallingCodes(countryCallingCode);

            for (int i = 1; i < numbers.size(); i++) {
                countryCallingCodeEntities.add(new CountryCallingCodeEntity(
                        country,
                        (numbers.get(0)) + numbers.get(i))
                );
            }
        } else {
            countryCallingCodeEntities.add(new CountryCallingCodeEntity(country, countryCallingCode));
        }

        countryCallingCodeRepository.saveAll(countryCallingCodeEntities);
    }

    private List<String> cleanCountryCallingCodes(String countryCallingCode) {
        List<String> numbers = new ArrayList<>();

        Pattern pattern = Pattern.compile(REGEX);

        Matcher matcher = pattern.matcher(countryCallingCode);

        while (matcher.find()) {
            String match = matcher.group();
            numbers.add(match);
        }

        return numbers;
    }
}
