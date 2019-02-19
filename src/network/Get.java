package network;

import java.net.URL;
import java.io.IOException;

public class Get extends RestRequest {
    Get(URL uri) throws IOException {
        super(uri);
        connection.setRequestMethod("GET");
    }

    @Override
    public Response execute() throws IOException {
        Response response = new Response(connection);

        connection.disconnect();

        return response;
    }

    @Override
    public Response execute(String body) throws IOException {
        return this.execute();
    }
}