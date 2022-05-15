package com.bennyhuo.kotlin.analyzer

import com.bennyhuo.kotlin.analyzer.core.InternalAnalyzer
import com.bennyhuo.kotlin.analyzer.core.toSettings

/**
 * Created by benny.
 */
class KotlinCodeAnalyzer(private val options: Options) {
    fun analyze(): AnalyzeResult {
        return InternalAnalyzer(options.toProcessingSpec().toSettings()).run()
    }
}