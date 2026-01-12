package com.darcy.kmpdemo

import com.darcy.kmpdemo.utils.PickHelper
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class PickerTest {
    @Test
    fun `test-pick-a-image`() {
        runTest {
            val path = PickHelper.pickImage()
            println("path: $path")
        }
    }

    @Test
    fun `test-pick-a-file`() {
        runTest {
            val path = PickHelper.pickFile()
            println("path: $path")
        }
    }
}