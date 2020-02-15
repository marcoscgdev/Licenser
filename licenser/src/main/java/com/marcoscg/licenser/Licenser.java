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

    private List<Library> apache2Libraries;
    private List<Library> mitLibraries;
    private List<Library> gnu3Libraries;
    private List<Library> creativeCommonsLibraries;
    private List<Library> iscLibraries;
    private List<Library> ntpLibraries;
    private List<Library> apache1Libraries;
    private List<Library> apache1_1Libraries;
    private List<Library> bsd3Libraries;
    private List<Library> bsd4Libraries;
    private List<Library> freeBsdLibraries;
    private List<Library> bslLibraries;
    private List<Library> gnu2Libraries;
    private List<Library> gnu2_1Libraries;

    private StringBuilder stringBuilder;
    private String noticeTitle = "Notices for files:";
    private int backgroundColor = -1;
    
    public Licenser() {
        apache2Libraries = new ArrayList<>();
        mitLibraries = new ArrayList<>();
        gnu3Libraries = new ArrayList<>();
        creativeCommonsLibraries = new ArrayList<>();
        iscLibraries = new ArrayList<>();
        ntpLibraries = new ArrayList<>();
        apache1Libraries = new ArrayList<>();
        apache1_1Libraries = new ArrayList<>();
        bsd3Libraries = new ArrayList<>();
        bsd4Libraries = new ArrayList<>();
        freeBsdLibraries = new ArrayList<>();
        bslLibraries = new ArrayList<>();
        gnu2Libraries = new ArrayList<>();
        gnu2_1Libraries = new ArrayList<>();

        stringBuilder = new StringBuilder();
    }

    public Licenser setLibrary(Library library) {
        switch (library.getLicense()) {
            case License.APACHE:
            case License.APACHE2:
                apache2Libraries.add(library);
                break;
            case License.MIT:
                mitLibraries.add(library);
                break;
            case License.GNU:
            case License.GNU3:
                gnu3Libraries.add(library);
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
            case License.APACHE1:
                apache1Libraries.add(library);
                break;
            case License.APACHE1_1:
                apache1_1Libraries.add(library);
                break;
            case License.GNU2:
                gnu2Libraries.add(library);
                break;
            case License.GNU2_1:
                gnu2_1Libraries.add(library);
                break;
            case License.BSD3:
                bsd3Libraries.add(library);
                break;
            case License.BSD4:
                bsd4Libraries.add(library);
                break;
            case License.BSL:
                bslLibraries.add(library);
                break;
            case License.FREEBSD:
                freeBsdLibraries.add(library);
                break;
        }

        return this;
    }

    public Licenser setCustomNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
        return this;
    }

    public Licenser setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public String getHTMLContent() {
        startDocument();
        addLicenses();
        endDocument();

        return stringBuilder.toString();
    }

    String getDialogHTMLContent(Context context) {
        if (backgroundColor==-1)
            backgroundColor = Utils.getThemeColor(context, R.attr.colorBackgroundFloating);

        int preColor = Utils.darkenColor(backgroundColor);
        int textColor = Color.BLACK;

        if (!Utils.isColorLight(backgroundColor)) {
            preColor = Utils.lightenColor(backgroundColor);
            textColor = Color.WHITE;
        }

        startDocument(backgroundColor, textColor, preColor);
        addLicenses();
        endDocument();

        return stringBuilder.toString();
    }

    /**
     *
     * @deprecated Use {@link Licenser#getApache2Libraries()} instead.
     */
    @Deprecated
    public List<Library> getApacheLibraries() {
        return apache2Libraries;
    }

    public List<Library> getApache2Libraries() {
        return apache2Libraries;
    }

    public List<Library> getMitLibraries() {
        return mitLibraries;
    }

    /**
     *
     * @deprecated Use {@link Licenser#getGnu3Libraries()} instead.
     */
    @Deprecated
    public List<Library> getGnuLibraries() {
        return gnu3Libraries;
    }

    public List<Library> getGnu3Libraries() {
        return gnu3Libraries;
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

    public List<Library> getApache1Libraries() {
        return apache1Libraries;
    }

    public List<Library> getApache1_1Libraries() {
        return apache1_1Libraries;
    }

    public List<Library> getBsd3Libraries() {
        return bsd3Libraries;
    }

    public List<Library> getBsd4Libraries() {
        return bsd4Libraries;
    }

    public List<Library> getFreeBsdLibraries() {
        return freeBsdLibraries;
    }

    public List<Library> getBslLibraries() {
        return bslLibraries;
    }

    public List<Library> getGnu2Libraries() {
        return gnu2Libraries;
    }

    public List<Library> getGnu2_1Libraries() {
        return gnu2_1Libraries;
    }

    private void addLicenses() {
        addLicenseLibraries(apache1Libraries, License.getApache1License());
        addLicenseLibraries(apache1_1Libraries, License.getApache11License());
        addLicenseLibraries(apache2Libraries, License.getApache2License());
        addLicenseLibraries(bsd3Libraries, License.getBSD3License());
        addLicenseLibraries(bsd4Libraries, License.getBSD4License());
        addLicenseLibraries(bslLibraries, License.getBSLLicense());
        addLicenseLibraries(creativeCommonsLibraries, License.getCreativeCommonsLicense());
        addLicenseLibraries(freeBsdLibraries, License.getFreeBSDLicense());
        addLicenseLibraries(gnu2Libraries, License.getGNU2License());
        addLicenseLibraries(gnu2_1Libraries, License.getGNU2_1License());
        addLicenseLibraries(gnu3Libraries, License.getGNU3License());
        addLicenseLibraries(iscLibraries, License.getISCLicense());
        addLicenseLibraries(mitLibraries, License.getMITLicense());
        addLicenseLibraries(ntpLibraries, License.getNTPLicense());
    }

    private void addLicenseLibraries(List<Library> libraryList, String licenseHtml) {
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

    private void startDocument() {
        startDocument(Color.WHITE, Color.BLACK, Color.parseColor("#F1F1F1"));
    }

    private void startDocument(int backgroundColor, int textColor, int preColor) {
        stringBuilder.setLength(0);
        stringBuilder.append("<html><head>");
        stringBuilder.append("<meta charset=\"utf-8\">\n");
        stringBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\">\n");
        stringBuilder.append("<style>body{font-family:sans-serif;margin:0;padding-left:8px;padding-right:8px;background-color:")
                .append(Utils.colorHex(backgroundColor)).append(";color:").append(Utils.colorHex(textColor)).append(";}").append("a{color:")
                .append(Utils.colorHex(textColor)).append(";}li{margin:0 0 4px;}" +
                "pre{padding:1em;white-space:pre-wrap;margin:0;background-color:").append(Utils.colorHex(preColor)).append(";color:")
                .append(Utils.colorHex(textColor)).append(";}h3{margin-left:16px;}ul{margin-top:-12px;}</style>\n");
        stringBuilder.append("</head><body>");
    }

    private void endDocument() {
        stringBuilder.append("</body></html>");
    }
    
}
