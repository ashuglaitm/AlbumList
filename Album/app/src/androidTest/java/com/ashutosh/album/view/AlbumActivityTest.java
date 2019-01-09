package com.ashutosh.album.view;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.ashutosh.album.R;
import com.ashutosh.album.common.Constant;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class AlbumActivityTest extends BaseTest {

    @Rule
    public ActivityTestRule<AlbumActivity> activityTestRule = new ActivityTestRule<>(AlbumActivity.class);

    /**
     * Testing album list
     */
    @Test
    public void testRecyclerListView() {
        doWait(TIME_5MS);

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));

        doWait(TIME_1MS);
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        doWait(TIME_1MS);
        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.recyclerView);
        int item = recyclerView.getAdapter().getItemCount();
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition(item - 1));

        doWait(TIME_1MS);
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(item - 1, click()));
    }

}
