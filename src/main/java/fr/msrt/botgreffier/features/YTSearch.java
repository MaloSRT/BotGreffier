package fr.msrt.botgreffier.features;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import fr.msrt.botgreffier.config.Config;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class YTSearch {

    private final YouTube youTube;

    public YTSearch() {
        YouTube tube = null;
        try {
            tube = new YouTube.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(),
                        GsonFactory.getDefaultInstance(),
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
                    .list(Arrays.asList("id", "snippet"))
                    .setQ(query)
                    .setMaxResults(1L)
                    .setType(Collections.singletonList("video"))
                    .setFields("items(id/videoId)")
                    .setKey(Config.getStringValue("yt_api_key"))
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