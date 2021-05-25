package org.nekostudio.util;

import cn.hutool.core.util.DesensitizedUtil;
import org.nekostudio.enums.SensitiveType;

/**
 * @author neko
 */
public class SensitivenUtil {

    public static String handler(SensitiveType type,String source) {
        return DesensitizedUtil.email(source);
    }
}
