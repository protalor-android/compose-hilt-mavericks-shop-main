package com.shop.main.ui.screen.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.shop.main.domain.model.HomeSectionModel

data class HomeState(
    val sections: Async<List<HomeSectionModel>> = Uninitialized,
    val anchorItemId: String? = null
) : MavericksState