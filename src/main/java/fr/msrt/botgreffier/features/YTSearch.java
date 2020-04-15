package fr.msrt.botgreffier.features;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import fr.msrt.botgreffier.Config;

import javax.annotation.Nullable;
import java.util.List;

public class YTSearch {

    private final YouTube youTube;

    public YTSearch() {
        YouTube tube = null;
        try {
            tube = new YouTube.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(),
                        JacksonFactory.getDefaultInstance(),
                        null
                    )
                    .setApplicationName("BotGreffier")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        youTube = tube;
    }

    @Nullable
    public String getYTSearch(String query) {

        try {
            List<SearchResult> results = youTube.search()
                    .list("id,snippet")
                    .setQ(query)
                    .setMaxResults(1L)
                    .setType("video")
                    .setFields("items(id/videoId)")
                    .setKey(Config.YT_API_KEY)
                    .execute()
                    .getItems();
            if (!results.isEmpty()) {
                return "https://www.youtube.com/watch?v=" + results.get(0).getId().getVideoId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}