package com.marcoscg.licenser

import android.content.Context
import android.graphics.Color
import com.marcoscg.licenser.LicenseContent.apache11License
import com.marcoscg.licenser.LicenseContent.apache1License
import com.marcoscg.licenser.LicenseContent.apache2License
import com.marcoscg.licenser.LicenseContent.bSD3License
import com.marcoscg.licenser.LicenseContent.bSD4License
import com.marcoscg.licenser.LicenseContent.bSLLicense
import com.marcoscg.licenser.LicenseContent.creativeCommonsLicense
import com.marcoscg.licenser.LicenseContent.freeBSDLicense
import com.marcoscg.licenser.LicenseContent.gNU2License
import com.marcoscg.licenser.LicenseContent.gNU3License
import com.marcoscg.licenser.LicenseContent.iSCLicense
import com.marcoscg.licenser.LicenseContent.lGPL2_1License
import com.marcoscg.licenser.LicenseContent.lGPL3License
import com.marcoscg.licenser.LicenseContent.mITLicense
import com.marcoscg.licenser.LicenseContent.nTPLicense
import com.marcoscg.licenser.Utils.colorHex
import com.marcoscg.licenser.Utils.darkenColor
import com.marcoscg.licenser.Utils.getThemeColor
import com.marcoscg.licenser.Utils.isColorLight
import com.marcoscg.licenser.Utils.lightenColor
import java.util.*

/**
 * Created by @MarcosCGdev on 11/02/2018.
 */
open class Licenser {

    private val apache2Libraries: MutableList<Library>
    private val mitLibraries: MutableList<Library>
    private val gnu3Libraries: MutableList<Library>
    private val creativeCommonsLibraries: MutableList<Library>
    private val iscLibraries: MutableList<Library>
    private val ntpLibraries: MutableList<Library>
    private val apache1Libraries: MutableList<Library>
    private val apache1_1Libraries: MutableList<Library>
    private val bsd3Libraries: MutableList<Library>
    private val bsd4Libraries: MutableList<Library>
    private val freeBsdLibraries: MutableList<Library>
    private val bslLibraries: MutableList<Library>
    private val gnu2Libraries: MutableList<Library>
    private val lgpl2_1Libraries: MutableList<Library>
    private val lgpl3Libraries: MutableList<Library>
    private val stringBuilder: StringBuilder
    private var noticeTitle = "Notices for files:"
    private var backgroundColor = -1

    open fun setLibrary(library: Library): Licenser? {
        when (library.license) {
            License.APACHE, License.APACHE2 -> apache2Libraries.add(library)
            License.MIT -> mitLibraries.add(library)
            License.GNU, License.GNU3 -> gnu3Libraries.add(library)
            License.CREATIVE_COMMONS -> creativeCommonsLibraries.add(library)
            License.ISC -> iscLibraries.add(library)
            License.NTP -> ntpLibraries.add(library)
            License.APACHE1 -> apache1Libraries.add(library)
            License.APACHE1_1 -> apache1_1Libraries.add(library)
            License.GNU2 -> gnu2Libraries.add(library)
            License.GNU2_1, License.LGPL2_1 -> lgpl2_1Libraries.add(library)
            License.BSD3 -> bsd3Libraries.add(library)
            License.BSD4 -> bsd4Libraries.add(library)
            License.BSL -> bslLibraries.add(library)
            License.FREEBSD -> freeBsdLibraries.add(library)
            License.LGPL3 -> lgpl3Libraries.add(library)
        }

        return this
    }

    open fun setCustomNoticeTitle(noticeTitle: String): Licenser? {
        this.noticeTitle = noticeTitle
        return this
    }

    open fun setBackgroundColor(backgroundColor: Int): Licenser? {
        this.backgroundColor = backgroundColor
        return this
    }

    val hTMLContent: String
        get() {
            startDocument()
            addLicenses()
            endDocument()
            return stringBuilder.toString()
        }

    fun getDialogHTMLContent(context: Context?): String {
        if (backgroundColor == -1) backgroundColor = getThemeColor(context!!, R.attr.colorBackgroundFloating)
        var preColor = darkenColor(backgroundColor)
        var textColor = Color.BLACK
        if (!isColorLight(backgroundColor)) {
            preColor = lightenColor(backgroundColor)
            textColor = Color.WHITE
        }
        startDocument(backgroundColor, textColor, preColor)
        addLicenses()
        endDocument()
        return stringBuilder.toString()
    }

    @get:Deprecated("Use {@link Licenser#getApache2Libraries()} instead.")
    val apacheLibraries: List<Library>
        get() = apache2Libraries

    fun getApache2Libraries(): List<Library> {
        return apache2Libraries
    }

    fun getMitLibraries(): List<Library> {
        return mitLibraries
    }

