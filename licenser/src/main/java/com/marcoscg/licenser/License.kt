package com.marcoscg.licenser

import com.marcoscg.licenser.LicenseContent.apache1License
import com.marcoscg.licenser.LicenseContent.apache1_1License
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
import com.marcoscg.licenser.LicenseContent.mPL1License
import com.marcoscg.licenser.LicenseContent.mPL1_1License
import com.marcoscg.licenser.LicenseContent.mPL2License
import com.marcoscg.licenser.LicenseContent.nTPLicense
import com.marcoscg.licenser.LicenseContent.oFL1_1License

data class License(val code: String, var htmlContent: String) {

    companion object {
        val APACHE1 = License("APACHE1", apache1License)
        val APACHE1_1 = License("APACHE1_1", apache1_1License)
        val APACHE2 = License("APACHE2", apache2License)
        val BSD3 = License("BSD3", bSD3License)
        val BSD4 = License("BSD4", bSD4License)
        val BSL = License("BSL", bSLLicense)
        val CREATIVE_COMMONS = License("CREATIVE_COMMONS", creativeCommonsLicense)
        val FREEBSD = License("FREEBSD", freeBSDLicense)
        val GNU2 = License("GNU2", gNU2License)
        val GNU3 = License("GNU3", gNU3License)
        val ISC = License("ISC", iSCLicense)
        val LGPL2_1 = License("LGPL2_1", lGPL2_1License)
        val LGPL3 = License("LGPL3", lGPL3License)
        val MIT = License("MIT", mITLicense)
        val MPL1 = License("MPL1", mPL1License)
        val MPL1_1 = License("MPL1_1", mPL1_1License)
        val MPL2 = License("MPL2", mPL2License)
        val NTP = License("NTP", nTPLicense)
        val OFL1_1 = License("OFL1_1", oFL1_1License)
    }

    override fun toString(): String {
        return code
    }
}