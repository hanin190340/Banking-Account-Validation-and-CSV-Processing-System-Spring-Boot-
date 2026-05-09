package progressof.Service;


import progressof.DTO.CsvRowDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParserService {

    public List<CsvRowDTO> parse(
            InputStreamReader reader) {

        List<CsvRowDTO> rows = new ArrayList<>();

        try {

            CSVParser parser =
                    CSVFormat.DEFAULT
                            .builder()
                            .setHeader()
                            .setSkipHeaderRecord(true)
                            .build()
                            .parse(reader);

            for (CSVRecord record : parser) {

                CsvRowDTO row = new CsvRowDTO();

                row.setRequestId(
                        record.get("request_id"));

                row.setCustomerReference(
                        record.get("customer_reference"));

                row.setIdentifier1(
                        record.get("identifier_1"));

                row.setIdentifier2(
                        record.get("identifier_2"));

                row.setIdentifier3(
                        record.get("identifier_3"));

                row.setSubmittedAt(
                        record.get("submitted_at"));

                rows.add(row);
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to parse CSV", e);
        }

        return rows;
    }
}