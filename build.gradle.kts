buildscript {
    //ANDROID LIBS
    val androidSupportVersion = "24.1.1"
    val retrofit2Version = "2.0.2"
    val okhttp3Version = "3.2.0"
    val glideVersion = "3.7.0"
    val daggerVersion = "2.5"
    val timberVersion = "4.1.2"
    val rxjavaVersion = "1.1.6"
    val rxandroidVersion = "1.1.0"
    val leakCanaryVersion = "1.3.1"
    val rxPreferencesVersion = "1.0.2"
    val realmVersion = "1.1.1"

    //TESTING
    val junitVersion = "4.12"
    val mockitoVersion = "2.0.52-beta"
    val robolectricVersion = "3.1.2"
    val espressoVersion = "2.2.2"
    val espressoRunnerVersion = "0.5"
    val uiautomatorVersion = "2.1.2"
    val hamcrestVersion = "1.3"
    val unmockVersion = "0.5.0"
    val spekVersion = "1.0.89"
    val junitPlatformVersion = "1.0.0-M2"
    val powermockVersion = "1.6.5"

    //ANKO
    val ankoVersion = "0.9"

    extra["android_supportV4"] = "com.android.support:support-v4:$androidSupportVersion"
    extra["android_supportV13"] = "com.android.support:support-v13:$androidSupportVersion"
    extra["android_appcompat"] = "com.android.support:appcompat-v7:$androidSupportVersion"
    extra["design_support"] = "com.android.support:design:$androidSupportVersion"
    extra["support_annotations"] = "com.android.support:support-annotations:$androidSupportVersion"
    extra["cardView"] = "com.android.support:cardview-v7:$androidSupportVersion"
    extra["recyclerView"] = "com.android.support:recyclerview-v7:$androidSupportVersion"
    extra["retrofit2"] = "com.squareup.retrofit2:retrofit:$retrofit2Version"
    extra["retrofit2_gson"] = "com.squareup.retrofit2:converter-gson:$retrofit2Version"
    extra["retrofit2_rx"] = "com.squareup.retrofit2:adapter-rxjava:$retrofit2Version"
    extra["glide"] = "com.github.bumptech.glide:glide:$glideVersion"
    extra["dagger"] = "com.google.dagger:dagger:$daggerVersion"
    extra["dagger_compiler"] = "com.google.dagger:dagger-compiler:$daggerVersion"
    extra["okhttp3"] = "com.squareup.okhttp3:okhttp:$okhttp3Version"
    extra["okhttp3_logger"] = "com.squareup.okhttp3:logging-interceptor:$okhttp3Version"
    extra["timber"] = "com.jakewharton.timber:timber:$timberVersion"
    extra["rxjava"] = "io.reactivex:rxjava:$rxjavaVersion"
    extra["rxandroid"] = "io.reactivex:rxandroid:$rxandroidVersion"
    extra["leak_canary"] = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
    extra["leak_canary_release"] = "com.squareup.leakcanary:leakcanary-android-no-op:$leakCanaryVersion"
    extra["rx_preferences"] = "com.f2prateek.rx.preferences:rx-preferences:$rxPreferencesVersion"

    // TESTING
    extra["junit"] = "junit:junit:$junitVersion"
    extra["mockito"] = "org.mockito:mockito-core:$mockitoVersion"
    extra["robolectric"] = "org.robolectric:robolectric:$robolectricVersion"
    extra["espresso"] = "com.android.support.test.espresso:espresso-core:$espressoVersion"
    extra["espresso_runner"] = "com.android.support.test:runner:$espressoRunnerVersion"
    extra["espresso_intents"] = "com.android.support.test.espresso:espresso-intents:$espressoVersion"
    extra["ui_automator"] = "com.android.support.test.uiautomator:uiautomator-v18:$uiautomatorVersion"
    extra["hamcrest"] = "org.hamcrest:hamcrest-all:$hamcrestVersion"
    extra["spek"] = "org.jetbrains.spek:spek-api:$spekVersion"
    extra["spek_runner"] = "org.jetbrains.spek:spek-junit-platform-engine:$spekVersion"
    extra["junit_platform"] = "org.junit.platform:junit-platform-runner:$junitPlatformVersion"
    extra["powermock_api"] = "org.powermock:powermock-api-mockito:$powermockVersion"
    extra["powermock_agent"] = "org.powermock:powermock-module-junit4-rule-agent:$powermockVersion"
    extra["powermock_rule"] = "org.powermock:powermock-module-junit4-rule:$powermockVersion"
    extra["powermock_junit"] = "org.powermock:powermock-module-junit4:$powermockVersion"

    //ANKO
    extra["anko"] = "org.jetbrains.anko:anko-sdk15:$ankoVersion"
    extra["anko_supportV4"] = "org.jetbrains.anko:anko-support-v4:$ankoVersion"
    extra["anko_appcompat"] = "org.jetbrains.anko:anko-appcompat-v7:$ankoVersion"
    extra["anko_design"] = "org.jetbrains.anko:anko-design:$ankoVersion"
    extra["anko_recyclerView"] = "org.jetbrains.anko:anko-recyclerview-v7:$ankoVersion"

    //Temporary hack until Android plugin has proper support
    System.setProperty("com.android.build.gradle.overrideVersionCheck", "true")

    repositories {
        jcenter()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://maven.fabric.io/public") }
        maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { setUrl("http://repository.jetbrains.com/all") }
        gradleScriptKotlin()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:2.2.0")
        classpath("de.mobilej.unmock:UnMockPlugin:$unmockVersion")
        classpath("io.fabric.tools:gradle:1.15.2")
        classpath(kotlinModule("gradle-plugin"))
        classpath(kotlinModule("android-extensions"))
    }
}

repositories {
    jcenter()
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
    maven { setUrl("https://maven.fabric.io/public") }
    maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
    maven { setUrl("http://repository.jetbrains.com/all") }
    gradleScriptKotlin()
}

task("clean") {
    delete(project.buildDir)
}