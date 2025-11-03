import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

// Due that gradle does not allows access to the libs.version.toml accessor, we need to due this
// workaround to access them. See: https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
val Project.projectLibs: LibrariesForLibs
    get() = the<LibrariesForLibs>()