package com.shop.main

import com.airbnb.mvrx.test.MavericksTestRule
import com.shop.main.domain.mapper.toDomain
import com.shop.main.domain.model.SectionType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SectionMapperTest {

    @get:Rule
    val mavericksTestRule = MavericksTestRule(testDispatcher = UnconfinedTestDispatcher())

    @Test
    fun `SectionDto 순서와 Mapper 동작 후 HomeSectionModel 의 결과 순서는 같아야 한다`() = runTest {
        // given
        val mockSectionList = MockData.mockSectionList
        val mockResponse = mockSectionList.flatMap { it.toDomain() }
        val orgTypes = mutableListOf<SectionType>()

        // when
        mockSectionList.forEach { section ->
            section.header?.let { orgTypes.add(SectionType.HEADER) }
            when (section.contents.type) {
                "BANNER" -> orgTypes.add(SectionType.BANNER)
                "GRID" -> section.contents.goods?.chunked(3)?.map { orgTypes.add(SectionType.GRID) }
                "SCROLL" -> orgTypes.add(SectionType.SCROLL)
                "STYLE" -> {
                    section.contents.styles?.chunked(6)?.forEachIndexed { index, chunk ->
                        val type = when {
                            chunk.size >= 6 -> {
                                if (index % 2 == 0) SectionType.STYLE_GRID_LEFT else SectionType.STYLE_GRID_RIGHT
                            }
                            else -> SectionType.STYLE_GRID
                        }
                        orgTypes.add(type)
                    }
                }
            }
            section.footer?.let { orgTypes.add(SectionType.FOOTER) }
        }

        val result = mockResponse.map { homeSectionModel ->
            homeSectionModel.contents.type
        }

        // then
        orgTypes.forEachIndexed { index, type ->
            assertEquals(type, result[index])
        }
    }
}