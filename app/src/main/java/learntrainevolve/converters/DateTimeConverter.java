package learntrainevolve.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {

    @Override
    public String convert(LocalDateTime dateTime) {
        String timeAndDate = null;
        try {
            timeAndDate = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        } catch(Exception e ) {e.printStackTrace();}
        return timeAndDate;
    }

    @Override
    public LocalDateTime unconvert(String dateTimeRepresentation) {
        LocalDateTime localDateTime = null;
        try {localDateTime =
         LocalDateTime.parse(dateTimeRepresentation); }
        catch (Exception e) {
            e.printStackTrace();
        }
        return localDateTime;
    }
}
