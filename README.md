# Kotlin Code Analyzer

If you want to analyze your Kotlin Code, especially the references of files, types or functions, you should use the compiler to do the analysis.

But it is not easy to invoke the Kotlin compiler. So I did some encapsulation based on [detekt](https://github.com/detekt/detekt).

Use it like this:

```kotlin
fun main() {
    val result = KotlinCodeAnalyzer(buildOptions {
        inputPaths = listOf("code-analyzer/testData")
    }).analyze()

    println(result.files)

    result.files.forEach {
        it.declarations.forEach {
            when (it) {
                is KtNamedFunction -> {
                    println(it.text)
                }
                is KtClass -> {
                    println(it.text)
                }
            }
        }
    }
}
```

The definition of `result` is shown as below:

```kotlin
data class AnalyzeResult(
    val files: List<KtFile>,
    val bindingContext: BindingContext
)
```

You can visit the `KtFile` to check the declarations in the corresponding source file, and use the `bindingContext` to find the analysis information such as `REFERENCE_TARGET` for a `TypeReference`.