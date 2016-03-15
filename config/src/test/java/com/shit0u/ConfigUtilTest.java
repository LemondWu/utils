package com.shit0u;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shit0u
 * Date: 16/3/15
 * Time: 16:21
 */
public class ConfigUtilTest {

    @Test
    public void testGetProperty() throws Exception {
        String mysqlUser = ConfigUtil.getProperty("mysql.user");
        String mysqlPassword = ConfigUtil.getProperty("mysql.password");

        assertNotNull(mysqlUser);
        assertNotNull(mysqlPassword);
        assertEquals("mysql", mysqlUser);
        assertEquals("passwd", mysqlPassword);
    }

    @Test
    public void testGetPropertyList() throws Exception {
        List<String> list = ConfigUtil.getPropertyList("array.properties");

        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("零",list.get(0));
        assertEquals("壹", list.get(1));
        assertEquals("贰", list.get(2));
    }

    @Test
    public void testGetBoolean() throws Exception {
        Boolean ifLoad = ConfigUtil.getBoolean("if.properties.load");

        assertTrue(ifLoad);
    }
}