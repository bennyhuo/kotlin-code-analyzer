package com.bennyhuo.kotlin.analyzer

import io.github.detekt.tooling.api.spec.ProcessingSpec
import io.github.detekt.tooling.dsl.ProcessingSpecBuilder
import java.io.File
import java.nio.file.Path

/**
 * Created by benny.
 */
class Options {
    var jvmTarget: String = "1.8"
    var languageVersion: String? = null
    var classpath: String? = null
    var jdkHome: String? = null
    var inheritClassPath: Boolean = false

    var basePath: String? = null
    var inputPaths: Collection<String> = emptyList()
    var debug: Boolean = false

    internal fun toProcessingSpec(): ProcessingSpec {
        return ProcessingSpecBuilder().apply {
            project {
                inputPaths = this@Options.inputPaths.map {
                    File(it).toPath()
                }
                basePath = this@Options.basePath?.let { File(it).toPath() }
            }

            logging {
                debug = this@Options.debug
            }

            compiler {
                jvmTarget = this@Options.jvmTarget
                languageVersion = this@Options.languageVersion
                classpath = this@Options.classpath

                if (inheritClassPath) {
                    val currentClassPath = System.getProperty("java.class.path")
                    if (currentClassPath.isNotEmpty()) {
                        classpath = if (classpath.isNullOrBlank()) {
                            currentClassPath
                        } else {
                            "$currentClassPath${File.pathSeparator}$classpath"
                        }
                    }
                }
                val jdkHomePath = this@Options.jdkHome?.takeIf { it.isNotBlank() } ?: System.getProperty("java.home")
                jdkHome = Path.of(jdkHomePath)
            }
        }.build()
    }
}

fun buildOptions(init: Options.() -> Unit): Options {
    return Options().apply(init)
}
