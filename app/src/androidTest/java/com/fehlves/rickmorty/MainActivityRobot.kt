package com.fehlves.rickmorty

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.fehlves.rickmorty.features.main.MainActivity


class MainActivityRobot {

    private lateinit var scenario: ActivityScenario<MainActivity>

    fun launchActivity() = apply {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    //region: given


    //endregion

    //region: when

    fun whenTitleAppear() = apply {
        onView(withId(R.id.ivLogo)).check(matches(isDisplayed()))
    }

    fun whenListHasAllItems(count: Int) = apply {
        onView(withId(R.id.rvMain)).check(matches(hasChildCount(count)))
    }

    //endregion

    //region: then

    fun thenTitleHasDescription() = apply {
        onView(withId(R.id.ivLogo)).check(matches(hasContentDescription()))
    }

    //endregion

}