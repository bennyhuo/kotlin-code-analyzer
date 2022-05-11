package com.bennyhuo.kotlin.analyzer.core

import io.gitlab.arturbosch.detekt.core.KtTreeCompiler
import io.gitlab.arturbosch.detekt.core.tooling.ParsingStrategy

internal val inputPathsToKtFiles: ParsingStrategy = { settings ->
    val compiler = KtTreeCompiler(settings, settings.spec.projectSpec)
    settings.spec.projectSpec.inputPaths.flatMap(compiler::compile)
}
