package com.washingtonpost.wordpress.rest.api;

import com.washingtonpost.wordpress.rest.api.model.WordPressPost;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * <p>Tests the JAXRSWordPressClient</p>
 */
@SuppressWarnings("unchecked")
public class TestJAXRSWordPressClient {

    @Test
    public void testHappyPath() throws IOException {

        WordPressClient<WordPressPost> client =
                new WordPressClientFactory().withMockResource("off-the-shelf-response.json").build();
        List<WordPressPost> posts = client.getPosts("?whatever=true&foo=bar");

        assertNotNull("List of posts should not be null", posts);

        WordPressPost post = posts.get(0);
        assertEquals(54, post.getId());
        assertEquals("Gulp for cross-newsroom workflows", post.getTitle());
        assertEquals("standard", post.getPostFormat());
        assertEquals("Ernest Hemmingway", post.getAuthor());
    }

    @Test
    public void testReadInputStreamToString() {
        String inputString = "The quick brown fox";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

        JAXRSWordPressClient client = new JAXRSWordPressClient(null, null, null);
        String outputString = client.readInputStreamToString(inputStream);
        assertEquals(inputString, outputString);
    }
}
