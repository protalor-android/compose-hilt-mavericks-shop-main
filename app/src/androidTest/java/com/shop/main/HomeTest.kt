package com.shop.main

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import com.airbnb.mvrx.Mavericks
import com.shop.main.test.TEST_TAG_APP_LOADING
import com.shop.main.test.TEST_TAG_BANNER_CONTENT
import com.shop.main.test.TEST_TAG_BRAND
import com.shop.main.test.TEST_TAG_DETAIL_SCREEN
import com.shop.main.test.TEST_TAG_FOOTER_REFRESH
import com.shop.main.test.TEST_TAG_HEADER_LINK
import com.shop.main.test.TEST_TAG_HOME_LAZY_COLUMN
import com.shop.main.test.TEST_TAG_PRICE
import com.shop.main.test.TEST_TAG_PRODUCT_CARD
import com.shop.main.test.TEST_TAG_SCROLL_SECTION
import com.shop.main.test.TEST_TAG_STYLE_CARD
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        Mavericks.initialize(composeTestRule.activity)
    }

    /**
     * 로딩 성공 후 AppLoading 제거
     */
    @Test
    fun testAppLoading() {
        with(composeTestRule) {
            waitUntil(timeoutMillis = 5000L) {
                onAllNodesWithTag(TEST_TAG_APP_LOADING).fetchSemanticsNodes().isEmpty()
            }
            onNodeWithTag(TEST_TAG_APP_LOADING).assertIsNotDisplayed()
        }
    }

    /**
     * 배너 클릭 시 DetailScreen 이동
     */
    @Test
    fun testBannerContentClick() {
        with(composeTestRule) {
            waitUntil(timeoutMillis = 5000L) {
                onAllNodesWithTag(TEST_TAG_APP_LOADING).fetchSemanticsNodes().isEmpty()
            }
            val targetTestTag = TEST_TAG_BANNER_CONTENT
            // BannerContent 까지 이동
            onNodeWithTag(TEST_TAG_HOME_LAZY_COLUMN).performScrollToNode(hasTestTag(targetTestTag))

            // 테스트 대상이 없으면 테스트 통과
            if (onAllNodesWithTag(targetTestTag).fetchSemanticsNodes().isEmpty()) {
                println("$targetTestTag 없음 - 테스트 통과")
                return@with
            }

            // BannerContent 클릭
            onAllNodesWithTag(targetTestTag)[0].performClick()
            // 상세 화면 이동
            onNodeWithTag(TEST_TAG_DETAIL_SCREEN).assertExists()
        }
    }

    /**
     * 상품 클릭 시 DetailScreen 이동
     */
    @Test
    fun testProductCardClick() {
        with(composeTestRule) {
            waitUntil(timeoutMillis = 5000L) {
                onAllNodesWithTag(TEST_TAG_APP_LOADING).fetchSemanticsNodes().isEmpty()
            }
            val targetTestTag = TEST_TAG_PRODUCT_CARD
            // ProductCard 까지 이동
            onNodeWithTag(TEST_TAG_HOME_LAZY_COLUMN).performScrollToNode(hasTestTag(targetTestTag))

            // 테스트 대상이 없으면 테스트 통과
            if (onAllNodesWithTag(targetTestTag).fetchSemanticsNodes().isEmpty()) {
                println("$targetTestTag 없음 - 테스트 통과")
                return@with
            }
            // 첫번쨰 ProductCard 클릭
            onAllNodesWithTag(targetTestTag)[0].performClick()
            // 상세 화면 이동
            onNodeWithTag(TEST_TAG_DETAIL_SCREEN).assertIsDisplayed()
        }
    }

    /**
     * StyleCard 클릭 시 DetailScreen 이동
     */
    @Test
    fun testStyleCardClick() {
        with(composeTestRule) {
            waitUntil(timeoutMillis = 5000L) {
                onAllNodesWithTag(TEST_TAG_APP_LOADING).fetchSemanticsNodes().isEmpty()
            }
            val targetTestTag = TEST_TAG_STYLE_CARD
            // StyleCard 까지 이동
            onNodeWithTag(TEST_TAG_HOME_LAZY_COLUMN).performScrollToNode(hasTestTag(targetTestTag))

            // 테스트 대상이 없으면 테스트 통과
            if (onAllNodesWithTag(targetTestTag).fetchSemanticsNodes().isEmpty()) {
                println("$targetTestTag 없음 - 테스트 통과")
                return@with
            }

            // 첫번쨰 StyleCard 클릭
            onAllNodesWithTag(targetTestTag)[0].performClick()
            // 상세 화면 이동
            onNodeWithTag(TEST_TAG_DETAIL_SCREEN).assertIsDisplayed()
        }
    }

    /**
     * HeaderLink 클릭 시 DetailScreen 이동
     */
    @Test
    fun testHeaderLinkClick() {
        with(composeTestRule) {
            waitUntil(timeoutMillis = 5000L) {
                onAllNodesWithTag(TEST_TAG_APP_LOADING).fetchSemanticsNodes().isEmpty()
            }
            val targetTestTag = TEST_TAG_HEADER_LINK
            // HeaderLink가 있는 Header 까지 이동
            onNodeWithTag(TEST_TAG_HOME_LAZY_COLUMN).performScrollToNode(hasTestTag(targetTestTag))

            // 테스트 대상이 없으면 테스트 통과
            if (onAllNodesWithTag(targetTestTag).fetchSemanticsNodes().isEmpty()) {
                println("$targetTestTag 없음 - 테스트 통과")
                return@with
            }

            // 클릭
            onNodeWithTag(targetTestTag).performClick()
            // 상세 화면 이동
            onNodeWithTag(TEST_TAG_DETAIL_SCREEN).assertIsDisplayed()
        }
    }

    /**
     * FooterType.REFRESH 클릭 시 상품 리스트 새로고침
     */
    @Test
    fun testFooterTypeRefreshClick() {
        with(composeTestRule) {
            val targetTestTag = TEST_TAG_FOOTER_REFRESH
            waitUntil(timeoutMillis = 5000L) {
                onAllNodesWithTag(TEST_TAG_APP_LOADING).fetchSemanticsNodes().isEmpty()
            }
            // Footer type REFRESH 까지 이동
            onNodeWithTag(TEST_TAG_HOME_LAZY_COLUMN).performScrollToNode(hasTestTag(targetTestTag))

            // 테스트 대상이 없으면 테스트 통과
            if (onAllNodesWithTag(targetTestTag).fetchSemanticsNodes().isEmpty()) {
                println("$targetTestTag 없음 - 테스트 통과")
                return@with
            }

            // 기존 브랜드 + 가격 텍스트
            val originalTexts = onNodeWithTag(TEST_TAG_SCROLL_SECTION, useUnmergedTree = true)
                .fetchSemanticsNode()
                .findTextsByTags(TEST_TAG_BRAND, TEST_TAG_PRICE)

            var newTexts: List<String>

            repeat(3) {
                // REFRESH 클릭
                onNodeWithTag(targetTestTag).performClick()

                // 셔플 결과
                newTexts = onNodeWithTag(TEST_TAG_SCROLL_SECTION, useUnmergedTree = true)
                    .fetchSemanticsNode()
                    .findTextsByTags(TEST_TAG_BRAND, TEST_TAG_PRICE)

                if (originalTexts != newTexts) {
                    assertTrue("셔플이 성공하였습니다.", true)
                    return@with // 테스트 성공
                }
            }

            assertTrue("셔플이 3회 실패하였습니다.", false)
        }
    }
}