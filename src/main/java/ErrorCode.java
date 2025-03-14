public enum ErrorCode {

    INVALID_CMD_ERR("What is %s ?"),
    INVALID_CMD_ARGS_ERR("Missing argument for command %s"),

    USAGE_ERR_TODO(ErrorCode.TODO_USAGE),
    USAGE_ERR_DEADLINE_MISSING("No deadline provided. " + ErrorCode.DEADLINE_USAGE),
    USAGE_ERR_DEADLINE_INVALID(ErrorCode.DEADLINE_USAGE),
    USAGE_ERR_EVENT_TIME_MISSING("Please specify a start and end time. " + ErrorCode.EVENT_USAGE),
    USAGE_ERR_EVENT_INVALID(ErrorCode.EVENT_USAGE),

    INVALID_TASK_LETTER("Invalid task detected."),
    INVALID_DATE_ERR("Invalid date/time format: %s"),

    INVALID_INDEX_ERR("Invalid task number: %d"),


    IO_FILE_WRITE_ERR("Error writing to file: %s"),
    IO_FILE_READ_ERR("Error reading file: %s"),
    IO_INVALID_TASK_ERR("Error reading task at line %s");


    // Define constants after enum values
    private static final String TODO_USAGE = "Usage: todo <description>";
    private static final String DEADLINE_USAGE = "Usage: deadline <description> /by <deadline>";
    private static final String EVENT_USAGE = "Usage: event <event description> /from <event from> /to <event to>";



    private final String messageTemplate;

    ErrorCode(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }


    public String getMessage(Object... args) {

        if (args == null || args.length == 0) {
            return messageTemplate; // Return raw message if no arguments provided
        }
        return String.format(messageTemplate, args);
    }

}
