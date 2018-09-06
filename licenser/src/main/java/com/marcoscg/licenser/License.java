package com.marcoscg.licenser;

/**
 * Created by @MarcosCGdev on 11/02/2018.
 */

public class License {

    public static int APACHE = 1;
    public static int MIT = 2;
    public static int GNU = 3;
    public static int CREATIVE_COMMONS = 4;

    static String getApacheLicense() {
        return "Licensed under the Apache License, Version 2.0 (the \"License\").<br>" +
                "<br>" +
                "You may not use this file except in compliance with the License.<br>" +
                "You may obtain a copy of the License at<br>" +
                "<br>" +
                "http://www.apache.org/licenses/LICENSE-2.0<br>" +
                "<br>" +
                "Unless required by applicable law or agreed to in writing, software<br>" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,<br>" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br>" +
                "See the License for the specific language governing permissions and<br>" +
                "limitations under the License.";
    }

    static String getMITLicense() {
        return "Licensed under the MIT License (MIT).<br>" +
                "<br>" +
                "Permission is hereby granted, free of charge, to any person obtaining a copy<br>" +
                "of this software and associated documentation files (the \"Software\"), to deal<br>" +
                "in the Software without restriction, including without limitation the rights<br>" +
                "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell<br>" +
                "copies of the Software, and to permit persons to whom the Software is<br>" +
                "furnished to do so, subject to the following conditions:<br>" +
                "<br>" +
                "The above copyright notice and this permission notice shall be included in all<br>" +
                "copies or substantial portions of the Software.<br>" +
                "<br>" +
                "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR<br>" +
                "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,<br>" +
                "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE<br>" +
                "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER<br>" +
                "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,<br>" +
                "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE<br>" +
                "SOFTWARE.";
    }

    static String getGNULicense() {
        return "Licensed under the GNU General Public License, Version 3.0.<br>" +
                "<br>" +
                "This program is free software: you can redistribute it and/or modify<br>" +
                "it under the terms of the GNU General Public License as published by<br>" +
                "the Free Software Foundation, either version 3 of the License, or<br>" +
                "(at your option) any later version.<br>" +
                "<br>" +
                "This program is distributed in the hope that it will be useful,<br>" +
                "but WITHOUT ANY WARRANTY; without even the implied warranty of<br>" +
                "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>" +
                "GNU General Public License for more details.<br>" +
                "<br>" +
                "You should have received a copy of the GNU General Public License<br>" +
                "along with this program.  If not, see <http://www.gnu.org/licenses/>.";
    }

    static String getCreativeCommonsLicense() {
        return "Licensed under a Creative Commons 3.0 License.<br>" +
                "<br>" +
                "You must give appropriate credit, provide a link to the license, and indicate " +
                "if changes were made. You may do so in any reasonable manner, but not in any way " +
                "that suggests the licensor endorses you or your use.<br>" +
                "<br>" +
                "No additional restrictions — You may not apply legal terms or technological measures " +
                "that legally restrict others from doing anything the license permits." +
                "You do not have to comply with the license for elements of the material in" +
                " the public domain or where your use is permitted by an applicable exception or limitation.<br>" +
                "<br>" +
                "No warranties are given. The license may not give you all of the permissions necessary " +
                "for your intended use. For example, other rights such as publicity, privacy, or moral " +
                "rights may limit how you use the material." +
                "<br><br>For more info visit https://creativecommons.org/licenses/.";
    }

}
