package com.darcy.kmpdemo

import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState
import kotlin.test.Test
import kotlin.test.assertTrue

open class Prent(
    var screenState: ScreenState = ScreenState.Initial,

) {
    override fun toString(): String {
        return "Prent(screenState=$screenState)"
    }
}

data class Child(
    val content: String
) : Prent() {
    override fun toString(): String {
        return "Child(content='$content' screenState=$screenState)"
    }
}

class DataClassTest {
    @Test
    fun `test-data-class-copy`() {
        val child = Child(
            content = "message1"
        )
        println("child: $child") // message1  Initial
        assertTrue {
            child.content == "message1" && child.screenState == ScreenState.Initial
        }
        val child2 = child.copy(content = "message2").apply {
            screenState = ScreenState.Success
        }
        println("child2: $child2") // message2  Success
        assertTrue {
            child2.content == "message2" && child2.screenState == ScreenState.Success
        }
        val child3 = child2.copy(content = "message3")
        println("child3: $child3") // message3  Initial?
        assertTrue {
            child3.content == "message3" && child3.screenState == ScreenState.Initial
        }
    }
}