package com.nepxion.banner;

import com.taobao.text.Color;
import com.taobao.text.Decoration;
import com.taobao.text.ui.Element;
import com.taobao.text.ui.LabelElement;
import com.taobao.text.ui.TableElement;
import com.taobao.text.util.RenderUtil;

public class LogoBanner extends AbstractBanner {

    /**
     * Logo中的元素总个数
     */
    private final int elementCount;

    /**
     * Logo中单个元素占用的行数
     */
    private final int elementLineCount;

    /**
     * Logo元素的颜色数组，每个元素对应一个设置的颜色，数组的长度为元素的总个数
     */
    private final Color[] elementColors;

    /**
     * Logo字体是否发亮
     */
    private final boolean boldOff;

    public LogoBanner(Class<?> resourceClass, String resourceLocation,
                      String defaultBanner, int elementCount,
                      int elementLineCount, Color[] elementColors,
                      boolean boldOff) {
        super(resourceClass, resourceLocation, defaultBanner);

        this.elementCount = elementCount;
        this.elementLineCount = elementLineCount;
        this.elementColors = elementColors;
        this.boldOff = boldOff;

        super.initialize();
    }

    @Override
    protected String generateBanner(String bannerText) {
        if (bannerText != null) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] elementTexts = new String[elementCount]; // Logo元素的总个数
            int i = 0, j = 0;

            for (String line : bannerText.split("\n")) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
                if (i++ == elementLineCount - 1) { // Logo元素的单个占行数减1
                    elementTexts[j++] = stringBuilder.toString();
                    i = 0;
                    stringBuilder.setLength(0);
                }
            }

            LabelElement[] labelElements = new LabelElement[elementCount];
            for (int k = 0; k < elementCount; k++) {
                if (boldOff) {
                    labelElements[k] = Element.label(elementTexts[k]).style(Decoration.bold_off.fg(elementColors[k]));
                } else {
                    labelElements[k] = Element.label(elementTexts[k]).style(Decoration.bold.fg(elementColors[k]));
                }
            }

            TableElement tableElement = new TableElement();
            tableElement.row(labelElements);

            return RenderUtil.render(tableElement);
        } else {
            return defaultBanner;
        }
    }
}