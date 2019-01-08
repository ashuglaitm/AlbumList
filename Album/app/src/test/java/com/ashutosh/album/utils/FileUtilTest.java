package com.ashutosh.album.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest( FileUtil.class)
@RunWith(PowerMockRunner.class)
public class FileUtilTest {

    /**
     * mock test to test file data
     * @throws IOException exception
     */
    @Test
    public void readDataTest() throws IOException {
            BufferedReader mockBufferReader = mock(BufferedReader.class);
            when(mockBufferReader.readLine()).thenReturn("ashutosh").thenReturn(null);

            assertEquals(FileUtil.getDataFromFile(mockBufferReader), "ashutosh");
    }
}
