package com.bennyhuo.kotlin.analyzer.core

import io.gitlab.arturbosch.detekt.core.ProcessingSettings
import io.gitlab.arturbosch.detekt.core.tooling.contentToKtFile
import io.gitlab.arturbosch.detekt.core.tooling.pathToKtFile
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import java.nio.file.Path
import java.nio.file.Paths

internal class AnalysisFacade(
    private val settings: ProcessingSettings
) {

    fun run() = runAnalysis {
        DefaultLifecycle(settings, inputPathsToKtFiles)
    }

    fun run(path: Path) = runAnalysis { DefaultLifecycle(settings, pathToKtFile(path)) }

    fun run(sourceCode: String, filename: String) =
        runAnalysis { DefaultLifecycle(settings, contentToKtFile(sourceCode, Paths.get(filename))) }

    fun run(files: Collection<KtFile>, bindingContext: BindingContext) =
        runAnalysis {
            DefaultLifecycle(
                settings,
                parsingStrategy = { files.toList() },
                bindingProvider = { bindingContext }
            )
        }

    internal fun runAnalysis(createLifecycle: () -> DefaultLifecycle) = createLifecycle().analyze()

}
