package network;

import java.net.URL;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Put extends RestRequest {
  Put(URL uri) throws IOException {
    super(uri);
    connection.setRequestMethod("PUT");
  }

  public Response execute() throws IOException {
    return this.execute("");
  }

  public Response execute(String body) throws IOException {
    connection.setDoOutput(true);
    connection.getOutputStream().write(body.getBytes(StandardCharsets.UTF_8));

    Response response = new Response(connection);

    connection.disconnect();

    return response;
  }
}