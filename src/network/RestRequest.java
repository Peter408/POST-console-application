package network;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;

public abstract class RestRequest {
    protected HttpURLConnection connection;

    public RestRequest(URL uri) throws MalformedURLException, IOException {
        connection = (HttpURLConnection) uri.openConnection();
        setRequiredHeaders();
    }

    public abstract Response execute() throws IOException;

    public abstract Response execute(String body) throws IOException;

    private void setRequiredHeaders() {
        // I want json returned
        connection.setRequestProperty("Accept", "application/json");
        // I'm sending json
        connection.setRequestProperty("Content-Type", "application/json");
        // Required authentication token
        connection.setRequestProperty("x-team-id", "team-tabs");
    }
}