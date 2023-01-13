package Http;

import Response.Response;
import com.google.gson.Gson;
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
//        File file = new File("src/main/java/Http/image0.png");
//        getInfoByPic(file);
//        System.out.println(getInfoByUrlApache("https://images.plurk.com/32B15UXxymfSMwKGTObY5e.jpg"));
        Response responseType = getInfoByUrlApache("https://images.plurk.com/32B15UXxymfSMwKGTObY5e.jpg");
        System.out.println(responseType.getFrameCount());
        System.out.println(responseType.getResult(0).getSimilarity());
//        getInfoByPicApache(file);
    }

    public static void getInfoByUrl(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.trace.moe/search?url=" + url))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public static Response getInfoByUrlApache(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.trace.moe/search?url=" + url);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        System.out.println(httpResponse.getCode() + " " + httpResponse.getReasonPhrase());
        HttpEntity entity = httpResponse.getEntity();
        String json = EntityUtils.toString(entity);
//        System.out.println(EntityUtils.toString(entity));
//        System.out.println(json);
        Gson gson = new Gson();
        System.out.println(json);
        Response responseType = gson.fromJson(json, Response.class);
//        System.out.println(responseType.getResult(0).getEpisode());
        return responseType;
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

    public static Response getInfoByUrlAnilistApache(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.trace.moe/search?anilistInfo&url=" + url);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        Gson gson = new Gson();
        Response response = gson.fromJson(EntityUtils.toString(entity), Response.class);
        return response;
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

//{"frameCount":12403339,"error":"","result":[{"anilist":10271,"filename":"[SC-OL][Kaiji2][26v2][End][BIG5][848X480].mp4","episode":26,"from":233.25,"to":238.83,"similarity":0.96,"video":"https://media.trace.moe/video/10271/%5BSC-OL%5D%5BKaiji2%5D%5B26v2%5D%5BEnd%5D%5BBIG5%5D%5B848X480%5D.mp4?t=236.04000000000002&now=1673571600&token=Ao6LnZADy5LC9tBW4DDqREX37uE","image":"https://media.trace.moe/image/10271/%5BSC-OL%5D%5BKaiji2%5D%5B26v2%5D%5BEnd%5D%5BBIG5%5D%5B848X480%5D.mp4.jpg?t=236.04000000000002&now=1673571600&token=2j3vyQyWqn8SS3rD8hHYyrg1eE"},{"anilist":114535,"filename":"[Ohys-Raws] Fumetsu no Anata e - 08 (NHKE 1280x720 x264 AAC).mp4","episode":8,"from":1483.75,"to":1483.75,"similarity":0.8631869053130837,"video":"https://media.trace.moe/video/114535/%5BOhys-Raws%5D%20Fumetsu%20no%20Anata%20e%20-%2008%20(NHKE%201280x720%20x264%20AAC).mp4?t=1483.75&now=1673571600&token=8PqnbewyCDU8EuJ8etAv6lrhak","image":"https://media.trace.moe/image/114535/%5BOhys-Raws%5D%20Fumetsu%20no%20Anata%20e%20-%2008%20(NHKE%201280x720%20x264%20AAC).mp4.jpg?t=1483.75&now=1673571600&token=lPiEdDfXimSafqtaJqpBCXfE4"},{"anilist":20662,"filename":"[Leopard-Raws] Uta no Prince-sama - Maji Love Revolutions - 06 RAW (MBS 1280x720 x264 AAC).mp4","episode":6,"from":797.33,"to":799,"similarity":0.86267821634201,"video":"https://media.trace.moe/video/20662/%5BLeopard-Raws%5D%20Uta%20no%20Prince-sama%20-%20Maji%20Love%20Revolutions%20-%2006%20RAW%20(MBS%201280x720%20x264%20AAC).mp4?t=798.165&now=1673571600&token=N0unGDakZF5n2T1ReTFSZ9nwmMI","image":"https://media.trace.moe/image/20662/%5BLeopard-Raws%5D%20Uta%20no%20Prince-sama%20-%20Maji%20Love%20Revolutions%20-%2006%20RAW%20(MBS%201280x720%20x264%20AAC).mp4.jpg?t=798.165&now=1673571600&token=HjWNaBsqqQjWrQn45JwSQv5Di5M"},{"anilist":6033,"filename":"[52wy][Dragon_Ball_Kai][092][x264_aac][Chs][BDRip][720P][298E9E99].mp4","episode":92,"from":1048.92,"to":1049.08,"similarity":0.8619608486492751,"video":"https://media.trace.moe/video/6033/%5B52wy%5D%5BDragon_Ball_Kai%5D%5B092%5D%5Bx264_aac%5D%5BChs%5D%5BBDRip%5D%5B720P%5D%5B298E9E99%5D.mp4?t=1049&now=1673571600&token=E0uVtJ8Rhloss5ARKVuWWkPx0vM","image":"https://media.trace.moe/image/6033/%5B52wy%5D%5BDragon_Ball_Kai%5D%5B092%5D%5Bx264_aac%5D%5BChs%5D%5BBDRip%5D%5B720P%5D%5B298E9E99%5D.mp4.jpg?t=1049&now=1673571600&token=CTI5jyCHRvWc37wyR7DUkPhWD8k"},{"anilist":113851,"filename":"[Impatience] Breakers - 05 [720p][238FE27A].mp4","episode":5,"from":158.25,"to":159.58,"similarity":0.8618035763229973,"video":"https://media.trace.moe/video/113851/%5BImpatience%5D%20Breakers%20-%2005%20%5B720p%5D%5B238FE27A%5D.mp4?t=158.91500000000002&now=1673571600&token=1AeA7YAa4YdNjYPovSaj0tQHVg","image":"https://media.trace.moe/image/113851/%5BImpatience%5D%20Breakers%20-%2005%20%5B720p%5D%5B238FE27A%5D.mp4.jpg?t=158.91500000000002&now=1673571600&token=HXe6lcrIYp1j2nkTDiVPIfP1k4"},{"anilist":3239,"filename":"Cream Lemon 25 - Moriyama Tou Special 2 - Afterschool XXX (640x480 DivX5).mp4","episode":25,"from":1144.92,"to":1147.25,"similarity":0.8616200755497587,"video":"https://media.trace.moe/video/3239/Cream%20Lemon%2025%20-%20Moriyama%20Tou%20Special%202%20-%20Afterschool%20XXX%20(640x480%20DivX5).mp4?t=1146.085&now=1673571600&token=JA0rmJFHsSFlxGOPAwVYtrGH8","image":"https://media.trace.moe/image/3239/Cream%20Lemon%2025%20-%20Moriyama%20Tou%20Special%202%20-%20Afterschool%20XXX%20(640x480%20DivX5).mp4.jpg?t=1146.085&now=1673571600&token=gx6JfHR9uoWGfGvIT2Aor7GysNQ"},{"anilist":2340,"filename":"(18禁アニメ) (無修正) 外道学園2 魔王アクィフェルの嘆き.mp4","episode":null,"from":542.92,"to":545.67,"similarity":0.8614434539959896,"video":"https://media.trace.moe/video/2340/(18%E7%A6%81%E3%82%A2%E3%83%8B%E3%83%A1)%20(%E7%84%A1%E4%BF%AE%E6%AD%A3)%20%E5%A4%96%E9%81%93%E5%AD%A6%E5%9C%922%20%E9%AD%94%E7%8E%8B%E3%82%A2%E3%82%AF%E3%82%A3%E3%83%95%E3%82%A7%E3%83%AB%E3%81%AE%E5%98%86%E3%81%8D.mp4?t=544.295&now=1673571600&token=mUNSsZMbpIKf1ypQ7L3XYPKxH4","image":"https://media.trace.moe/image/2340/(18%E7%A6%81%E3%82%A2%E3%83%8B%E3%83%A1)%20(%E7%84%A1%E4%BF%AE%E6%AD%A3)%20%E5%A4%96%E9%81%93%E5%AD%A6%E5%9C%922%20%E9%AD%94%E7%8E%8B%E3%82%A2%E3%82%AF%E3%82%A3%E3%83%95%E3%82%A7%E3%83%AB%E3%81%AE%E5%98%86%E3%81%8D.mp4.jpg?t=544.295&now=1673571600&token=eKKZAd8p1BIUpZvgDqObyxevSEI"},{"anilist":99634,"filename":"[Ohys-Raws] Shingeki no Kyojin - 16.5A OAD (NHKG 1280x720 x264 AAC).mp4","episode":16.5,"from":495.25,"to":497.33,"similarity":0.8606921962007936,"video":"https://media.trace.moe/video/99634/%5BOhys-Raws%5D%20Shingeki%20no%20Kyojin%20-%2016.5A%20OAD%20(NHKG%201280x720%20x264%20AAC).mp4?t=496.28999999999996&now=1673571600&token=AhvG1Igj5QMAddsSSorDqshgBlY","image":"https://media.trace.moe/image/99634/%5BOhys-Raws%5D%20Shingeki%20no%20Kyojin%20-%2016.5A%20OAD%20(NHKG%201280x720%20x264%20AAC).mp4.jpg?t=496.28999999999996&now=1673571600&token=vdIjodDdYFPRyLg31Bs4PuWyGwg"},{"anilist":1735,"filename":"[XFSUB][Naruto Shippuuden][546_326][BIG5][x264 1280x720 AAC].mp4","episode":"326|546","from":631.25,"to":632.33,"similarity":0.8584671829713182,"video":"https://media.trace.moe/video/1735/%5BXFSUB%5D%5BNaruto%20Shippuuden%5D%5B546_326%5D%5BBIG5%5D%5Bx264%201280x720%20AAC%5D.mp4?t=631.79&now=1673571600&token=bKa1ibUiEoOpRuDrlTp4VnBWkw","image":"https://media.trace.moe/image/1735/%5BXFSUB%5D%5BNaruto%20Shippuuden%5D%5B546_326%5D%5BBIG5%5D%5Bx264%201280x720%20AAC%5D.mp4.jpg?t=631.79&now=1673571600&token=32nVbq9rbrpYofOGD0inD74ZJIw"},{"anilist":113851,"filename":"[Impatience] Breakers - 06 [720p][0D2EA671].mp4","episode":6,"from":27.83,"to":28.58,"similarity":0.8576009163422572,"video":"https://media.trace.moe/video/113851/%5BImpatience%5D%20Breakers%20-%2006%20%5B720p%5D%5B0D2EA671%5D.mp4?t=28.205&now=1673571600&token=rrzfgZEFedzB3ihyKSrfpHoo","image":"https://media.trace.moe/image/113851/%5BImpatience%5D%20Breakers%20-%2006%20%5B720p%5D%5B0D2EA671%5D.mp4.jpg?t=28.205&now=1673571600&token=Wi2oMXRGuMT51j9vPhQbuAvYM"}]}
