package br.alexandregpereira.abacate.ui.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.alexandregpereira.abacate.presentation.MainActivity
import br.alexandregpereira.abacate.ui.theme.AbacateTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalAnimationApi
@RunWith(AndroidJUnit4::class)
class ActionHeaderTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldShowTextAndHideImages() {
        composeTestRule.setContent {
            AbacateTheme {
                ActionHeader(
                    text = "Anything"
                )
            }
        }

        composeTestRule.onNodeWithText(text = "Anything").assertExists()
        composeTestRule.onNodeWithContentDescription("OvalImages").assertDoesNotExist()
        composeTestRule.onNodeWithTag("ActionHeader").assert(isNotEnabled())
        composeTestRule.onNodeWithTag("ActionHeader").assert(hasClickAction())
    }

    @Test
    fun shouldShowThreeImages() {
        composeTestRule.setContent {
            AbacateTheme {
                ActionHeader(
                    text = "Anything",
                    urls = (0..2).map { "Any" }
                )
            }
        }

        composeTestRule.onNodeWithText(text = "Anything").assertExists()
        composeTestRule.onNodeWithContentDescription("OvalImages").assertExists()
        composeTestRule.onNodeWithTag("ActionHeader").assert(isEnabled())
    }

    @Test
    fun shouldShowHideText() {
        composeTestRule.setContent {
            AbacateTheme {
                ActionHeader(
                    text = "Anything",
                    urls = (0..2).map { "Any" }
                )
            }
        }

        composeTestRule.onNodeWithTag("ActionHeader").performClick()
        composeTestRule.onNodeWithText(text = "Anything").assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("OvalImages").assertExists()
        composeTestRule.onNodeWithTag("ActionHeader").assert(isEnabled())
    }

    @Test
    fun shouldShowShowText() {
        composeTestRule.setContent {
            AbacateTheme {
                ActionHeader(
                    text = "Anything",
                    urls = (0..2).map { "Any" }
                )
            }
        }

        composeTestRule.onNodeWithTag("ActionHeader").performClick()
        composeTestRule.onNodeWithTag("ActionHeader").performClick()
        composeTestRule.onNodeWithText(text = "Anything").assertExists()
        composeTestRule.onNodeWithContentDescription("OvalImages").assertExists()
        composeTestRule.onNodeWithTag("ActionHeader").assert(isEnabled())
    }
}