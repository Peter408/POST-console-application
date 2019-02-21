package network;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Post extends RestRequest {

    public Post(URL uri) throws IOException {
        super(uri);
        connection.setRequestMethod("POST");
    }

    @Override
    public Response execute() throws IOException {
        return this.execute("");
    }

    @Override
    public Response execute(String body) throws IOException {
        connection.setDoOutput(true);
        connection.getOutputStream().write(body.getBytes(StandardCharsets.UTF_8));

        Response response = new Response(connection);

        connection.disconnect();

        return response;
    }
}
