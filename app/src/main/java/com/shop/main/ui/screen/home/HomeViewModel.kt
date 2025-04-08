package com.shop.main.ui.screen.home

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.shop.main.domain.model.SectionType
import com.shop.main.domain.usecase.FetchHomeSectionsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class HomeViewModel @AssistedInject constructor(
    @Assisted initialState: HomeState,
    private val fetchHomeSectionsUseCase: FetchHomeSectionsUseCase
) : MavericksViewModel<HomeState>(initialState)
{
    companion object : MavericksViewModelFactory<HomeViewModel, HomeState> by hiltMavericksViewModelFactory()

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeState> {
        override fun create(state: HomeState): HomeViewModel
    }

    init {
        fetchSections()
    }

    fun fetchSections() {
        suspend {
            fetchHomeSectionsUseCase()
        }.execute { result ->
            copy(sections = result)
        }
    }

    fun expandGroup(sectionId: String) = setState {
        val list = sections() ?: return@setState this
        var findFirstRow = false
        var hasInvisible = false
        var anchorItemId = ""
        val updated = list.map { section ->
            if (section.sectionId == sectionId) {
                when (section.contents.type) {
                    SectionType.HEADER -> {
                        anchorItemId = section.itemId
                        section.copy(viewable = true)
                    }
                    SectionType.GRID -> {
                        if (!findFirstRow && !section.viewable) {
                            findFirstRow = true
                            section.copy(viewable = true)
                        } else {
                            hasInvisible = !section.viewable
                            section.copy(viewable = section.viewable)
                        }
                    }
                    SectionType.STYLE_GRID,
                    SectionType.STYLE_GRID_LEFT,
                    SectionType.STYLE_GRID_RIGHT -> {
                        if (!findFirstRow && !section.viewable) {
                            findFirstRow = true
                            anchorItemId = section.itemId
                            section.copy(viewable = true)
                        } else {
                            hasInvisible = !section.viewable
                            section.copy(viewable = section.viewable)
                        }
                    }
                    SectionType.FOOTER -> section.copy(viewable = hasInvisible)
                    else -> section.copy(viewable = true)
                }
            } else section
        }
        copy(
            sections = Success(updated),
            anchorItemId = anchorItemId,
        )
    }

    fun shuffleGroup(sectionId: String) = setState {
        val list = sections() ?: return@setState this
        val updated = Success(
            list.map { section ->
                if (section.sectionId == sectionId) section.copy(shuffleKey = section.shuffleKey.inc()) else section
            }
        )
        copy(sections = updated)
    }


}