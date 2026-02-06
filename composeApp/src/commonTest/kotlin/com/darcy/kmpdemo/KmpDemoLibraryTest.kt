//package com.darcy.kmpdemo
//
//import com.darcy.kmpdemo.log.Loger
//import io.github.kotlin.fibonacci.generateFibi
//import kotlin.test.BeforeTest
//import kotlin.test.Test
//import kotlin.test.assertEquals
//
//class KmpDemoLibraryTest {
//
//    @BeforeTest
//    fun setUp() {
//        Loger.initLogger()
//    }
//    @Test
//    fun `test-method-call-from-kmp-library-demo`() {
//        val x = generateFibi().take(3).last()
//        assertEquals(5, x)
//    }
//}