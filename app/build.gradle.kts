
import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.api.AndroidSourceSet
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.LintOptions
import com.android.builder.core.DefaultApiVersion
import com.android.builder.core.DefaultProductFlavor
import com.android.builder.model.ApiVersion
import de.mobilej.unmock.UnMockExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.script.lang.kotlin.*
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper
import java.io.File

apply {
    plugin<AppPlugin>()
    plugin<KotlinAndroidPluginWrapper>()
}

val versionMajor = 0
val versionMinor = 9
val versionPatch = 0
val versionBuild = 0

android {
    buildToolsVersion("23.0.3")
    compileSdkVersion(23)

    defaultConfigExtension {
        setMinSdkVersion(15)
        setTargetSdkVersion(23)

        applicationId = "com.example.kotlingradle"
        versionCode = buildVersionCode(versionMajor,versionMinor,versionPatch,versionBuild)
        versionName = buildVersionName(versionMajor,versionMinor,versionPatch)

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypesExtension {
        release {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
            isTestCoverageEnabled = true
        }
        debug {
            applicationIdSuffix = "debug"
            versionNameSuffix = " - DEBUG"
            isTestCoverageEnabled = true
        }
    }

    sourceSetsExtension {
        getByName("main").java.srcDirs.add(File("src/main/kotlin"))
        getByName("androidTest").java.srcDirs.add(File("src/androidTest/kotlin"))
        getByName("test").java.srcDirs.add(File("src/test/kotlin"))
    }

    lintOptionsExtension {
        isAbortOnError = true
    }
}

kapt {
    generateStubs = true
}

unmock {
    keep("android.webkut.URLUtil")
    keepStartingWith("android.util.")
}

dependencies {
    compile(extra["android_appcompat"])
    compile(extra["rxjava"])
    compile(extra["rxandroid"])
    compile(kotlinModule("stdlib"))

    testCompile(extra["junit"])
    testCompile(extra["kotlin"])
    testCompile(extra["kotlin_test"])
    testCompile(extra["robolectric"])
    testCompile(extra["leak_canary_relea"])
    testCompile(extra["spek"])
    testCompile(extra["spek_runner"])
    testCompile(extra["powermock_api"])
    testCompile(extra["powermock_agent"])
    testCompile(extra["powermock_rule"])
    testCompile(extra["powermock_junit"])
    testCompile(extra["junit_platform"])
    testCompile(extra["hamcrest"])
}


fun buildVersionName(versionMajor: Int, versionMinor: Int, versionPatch: Int) = "$versionMajor.$versionMinor.$versionPatch"

fun buildVersionCode(versionMajor: Int, versionMinor: Int, versionPatch: Int, versionBuild: Int) = versionMajor * 1000 + versionMinor * 100 + versionPatch * 10 + versionBuild

//Extension functions to allow comfortable references
fun Project.android(setup: AppExtension.() -> Unit) = the<AppExtension>().setup()

fun AppExtension.defaultConfigExtension(setup: DefaultProductFlavor.() -> Unit) = defaultConfig.setup()

fun AppExtension.buildTypesExtension(setup: NamedDomainObjectContainer<BuildType>.() -> Unit) = buildTypes { it.setup() }
fun NamedDomainObjectContainer<BuildType>.release(setup: BuildType.() -> Unit) = findByName("release").setup()
fun NamedDomainObjectContainer<BuildType>.debug(setup: BuildType.() -> Unit) = findByName("debug").setup()

fun AppExtension.lintOptionsExtension(setup: LintOptions.() -> Unit) = lintOptions { it.setup() }

fun AppExtension.sourceSetsExtension(setup: NamedDomainObjectContainer<AndroidSourceSet>.() -> Unit) = sourceSets { it.setup() }

fun Project.unmock(setup: UnMockExtension.() -> Unit) = the<UnMockExtension>().setup()

fun Project.kapt(setup: KaptExtension.() -> Unit) = the<KaptExtension>().setup()

fun DefaultProductFlavor.setMinSdkVersion(value: Int) = setMinSdkVersion(value.asApiVersion())

fun DefaultProductFlavor.setTargetSdkVersion(value: Int) = setTargetSdkVersion(value.asApiVersion())

fun Int.asApiVersion(): ApiVersion = DefaultApiVersion.create(this)