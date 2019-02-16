package network;

import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;

public class Put extends RestRequest {
  public Put(URL uri) throws MalformedURLException, IOException {
    super(uri);
    connection.setRequestMethod("PUT");
  }

  public Response execute() throws IOException {
    return this.execute("");
  }

  public Response execute(String body) throws IOException {
    connection.setDoOutput(true);
    connection.getOutputStream().write(body.getBytes("UTF-8"));

    Response response = new Response(connection);

    connection.disconnect();

    return response;
  }
}