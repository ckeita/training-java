package fr.ebiz.computer_database.exception;

/**
 * Created by ebiz on 02/06/17.
 */
public class ServiceException extends RuntimeException {

	public ServiceException(String message) {
		throw new RuntimeException(message);
	}
}
