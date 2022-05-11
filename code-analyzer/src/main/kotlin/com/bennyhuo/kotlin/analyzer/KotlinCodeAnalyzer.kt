package com.bennyhuo.kotlin.analyzer

import com.bennyhuo.kotlin.analyzer.core.AnalysisFacade
import com.bennyhuo.kotlin.analyzer.core.toSettings
import io.github.detekt.tooling.dsl.ProcessingSpecBuilder
import io.gitlab.arturbosch.detekt.core.ProcessingSettings
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.io.File

/**
 * Created by benny.
 */
class KotlinCodeAnalyzer(private val options: Options) {
    fun analyze(): AnalyzeResult {
        return AnalysisFacade(options.toProcessingSpec().toSettings()).run()
    }
}