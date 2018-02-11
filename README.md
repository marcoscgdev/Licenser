# Licenser  [![API](https://img.shields.io/badge/API-14%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=14) 
An android library to display the licenses of your application libraries in a easy way.

---

## Releases:

#### Current release: 1.0.0.

You can see all the library releases [here](https://github.com/marcoscgdev/Licenser/releases).

---

## Demo:

You can download the **sample apk** [here](https://github.com/marcoscgdev/Licenser/releases/download/1.0.0/app-debug.apk).

<img src="https://raw.githubusercontent.com/marcoscgdev/Licenser/master/device-2018-02-11-161003.png" width="350">

---

## Usage:

#### Step: 1 - adding the library via Gradle

First of all add this to your root *build.gradle* file:

```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Now add the dependency to your app *build.gradle* file:

```groovy
compile 'com.github.marcoscgdev:Licenser:1.0.0'
```

#### Step: 2 - creating a dialog

```java
new LicenserDialog(this)
        .setTitle("Licenses")
        .setLibrary(new Library("Android Support Libraries",
                "https://developer.android.com/topic/libraries/support-library/index.html",
                License.APACHE))
        .setLibrary(new Library("Example Library",
                "https://github.com/marcoscgdev",
                License.APACHE))
        .setLibrary(new Library("Licenser",
                "https://github.com/marcoscgdev/Licenser",
                License.MIT))
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // TODO: 11/02/2018
            }
        })
        .show();
```

Library structure:

```java
Library lib1 = new Library(String title, String url, int licenseType);
```

LICENSE TYPES (At this moment):

```java
 - License.APACHE
 - License.MIT
 - License.GNU
 ```
 
 ```java
Library lib1 = new Library("Library name", "https://github.com/marcoscgdev", License.APACHE);
 ```

If you don't want a dialog, you can use the Licenser class:

```java
Licenser licenser = new Licenser()
        .setLibrary(new Library("Android Support Libraries",
                "https://developer.android.com/topic/libraries/support-library/index.html",
                License.APACHE))
        .setLibrary(new Library("Example Library",
                "https://github.com/marcoscgdev",
                License.APACHE))
        .setLibrary(new Library("Licenser",
                "https://github.com/marcoscgdev/Licenser",
                License.MIT));
```

And show it in a webview:

```java
WebView webView = (WebView) findViewById(R.id.webView);
webView.loadData(licenser.getHTMLContent(), "text/html; charset=UTF-8", null);
```

#### Extra functions

You can access this functions with both classes

```java
String licenses = licenserDialog.getHTMLContent(); // HTML content
List<Library> apacheLibraries = licenser.getApacheLibraries();
List<Library> mitLibraries = licenser.getMitLibraries();
List<Library> gnuLibraries = licenser.getGnuLibraries();
```
