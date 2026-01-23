package com.darcy.kmpdemo.ui.screen.loaddata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.darcy.kmpdemo.ui.base.BaseViewModel
import com.darcy.kmpdemo.ui.base.IIntent
import com.darcy.kmpdemo.ui.base.IReducer
import com.darcy.kmpdemo.ui.base.IState
import com.darcy.kmpdemo.ui.base.ScreenStatus
import com.darcy.kmpdemo.ui.base.impl.LoadDataReducer2
import com.darcy.kmpdemo.ui.base.impl.ScreenStatusIntent
import com.darcy.kmpdemo.ui.base.impl.ScreenStatusReducer
import com.darcy.kmpdemo.ui.screen.loaddata.intent.LoadDataIntent
import com.darcy.kmpdemo.ui.screen.loaddata.reducer.LoadDataReducer
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState
import kotlinx.coroutines.delay
import kotlin.reflect.KClass

class LoadDataViewModel : BaseViewModel<LoadDataState>() {
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: KClass<T>,
                extras: CreationExtras
            ): T {
                return LoadDataViewModel() as T
            }
        }
    }

    override fun initState(): LoadDataState {
        return LoadDataState()
    }

    override fun initReducers(): List<IReducer<LoadDataState>> {
        return listOf(
            LoadDataReducer2(),
//            ScreenStatusReducer(),
        )
    }

    override fun dispatch(intent: IIntent) {
        when (intent) {
            is LoadDataIntent.ActionLoadData -> {
                io {
                    dispatch(ScreenStatusIntent.ScreenStateChange(ScreenStatus.Loading))
                    delay(2_000)
                    val result = "加载数据成功"
                    dispatch(LoadDataIntent.RefreshByLoadData(result))
                    dispatch(ScreenStatusIntent.ScreenStateChange(ScreenStatus.Success))
                }
            }

            else -> {
                super.dispatch(intent)
            }
        }
    }
}