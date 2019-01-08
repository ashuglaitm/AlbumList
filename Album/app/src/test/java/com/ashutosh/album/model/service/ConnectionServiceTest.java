package com.ashutosh.album.model.service;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static junit.framework.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({URL.class, ConnectionService.class})
public class ConnectionServiceTest {

    private final static String serverURL = "https://jsonplaceholder.typicode.com/albums";

    /**
     * Mock test to download content from server and read in form of string data
     */
    @Test
    public void openConnectionTest() {
        try {
            URL mockUrl = mock(URL.class);
            HttpsURLConnection mockHttpsURLConnection = mock(HttpsURLConnection.class);

            ConnectionService connectionService = new ConnectionService();
            connectionService.setURL(serverURL);
            whenNew(URL.class).withArguments(serverURL).thenReturn(mockUrl);
            when(mockUrl.openConnection()).thenReturn(mockHttpsURLConnection);
            when(mockHttpsURLConnection.getResponseCode()).thenReturn(HttpsURLConnection.HTTP_OK);

            InputStream mockInputStream = mock(InputStream.class);
            InputStreamReader mockInputStreamReader = mock(InputStreamReader.class);
            BufferedReader mockBufferReader = mock(BufferedReader.class);

            when(mockHttpsURLConnection.getInputStream()).thenReturn(mockInputStream);
            whenNew(InputStreamReader.class).withArguments(mockInputStream).thenReturn(mockInputStreamReader);
            whenNew(BufferedReader.class).withArguments(mockInputStreamReader).thenReturn(mockBufferReader);

            when(mockBufferReader.readLine()).thenReturn(generateTestData()).thenReturn(null);

            assertEquals(connectionService.openConnection(), generateTestData());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    private String generateTestData() {
        String sb = "{" +
                "\"userId\":\"1\"," +
                "\"id\":\"1\"," +
                "\"title\":\"albumtitle\"" +
                "}";
        return sb;
    }

}
