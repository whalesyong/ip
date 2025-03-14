import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A wrapper class for {@link LocalDateTime} that provides custom date-related functionalities.
 * This class wraps a {@link LocalDateTime} and overrides toString().
 */
public class CustomDate {
    protected final LocalDateTime dateTime;

    public CustomDate(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public LocalDate getDate() { return dateTime.toLocalDate(); }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(formatter);
    }
}
