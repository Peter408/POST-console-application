package network;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;

public abstract class RestRequest {
    protected HttpURLConnection connection;

    public RestRequest(String uri) throws MalformedURLException, IOException {
        connection = (HttpURLConnection) new URL(uri).openConnection();
        setRequiredHeaders();
    }

    public abstract String execute() throws IOException;

    public abstract String execute(String body) throws IOException;

    private void setRequiredHeaders() {
        // I want json returned
        connection.setRequestProperty("Accept", "application/json");
        // I'm sending json
        connection.setRequestProperty("Content-Type", "application/json");
        // Required authentication token
        connection.setRequestProperty("x-team-id", "jrob");
    }
}