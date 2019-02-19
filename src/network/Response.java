package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;

public class Response {
    private int statusCode;
    private StringBuilder result;
    private BufferedReader bodyReader;
    private HttpURLConnection connection;

    Response(HttpURLConnection connection) throws IOException {
        this.result = new StringBuilder();
        this.connection = connection;

        this.createReader();

        this.read();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    String getBody() {
        return this.result.toString();
    }

    private void createReader() {
        try {
            this.statusCode = connection.getResponseCode();
            this.bodyReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException ioex) {
            // Not clear what was happening, so noting here:
            // Non 200s raise IOException when getResponseCode invoked
            // So we want to get the error stream instead to read the body
            this.bodyReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
    }

    private void read() throws IOException {
        String line;

        while ((line = bodyReader.readLine()) != null) {
            this.result.append(line);
        }
    }

    @Override
    public String toString() {
        return this.getBody();
    }
}