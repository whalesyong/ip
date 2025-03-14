import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of {@link CustomDate} that adds functionality for handling {@link LocalDateTime}.
 * Provides custom formatting for date-time representation.
 */
public class CustomDateTime extends CustomDate {

    public CustomDateTime(LocalDateTime dateTime) {
        super(dateTime);
    }

    public LocalDateTime getDateTime() { return dateTime; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}
