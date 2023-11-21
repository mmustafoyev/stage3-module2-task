package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mjc.school.repository.utils.Utils.getRandomContentByFilePath;
import static com.mjc.school.repository.utils.Utils.getRandomDate;

public class NewsData {
    private static final String CONTENT_FILE_NAME = "content";
    private static final String NEWS_FILE_NAME = "news";
    private static NewsData newsData;
    private static List<NewsModel> newsList;

    public NewsData(List<AuthorModel> authorModelList) {
        init(authorModelList);
    }
    public static NewsData getNewsData(List<AuthorModel> authorModelList){
        if(newsData == null)
            newsData = new NewsData(authorModelList);
        return newsData;
    }
    private void init(List<AuthorModel> authorModelList) {
        newsList = new ArrayList<>();
        Random r = new Random();
        for (long i = 1; i < 30; i++) {
            LocalDateTime date = getRandomDate();
            newsList.add(
                    new NewsModel(
                            i,
                            getRandomContentByFilePath(NEWS_FILE_NAME),
                            getRandomContentByFilePath(CONTENT_FILE_NAME),
                            date,
                            date,
                            authorModelList.get(r.nextInt(authorModelList.size())).getId()
                    ));
        }
    }

    public List<NewsModel> getNewsList(){
        return newsList;
    }
}
