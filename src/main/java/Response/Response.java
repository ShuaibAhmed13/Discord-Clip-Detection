package Response;

public class Response {
    Integer frameCount;
    String error;
    Result[] result;

    public Integer getFrameCount() {
        return frameCount;
    }

    public String getError() {
        return error;
    }

    public Result getResult(int index) {
        return result[index];
    }

}

