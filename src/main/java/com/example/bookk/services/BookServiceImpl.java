package com.example.bookk.services;

import com.example.bookk.data.model.*;
import com.example.bookk.web.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final String[] BOOKS_NAME = new String[]{"Javascript", "React", "Database", "Java", "Spring", "Springboot", "Python", "Django"};

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Book findBookById(String id) throws BookNotFoundException {
        final String URL = "https://www.googleapis.com/books/v1/volumes/{id}";
        ResponseEntity<SearchResult> apiResponseEntity = restTemplate.getForEntity(URL, SearchResult.class, id);
        SearchResult searchResult = apiResponseEntity.getBody();
        log.info("Search result --> {}", searchResult);
        if (searchResult == null)
            throw new BookNotFoundException(String.format("No book with this id %s found. search for a valid book", id));

        String bookId = searchResult.getId();
        VolumeInfo volumeInfo = searchResult.getVolumeInfo();
        String title = volumeInfo.getTitle();
        String description = volumeInfo.getDescription();
        ImageLink imageLink = volumeInfo.getImageLinks();
        String smallThumbnail = imageLink.getSmallThumbnail();
        String thumbnails = imageLink.getThumbnail();
        String previewLinks = volumeInfo.getPreviewLink();
        String publisher = volumeInfo.getPublisher();
        List<String> authors = volumeInfo.getAuthors();
        return bookMapper(bookId, title, publisher,  authors, previewLinks, description,thumbnails, smallThumbnail);
    }


    @Override
    public List<Book> search(String searchSentence) {
        searchSentence = searchSentence.replaceAll(" ", "");
        final String URI = "https://www.googleapis.com/books/v1/volumes?q=" + searchSentence;
        return getBooksResponse(URI);
    }

    private Book bookMapper(String id, String title, String publisher, List<String> author, String previewLink, String description, String smallImage, String image) {
        Book book = new Book();
        book.setId(id);
        book.setDescription(description.substring(0, 40));
        book.setImage(image);
        book.setPreviewLink(previewLink);
        book.setPublisher(publisher);
        book.setSmallImage(smallImage);
        book.setTitle(title);
        book.setAuthors(author);
        return book;
    }

    @Override
    public List<Book> getProgrammingBooks() {
        final String URI = "https://www.googleapis.com/books/v1/volumes?q=" + BOOKS_NAME[random(BOOKS_NAME.length)];
        return getBooksResponse(URI);
    }

    private List<Book> getBooksResponse(String URI) {
        ResponseEntity<SearchApiResponse> apiResponseResponseEntity = restTemplate.getForEntity(URI, SearchApiResponse.class);
        SearchApiResponse apiResponse = apiResponseResponseEntity.getBody();
        assert apiResponse != null;
        return apiResponse.getItems().stream().map(
                searchResults -> {
                    String id = searchResults.getId();
                    VolumeInfo volumeInfo = searchResults.getVolumeInfo();
                    String title = volumeInfo.getTitle();
                    String publisher = volumeInfo.getPublisher();
                    List<String> author = volumeInfo.getAuthors();
                    String previewLink = volumeInfo.getPreviewLink();
                    String description = volumeInfo.getDescription();
                    ImageLink imageLinks = volumeInfo.getImageLinks();
                    String smallImage = imageLinks.getSmallThumbnail();
                    String image = imageLinks.getThumbnail();
                    return bookMapper(id, title, publisher, author, previewLink, description, smallImage, image);
                }
        ).collect(Collectors.toList());
    }

    private int random(int max){
        return (int)Math.floor(Math.random() * (max + 1) + 0);
    }

}

