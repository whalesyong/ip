import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//custom date time class for usage with duncan.
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
