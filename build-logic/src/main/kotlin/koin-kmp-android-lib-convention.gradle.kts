// Android requires additionally to either add the android application gradle plugin, or library plugin
// Due that we cannot apply both at the same time, we need to have a separate plugin for each
// Use this on the module, where you build an android library
plugins {
    // Applies our main kmp koin convention plugin
    id("koin-kmp-convention")
    id("com.android.library")
}

kotlin {
    androidTarget()
}
dependencies {
    add("kspAndroid", projectLibs.koin.ksp.compiler)
}