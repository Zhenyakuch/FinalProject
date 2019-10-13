package library;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Genre {
    FANTASY("1"),
    NOVEL("2"),
    ACTION("3"),
    SCIENCE("4"),
    PHILOSOPHY("5");

    private String id;
    private static Map<String, Genre> GENRE_MAP;

    private Genre(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    static {
        Map<String, Genre> map = new ConcurrentHashMap<String, Genre>();
        for (Genre g : Genre.values()) {
            map.put(g.getId(), g);
        }
        GENRE_MAP = Collections.unmodifiableMap(map);
    }

    public static Genre get(String id) {
        return GENRE_MAP.get(id);
    }

}
