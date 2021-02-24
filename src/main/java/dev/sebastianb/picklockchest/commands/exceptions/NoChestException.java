package dev.sebastianb.picklockchest.commands.exceptions;

public class NoChestException extends RuntimeException {

    public NoChestException() {

    }

    public NoChestException(String errorMessage) {
        super(errorMessage);
    }

    public NoChestException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
