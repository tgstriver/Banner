package com.nepxion.banner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NepxionBanner {

    public static void show(LogoBanner logoBanner, Description... descriptionList) {
        String bannerShown = System.getProperty(BannerConstant.BANNER_SHOWN, "true");
        if (!Boolean.parseBoolean(bannerShown)) {
            return;
        }

        System.out.println();
        String bannerShownAnsiMode = System.getProperty(BannerConstant.BANNER_SHOWN_ANSI_MODE, "false");
        if (Boolean.parseBoolean(bannerShownAnsiMode)) {
            System.out.println(logoBanner.getBanner());
        } else {
            System.out.println(logoBanner.getPlainBanner());
        }

        List<Description> descriptions = new ArrayList<>();
        Collections.addAll(descriptions, descriptionList);
        descriptions.add(new Description(BannerConstant.SITE + ":", BannerConstant.NEPXION_SITE, 0, 1));

        DescriptionBanner descriptionBanner = new DescriptionBanner();
        System.out.println(descriptionBanner.getBanner(descriptions));
    }
}