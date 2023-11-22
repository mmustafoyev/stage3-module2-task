//package com.mjc.school.service.factory;
//
//import com.mjc.school.service.implementation.AuthorService;
//import com.mjc.school.service.implementation.NewsService;
//
//public class ServiceFactory {
//    private static ServiceFactory instance;
//    private final NewsService newsService;
//    private final AuthorService authorService;
//
//    public ServiceFactory() {
//        this.newsService = new NewsService();
//        this.authorService = new AuthorService();
//    }
//
//    public static ServiceFactory getInstance(){
//        if(instance == null)
//            instance = new ServiceFactory();
//        return instance;
//    }
//
//    public NewsService getNewsService(){
//        return newsService;
//    }
//    public AuthorService getAuthorService(){
//        return authorService;
//    }
//}
