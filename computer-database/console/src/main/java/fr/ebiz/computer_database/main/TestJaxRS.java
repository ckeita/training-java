package fr.ebiz.computer_database.main;

import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.util.Util;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ebiz on 06/06/17.
 */
public class TestJaxRS {

	public static void main (String args[]) {

		HttpAuthenticationFeature httpAuthenticationFeature = HttpAuthenticationFeature.basic("ckeita","pass");
		Client client = ClientBuilder.newClient();
		client.register(httpAuthenticationFeature);

		System.out.println(client.target(Util.REST_URI)
				.path(Util.COMPUTERS_PATH)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<ComputerDTO>>(){}));
	}
}
