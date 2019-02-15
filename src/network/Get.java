package network;

import java.io.IOException;
import java.net.MalformedURLException;

public class Get extends RestRequest {
    public Get(String uri) throws MalformedURLException, IOException {
        super(uri);
        connection.setRequestMethod("GET");
    }

    @Override
    public String execute() throws IOException {
        Response response = new Response(connection);

        connection.disconnect();

        return response.getBody();
    }

    @Override
    public String execute(String body) throws IOException {
        return this.execute();
    }
}