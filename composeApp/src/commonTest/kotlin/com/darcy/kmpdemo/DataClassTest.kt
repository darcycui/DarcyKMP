package com.darcy.kmpdemo

import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState
import kotlin.test.Test

class DataClassTest {
    @Test
    fun `test-data-class-copy`() {
        val screenStateState = ScreenStatusState(
            screenState = ScreenState.Initial
        )
        println("screenStatusState: $screenStateState")
        val loadDataState = LoadDataState(
            content = "message1"
        )
        println("loadDataState: $loadDataState")
        val loadDataState2 = loadDataState.copy(content = "message2").apply {
            screenState = ScreenState.Success
        }
        println("loadDataState2: $loadDataState2") // message2  Success
        val loadDataState3 = loadDataState2.copy(content = "message3")
        println("loadDataState3: $loadDataState3") // message3  Initial?
    }
}