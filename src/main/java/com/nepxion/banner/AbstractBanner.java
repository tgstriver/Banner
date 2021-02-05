package com.nepxion.banner;

import com.taobao.text.util.RenderUtil;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class AbstractBanner {

    // Resource类
    protected Class<?> resourceClass;

    // Resource位置
    protected String resourceLocation;

    // 默认旗标文本
    protected String defaultBanner;

    // 最终旗标文本
    protected String banner;

    protected AbstractBanner(Class<?> resourceClass, String resourceLocation, String defaultBanner) {
        this.resourceClass = resourceClass;
        this.resourceLocation = resourceLocation;
        this.defaultBanner = defaultBanner;
    }

    protected void initialize() {
        InputStream inputStream = null;
        String bannerText = null;

        try {
            if (resourceLocation != null) {
                inputStream = resourceClass.getResourceAsStream(resourceLocation);
                bannerText = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {

        } finally {
            banner = this.generateBanner(bannerText);
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

    public String getBanner() {
        return banner;
    }

    /**
     * 显示成非ansi模式
     *
     * @return
     */
    public String getPlainBanner() {
        return RenderUtil.ansiToPlainText(banner);
    }

    protected abstract String generateBanner(String bannerText);
}