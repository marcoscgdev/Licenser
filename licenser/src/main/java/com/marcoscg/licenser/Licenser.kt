package com.marcoscg.licenser

import android.content.Context
import android.graphics.Color
import com.marcoscg.licenser.Utils.colorHex
import com.marcoscg.licenser.Utils.darkenColor
import com.marcoscg.licenser.Utils.getThemeColor
import com.marcoscg.licenser.Utils.isColorLight
import com.marcoscg.licenser.Utils.lightenColor
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by @MarcosCGdev on 11/02/2018.
 */
open class Licenser {

    private val libraries = HashMap<License, MutableList<Library>>()
    private val stringBuilder = StringBuilder()
    private var noticeTitle = "Notices for files:"
    private var backgroundColor = -1

    open fun setLibrary(library: Library): Licenser? {
        if (libraries.containsKey(library.license).not()) {
            libraries[library.license] = ArrayList()
        }

        libraries[library.license]?.add(library)

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

    val htmlContent: String
        get() {
            startDocument()
            addLicenses()
            endDocument()
            return stringBuilder.toString()
        }

    val apache2Libraries: MutableList<Library>?
        get() = libraries[License.APACHE2]

    val mitLibraries: MutableList<Library>?
        get() = libraries[License.MIT]

    val gnu3Libraries: MutableList<Library>?
        get() = libraries[License.GNU3]

    val creativeCommonsLibraries: MutableList<Library>?
        get() = libraries[License.CREATIVE_COMMONS]

    val iscLibraries: MutableList<Library>?
        get() = libraries[License.ISC]

    val ntpLibraries: MutableList<Library>?
        get() = libraries[License.NTP]

    val apache1Libraries: MutableList<Library>?
        get() = libraries[License.APACHE1]

    val apache1_1Libraries: MutableList<Library>?
        get() = libraries[License.APACHE1_1]

    val bsd3Libraries: MutableList<Library>?
        get() = libraries[License.BSD3]

    val bsd4Libraries: MutableList<Library>?
        get() = libraries[License.BSD4]

    val freeBsdLibraries: MutableList<Library>?
        get() = libraries[License.FREEBSD]

    val bslLibraries: MutableList<Library>?
        get() = libraries[License.BSL]

    val gnu2Libraries: MutableList<Library>?
        get() = libraries[License.GNU2]

    val lgpl2_1Libraries: MutableList<Library>?
        get() = libraries[License.LGPL2_1]

    val lgpl3Libraries: MutableList<Library>?
        get() = libraries[License.LGPL3]

    val ofl1_1Libraries: MutableList<Library>?
        get() = libraries[License.OFL1_1]

    val mpl1Libraries: MutableList<Library>?
        get() = libraries[License.MPL1]

    val mpl1_1Libraries: MutableList<Library>?
        get() = libraries[License.MPL1_1]

    val mpl2Libraries: MutableList<Library>?
        get() = libraries[License.MPL2]

    protected fun getDialogHTMLContent(context: Context?): String {
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

    private fun addLicenses() {
        for (lib in libraries.toSortedMap(compareBy { it.code })) {
            addLicenseLibraries(lib.key, lib.value)
        }
    }

    private fun addLicenseLibraries(license: License, libraryList: List<Library>?) {
        if (libraryList != null && libraryList.isNotEmpty()) {
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
            stringBuilder.append("<pre>").append(license.htmlContent).append("</pre>")
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
}