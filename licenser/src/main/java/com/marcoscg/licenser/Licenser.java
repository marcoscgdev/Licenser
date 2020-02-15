package com.marcoscg.licenser;

import android.content.Context;
import android.graphics.Color;
import android.util.Base64;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @MarcosCGdev on 11/02/2018.
 */

public class Licenser {

    private List<Library> apacheLibraries;
    private List<Library> mitLibraries;
    private List<Library> gnuLibraries;
    private List<Library> creativeCommonsLibraries;
    private List<Library> iscLibraries;
    private List<Library> ntpLibraries;

    private StringBuilder stringBuilder;
    private String noticeTitle = "Notices for files:";
    private int backgroundColor = -1;
    
    public Licenser() {
        apacheLibraries = new ArrayList<>();
        mitLibraries = new ArrayList<>();
        gnuLibraries = new ArrayList<>();
        creativeCommonsLibraries = new ArrayList<>();
        iscLibraries = new ArrayList<>();
        ntpLibraries = new ArrayList<>();

        stringBuilder = new StringBuilder();

        stringBuilder.append("<html><head>");
        stringBuilder.append("<meta charset=\"utf-8\">\n");
        stringBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\">\n");
        stringBuilder.append("<style>body{font-family:sans-serif;margin:0;padding-left:8px;padding-right:8px;}li{margin:0 0 4px;}" +
                "pre{padding:1em;white-space:pre-wrap;margin: 0;}h3{margin-left: 16px;}ul{margin-top: -12px;}</style>\n");
        stringBuilder.append("</head><body>");
    }

    public Licenser setLibrary(Library library) {
        switch (library.getLicense()) {
            case License.APACHE:
                apacheLibraries.add(library);
                break;
            case License.MIT:
                mitLibraries.add(library);
                break;
            case License.GNU:
                gnuLibraries.add(library);
                break;
            case License.CREATIVE_COMMONS:
                creativeCommonsLibraries.add(library);
                break;
            case License.ISC:
                iscLibraries.add(library);
                break;
            case License.NTP:
                ntpLibraries.add(library);
                break;
        }

        return this;
    }

    public Licenser setCustomNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
        return this;
    }

    public String getHTMLContent() {
        prepare();
        stringBuilder.append("</body></html>");
        return Base64.encodeToString(stringBuilder.toString().getBytes(), Base64.NO_PADDING);
    }

    public Licenser setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    String getHTMLContent(Context context) {
        if (backgroundColor==-1)
            backgroundColor = Utils.getThemeColor(context, R.attr.colorBackgroundFloating);

        int preColor = Utils.darkenColor(backgroundColor);
        int textColor = Color.BLACK;

        if (!Utils.isColorLight(backgroundColor)) {
            preColor = Utils.lightenColor(backgroundColor);
            textColor = Color.WHITE;
        }

        stringBuilder.append("<style>body{background-color:").append(Utils.colorHex(backgroundColor)).append(";color:")
                .append(Utils.colorHex(textColor)).append(";}").append("a{color:").append(Utils.colorHex(textColor))
                .append(";}pre{background-color:").append(Utils.colorHex(preColor)).append(";color:")
                .append(Utils.colorHex(textColor)).append(";}</style>");

        return getHTMLContent();
    }

    public List<Library> getApacheLibraries() {
        return apacheLibraries;
    }

    public List<Library> getMitLibraries() {
        return mitLibraries;
    }

    public List<Library> getGnuLibraries() {
        return gnuLibraries;
    }

    public List<Library> getCreativeCommonsLibraries() {
        return creativeCommonsLibraries;
    }

    public List<Library> getIscLibraries() {
        return iscLibraries;
    }

    public List<Library> getNtpLibraries() {
        return ntpLibraries;
    }

    private void prepare() {
        prepareLicense(apacheLibraries, License.getApacheLicense());
        prepareLicense(creativeCommonsLibraries, License.getCreativeCommonsLicense());
        prepareLicense(gnuLibraries, License.getGNULicense());
        prepareLicense(iscLibraries, License.getISCLicense());
        prepareLicense(mitLibraries, License.getMITLicense());
        prepareLicense(ntpLibraries, License.getNTPLicense());
    }

    private void prepareLicense(List<Library> libraryList, String licenseHtml) {
        if (libraryList.size() > 0) {
            stringBuilder.append("<h3>").append(noticeTitle).append("</h3>");
            stringBuilder.append("<ul>");

            for (Library library : libraryList) {
                if (library.getUrl() == null) {
                    stringBuilder.append("<li><b>").append(library.getTitle()).append("</b></li>");
                } else {
                    stringBuilder.append("<li><a href=\"").append(library.getUrl()).append("\"><b>")
                            .append(library.getTitle()).append("</b></a></li>");
                }
            }

            stringBuilder.append("</ul>");
            stringBuilder.append("<pre>").append(licenseHtml).append("</pre>");
        }
    }
    
}
