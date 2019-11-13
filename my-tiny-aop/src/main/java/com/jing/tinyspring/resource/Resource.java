package com.jing.tinyspring.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 内部定位资源接口
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
