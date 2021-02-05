package com.nepxion.banner;

public class VersionBanner extends AbstractBanner {

    public VersionBanner(Class<?> resourceClass, String resourceLocation, String defaultBanner) {
        super(resourceClass, resourceLocation, defaultBanner);
        super.initialize();
    }

    @Override
    protected String generateBanner(String bannerText) {
        if (bannerText == null) {
            String implementationVersion = resourceClass.getPackage().getImplementationVersion();
            if (implementationVersion != null) {
                return implementationVersion;
            } else {
                return defaultBanner;
            }
        } else {
            return bannerText;
        }
    }
}