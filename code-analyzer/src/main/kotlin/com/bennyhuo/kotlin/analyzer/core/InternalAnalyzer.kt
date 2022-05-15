package com.bennyhuo.kotlin.analyzer.core

import com.bennyhuo.kotlin.analyzer.AnalyzeResult
import io.gitlab.arturbosch.detekt.core.KtTreeCompiler
import io.gitlab.arturbosch.detekt.core.ProcessingSettings
import org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.cli.jvm.compiler.NoScopeRecordCliBindingTrace
import org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM
import org.jetbrains.kotlin.config.languageVersionSettings
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.lazy.declarations.FileBasedDeclarationProviderFactory
import org.jetbrains.kotlin.types.ErrorUtils

internal class InternalAnalyzer(
    private val settings: ProcessingSettings
) {

    fun run(): AnalyzeResult {
        val compiler = KtTreeCompiler(settings, settings.spec.projectSpec)
        val filesToAnalyze = settings.spec.projectSpec.inputPaths.flatMap(compiler::compile)
        return doAnalyze(settings.environment, filesToAnalyze)
    }

    private fun doAnalyze(
        environment: KotlinCoreEnvironment,
        files: List<KtFile>,
    ): AnalyzeResult {
        val analyzer = AnalyzerWithCompilerReport(
            CodeAnalyzerMessageCollector(minSeverity = CompilerMessageSeverity.ERROR),
            environment.configuration.languageVersionSettings
        )
        analyzer.analyzeAndReport(files) {
            TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration(
                environment.project,
                files,
                NoScopeRecordCliBindingTrace(),
                environment.configuration,
                environment::createPackagePartProvider,
                ::FileBasedDeclarationProviderFactory
            )
        }

        return AnalyzeResult(files, analyzer.analysisResult.bindingContext, analyzer.analysisResult.moduleDescriptor)
    }

}