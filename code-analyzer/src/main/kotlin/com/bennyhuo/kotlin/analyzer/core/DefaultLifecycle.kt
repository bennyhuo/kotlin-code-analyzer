package com.bennyhuo.kotlin.analyzer.core

import com.bennyhuo.kotlin.analyzer.AnalyzeResult
import io.gitlab.arturbosch.detekt.core.ProcessingSettings
import io.gitlab.arturbosch.detekt.core.tooling.ParsingStrategy
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext

internal class DefaultLifecycle(
    val settings: ProcessingSettings,
    val parsingStrategy: ParsingStrategy,
    val bindingProvider: (files: List<KtFile>) -> BindingContext =
        { generateBindingContext(settings.environment, settings.classpath, it) }
) {
    fun analyze(): AnalyzeResult {
        val filesToAnalyze = parsingStrategy.invoke(settings)
        return AnalyzeResult(filesToAnalyze, bindingProvider.invoke(filesToAnalyze))
    }
}