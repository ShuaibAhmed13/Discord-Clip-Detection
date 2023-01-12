package Http;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.FileEntity;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Connection {

    public static void main(String[] args) throws Exception {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY"))
//                .GET()
//                .build();
//        HttpClient httpClient = HttpClient.newHttpClient();
//        System.out.println(request);
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
//        getInfoByUrl("https://images.plurk.com/32B15UXxymfSMwKGTObY5e.jpg");
        File file = new File("src/main/java/Http/image0.png");
//        getInfoByPic(file);
//        getInfoByUrlApache("https://images.plurk.com/32B15UXxymfSMwKGTObY5e.jpg");
        getInfoByPicApache(file);
    }

    public static void getInfoByUrl(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.trace.moe/search?url=" + url))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public static void getInfoByUrlApache(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.trace.moe/search?url=" + url);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        System.out.println(httpResponse.getCode() + " " + httpResponse.getReasonPhrase());
        HttpEntity entity = httpResponse.getEntity();
        System.out.println(EntityUtils.toString(entity));
    }
    public static void getInfoByPicApache(File file) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.trace.moe/search");
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart("image", new FileBody(file));
        httpPost.setEntity(multipartEntityBuilder.build());
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();
        System.out.println(EntityUtils.toString(entity));
    }

//    public static void getInfoByPic(File file) throws Exception {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI("https://api.trace.moe/search"))
//                .setHeader("Content-Type", "image/*")
//                .POST(HttpRequest.BodyPublishers.ofFile(file.getAbsoluteFile().toPath()))
//                .build();
//        HttpClient client = HttpClient.newHttpClient();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response);
//    }
}
