package com.shop.main

import com.airbnb.mvrx.Success
import com.airbnb.mvrx.test.MavericksTestRule
import com.airbnb.mvrx.withState
import com.shop.main.domain.model.SectionType
import com.shop.main.domain.usecase.FetchHomeSectionsUseCase
import com.shop.main.ui.screen.home.HomeState
import com.shop.main.ui.screen.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mavericksTestRule = MavericksTestRule(testDispatcher = UnconfinedTestDispatcher())
    private val mockFetchHomeUseCase = mockk<FetchHomeSectionsUseCase>()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            initialState = HomeState(),
            fetchHomeSectionsUseCase = mockFetchHomeUseCase
        )
    }

    @Test
    fun `fetchHomeSections 성공 시 상태가 Success로 설정된다`() = runTest {
        // given
        val mockResponse = MockData.mockResponse
        coEvery { mockFetchHomeUseCase.invoke() } returns mockResponse

        // when
        viewModel.fetchSections()

        // then
        withState(viewModel) { state ->
            assertTrue(state.sections is Success)
        }
    }

    @Test
    fun `더보기로 아이템이 모두 펼쳐지면 footer의 viewable은 false이다`() = runTest {
        // given
        val mockResponse = MockData.mockResponse
        coEvery { mockFetchHomeUseCase.invoke() } returns mockResponse

        // when
        val sectionId = mockResponse.find { it.contents.type == SectionType.GRID }?.sectionId!!
        viewModel.fetchSections()
        repeat(10) {
            viewModel.expandGroup(sectionId)
        }

        // then
        withState(viewModel) { state ->
            val viewableList = state.sections()?.filter { it.sectionId == sectionId && it.contents.type == SectionType.GRID }?.map { section ->
                section.viewable
            }

            val footerViewable = state.sections()?.find { it.sectionId == sectionId && it.contents.type == SectionType.FOOTER }?.viewable
            if (viewableList?.all { it } == true) {
                println("viewableList is all expanded, footerViewable = $footerViewable")
                assertTrue(footerViewable == false)
            }
            else {
                println("viewableList is not all expanded, footerViewable = $footerViewable")
                assertTrue(footerViewable == true)
            }
        }
    }

    @Test
    fun `더보기로 아이템이 모두 펼쳐지지 않았으면 footer의 viewable은 true이다`() = runTest {
        // given
        val mockResponse = MockData.mockResponse
        coEvery { mockFetchHomeUseCase.invoke() } returns mockResponse

        // when
        val sectionId = mockResponse.find { it.contents.type == SectionType.GRID }?.sectionId!!
        viewModel.fetchSections()
        viewModel.expandGroup(sectionId)

        // then
        withState(viewModel) { state ->
            val viewableList = state.sections()?.filter { it.sectionId == sectionId && it.contents.type == SectionType.GRID }?.map { section ->
                section.viewable
            }

            val footerViewable = state.sections()?.find { it.sectionId == sectionId && it.contents.type == SectionType.FOOTER }?.viewable
            if (viewableList?.all { it } == true) {
                println("모두 펼쳐짐, footerViewable = $footerViewable")
                assertTrue(footerViewable == false)
            }
            else {
                println("모두 펼쳐지지 않음, footerViewable = $footerViewable")
                assertTrue(footerViewable == true)
            }
        }
    }


    @Test
    fun `PRODUCT_GRID_ROW shuffleGroup() 호출 후  shuffleKey 값은 0 이상 이다`() = runTest {
        // given
        val mockResponse = MockData.mockResponse
        coEvery { mockFetchHomeUseCase.invoke() } returns mockResponse

        // when
        val sectionId = mockResponse.find { it.contents.type == SectionType.SCROLL }?.sectionId!!
        viewModel.fetchSections()
        viewModel.shuffleGroup(sectionId)

        // then
        withState(viewModel) { state ->
            state.sections()?.first { it.sectionId == sectionId && it.contents.type == SectionType.SCROLL }?.let { section ->
                assertTrue(section.shuffleKey > 0)
            }
        }
    }
}