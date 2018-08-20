package com.marcoscg.licenser;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @MarcosCGdev on 11/02/2018.
 */

public class Licenser {

    private List<Library> apacheLibraries;
    private List<Library> mitLibraries;
    private List<Library> gnuLibraries;

    private StringBuilder stringBuilder;
    private String noticeTitle = "Notices for files:";
    private int backgroundColor = -1;
    
    public Licenser() {

        apacheLibraries = new ArrayList<>();
        mitLibraries = new ArrayList<>();
        gnuLibraries = new ArrayList<>();
        stringBuilder = new StringBuilder();

        stringBuilder.append("<html><head>");
        stringBuilder.append("<meta charset=\"utf-8\">\n");
        stringBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\">\n");
        stringBuilder.append("<style>body{font-family:sans-serif;margin:0;padding-left:8px;padding-right:8px;}li{margin:0 0 4px;}" +
                "pre{padding:1em;white-space:pre-wrap;margin: 0;}h3{margin-left: 16px;}ul{margin-top: -12px;}</style>\n");
        stringBuilder.append("</head><body>");
    }

    public Licenser setLibrary(Library library) {
        if (library.getLicense()==License.APACHE)
            apacheLibraries.add(library);
        else if (library.getLicense()==License.MIT)
            mitLibraries.add(library);
        else if (library.getLicense()==License.GNU)
            gnuLibraries.add(library);
        return this;
    }

    public Licenser setCustomNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
        return this;
    }

    public String getHTMLContent() {
        prepare();
        stringBuilder.append("</body></html>");
        return stringBuilder.toString();
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

        stringBuilder.append("<style>body{background-color:"+Utils.colorHex(backgroundColor)+";color:"+Utils.colorHex(textColor)+";}" +
                "a{color:"+Utils.colorHex(textColor)+";}pre{background-color:"+Utils.colorHex(preColor)+";color:"+Utils.colorHex(textColor)+";}</style>");
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

    private void prepare() {
        if (apacheLibraries.size()>0) {
            stringBuilder.append("<h3>"+noticeTitle+"</h3>");
            stringBuilder.append("<ul>");
            for (Library library:apacheLibraries) {
                if (library.getUrl() == null)
                    stringBuilder.append("<li><b>"+library.getTitle()+"</b></li>");
                else
                    stringBuilder.append("<li><a href=\""+library.getUrl()+"\"><b>"+library.getTitle()+"</b></a></li>");
            }
            stringBuilder.append("</ul>");
            stringBuilder.append("<pre>"+License.getApacheLicense()+"</pre>");
        }

        if (mitLibraries.size()>0) {
            stringBuilder.append("<h3>"+noticeTitle+"</h3>");
            stringBuilder.append("<ul>");
            for (Library library:mitLibraries) {
                if (library.getUrl() == null)
                    stringBuilder.append("<li><b>"+library.getTitle()+"</b></li>");
                else
                    stringBuilder.append("<li><a href=\""+library.getUrl()+"\"><b>"+library.getTitle()+"</b></a></li>");
            }
            stringBuilder.append("</ul>");
            stringBuilder.append("<pre>"+License.getMITLicense()+"</pre>");
        }

        if (gnuLibraries.size()>0) {
            stringBuilder.append("<h3>"+noticeTitle+"</h3>");
            stringBuilder.append("<ul>");
            for (Library library:gnuLibraries) {
                if (library.getUrl() == null)
                    stringBuilder.append("<li><b>"+library.getTitle()+"</b></li>");
                else
                    stringBuilder.append("<li><a href=\""+library.getUrl()+"\"><b>"+library.getTitle()+"</b></a></li>");
            }
            stringBuilder.append("</ul>");
            stringBuilder.append("<pre>"+License.getGNULicense()+"</pre>");
        }
    }
    
}