    @get:Deprecated("Use {@link Licenser#getGnu3Libraries()} instead.")
    val gnuLibraries: List<Library>
        get() = gnu3Libraries

    fun getGnu3Libraries(): List<Library> {
        return gnu3Libraries
    }

    fun getCreativeCommonsLibraries(): List<Library> {
        return creativeCommonsLibraries
    }

    fun getIscLibraries(): List<Library> {
        return iscLibraries
    }

    fun getNtpLibraries(): List<Library> {
        return ntpLibraries
    }

    fun getApache1Libraries(): List<Library> {
        return apache1Libraries
    }

    fun getApache1_1Libraries(): List<Library> {
        return apache1_1Libraries
    }

    fun getBsd3Libraries(): List<Library> {
        return bsd3Libraries
    }

    fun getBsd4Libraries(): List<Library> {
        return bsd4Libraries
    }

    fun getFreeBsdLibraries(): List<Library> {
        return freeBsdLibraries
    }

    fun getBslLibraries(): List<Library> {
        return bslLibraries
    }

    fun getGnu2Libraries(): List<Library> {
        return gnu2Libraries
    }

    fun getLgpl2_1Libraries(): List<Library> {
        return lgpl2_1Libraries
    }

    fun getLgpl3Libraries(): List<Library> {
        return lgpl3Libraries
    }

    private fun addLicenses() {
        addLicenseLibraries(apache1Libraries, apache1License)
        addLicenseLibraries(apache1_1Libraries, apache11License)
        addLicenseLibraries(apache2Libraries, apache2License)
        addLicenseLibraries(bsd3Libraries, bSD3License)
        addLicenseLibraries(bsd4Libraries, bSD4License)
        addLicenseLibraries(bslLibraries, bSLLicense)
        addLicenseLibraries(creativeCommonsLibraries, creativeCommonsLicense)
        addLicenseLibraries(freeBsdLibraries, freeBSDLicense)
        addLicenseLibraries(gnu2Libraries, gNU2License)
        addLicenseLibraries(gnu3Libraries, gNU3License)
        addLicenseLibraries(iscLibraries, iSCLicense)
        addLicenseLibraries(lgpl2_1Libraries, lGPL2_1License)
        addLicenseLibraries(lgpl3Libraries, lGPL3License)
        addLicenseLibraries(mitLibraries, mITLicense)
        addLicenseLibraries(ntpLibraries, nTPLicense)
    }

    private fun addLicenseLibraries(libraryList: List<Library>, licenseHtml: String) {
        if (libraryList.isNotEmpty()) {
            stringBuilder.append("<h3>").append(noticeTitle).append("</h3>")
            stringBuilder.append("<ul>")

            for ((title, url) in libraryList) {
                if (url == null) {
                    stringBuilder.append("<li><b>").append(title).append("</b></li>")
                } else {
                    stringBuilder.append("<li><a href=\"").append(url).append("\"><b>")
                            .append(title).append("</b></a></li>")
                }
            }

            stringBuilder.append("</ul>")
            stringBuilder.append("<pre>").append(licenseHtml).append("</pre>")
        }
    }

    private fun startDocument(backgroundColor: Int = Color.WHITE, textColor: Int = Color.BLACK, preColor: Int = Color.parseColor("#F1F1F1")) {
        stringBuilder.setLength(0)
        stringBuilder.append("<html><head>")
        stringBuilder.append("<meta charset=\"utf-8\">\n")
        stringBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\">\n")
        stringBuilder.append("<style>body{font-family:sans-serif;margin:0;padding-left:8px;padding-right:8px;background-color:")
                .append(colorHex(backgroundColor)).append(";color:").append(colorHex(textColor)).append(";}").append("a{color:")
                .append(colorHex(textColor)).append(";}li{margin:0 0 4px;}" +
                        "pre{padding:1em;white-space:pre-wrap;margin:0;background-color:").append(colorHex(preColor)).append(";color:")
                .append(colorHex(textColor)).append(";}h3{margin-left:16px;}ul{margin-top:-12px;}</style>\n")
        stringBuilder.append("</head><body>")
    }

    private fun endDocument() {
        stringBuilder.append("</body></html>")
    }

    init {
        apache2Libraries = ArrayList()
        mitLibraries = ArrayList()
        gnu3Libraries = ArrayList()
        creativeCommonsLibraries = ArrayList()
        iscLibraries = ArrayList()
        ntpLibraries = ArrayList()
        apache1Libraries = ArrayList()
        apache1_1Libraries = ArrayList()
        bsd3Libraries = ArrayList()
        bsd4Libraries = ArrayList()
        freeBsdLibraries = ArrayList()
        bslLibraries = ArrayList()
        gnu2Libraries = ArrayList()
        lgpl2_1Libraries = ArrayList()
        lgpl3Libraries = ArrayList()
        stringBuilder = StringBuilder()
    }
}