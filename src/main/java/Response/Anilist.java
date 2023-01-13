package Response;

public class Anilist {
    Integer id;
    Integer idMal;
    Title title;
    String[] synonyms;
    String isAdult;

    public Integer getId() {
        return id;
    }

    public Integer getIdMal() {
        return idMal;
    }

    public Title getTitle() {
        return title;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public String getIsAdult() {
        return isAdult;
    }
}

