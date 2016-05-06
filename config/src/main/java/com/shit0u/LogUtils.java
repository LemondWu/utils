package com.shit0u;

import org.apache.commons.lang.StringUtils;

import javax.xml.crypto.Data;
import java.util.MissingFormatArgumentException;

/**
 * @author shit0u
 * @version 2.0
 *          Date: 16/5/3
 *          Time: 08:58
 * @description Log String 拼装类, 按照String Format( "content0 %s content1" ) 的格式拼装Log内容
 */
public class LogUtils {
    public static String format(String template, Object... args) {
        if (template == null || StringUtils.isBlank(template)) {
            throw new MissingFormatArgumentException("Template is invalid when build log text with LogUtils.");
        }

        if (args.length == 0) {
            throw new MissingFormatArgumentException("No args valid when build log text with LogUtils.");
        }

        template = template + " ";
        String[] templateArray = template.split("%s");

        if (templateArray.length > args.length + 1) {
            throw new MissingFormatArgumentException("Need more args, or too much specifier");
        }

        if (templateArray.length < args.length + 1) {
            throw new MissingFormatArgumentException("Too much args, or not enough specifier");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < templateArray.length; i++) {
            sb.append(templateArray[i]);
            if (i < templateArray.length - 1) {
                sb.append(args[i].toString());
            }
        }
        template = sb.toString();
        sb = null;
        templateArray = null;
        return template;
    }
}
