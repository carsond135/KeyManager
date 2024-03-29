@file:Suppress("DSL_SCOPE_VIOLATION")

import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.hilt) apply false

    id("dev.yash.keymanager.spotless")
    id("dev.yash.keymanager.githooks")
}

val clean by tasks.existing(Delete::class) { delete(rootProject.buildDir) }

afterEvaluate {
    tasks.prepareKotlinBuildScriptModel.dependsOn(tasks.copyGitHooks, tasks.installGitHooks)
}
