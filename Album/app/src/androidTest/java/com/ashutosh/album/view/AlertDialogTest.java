package com.ashutosh.album.view;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ashutosh.album.R;
import com.ashutosh.album.common.Constant;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AlertDialogTest extends BaseTest {

    private Context mBaseActivityContext;
    private boolean mIsWiFiAlreadyOn;
    private WifiManager mWifiManager;

    @Before
    public void setUp() {
        mBaseActivityContext = activityTestRule.getActivity().getBaseContext();
        deleteOfflineData();
        turnOffWiFiConnection();
    }


    @Rule
    public ActivityTestRule<AlbumActivity> activityTestRule = new ActivityTestRule<>(AlbumActivity.class);

    /**
     * Show error dialog in case of no offline data and no wifi connection (will be failed in case of mobile data on)
     */
    @Test
    public void testErrorDialog(){
        doWait(TIME_1MS);
        onView(withText(R.string.dialog_title)).check(matches(isDisplayed()));
        onView(withText(android.R.string.ok)).check(matches(isDisplayed()));
        doWait(TIME_5MS);
        onView(withText(android.R.string.ok)).perform(click());

        turnOnWiFiConnection();

    }

    private void deleteOfflineData() {
        String fileLocation = mBaseActivityContext.getFilesDir() + File.separator + Constant.DIR_NAME +
                File.separator + Constant.FILE_NAME;
        File file = new File(fileLocation);
        if (file.exists()) {
            file.delete();
        }
    }

    private void turnOffWiFiConnection() {
        mWifiManager = (WifiManager) getInstrumentation().getTargetContext().getSystemService(Context.WIFI_SERVICE);
        if (mWifiManager.isWifiEnabled()) {
            mIsWiFiAlreadyOn = true;
            mWifiManager.setWifiEnabled(false);
        }
    }

    private void turnOnWiFiConnection(){
        if(mIsWiFiAlreadyOn){
            mWifiManager.setWifiEnabled(true);
            doWait(1500);
            mIsWiFiAlreadyOn = false;
        }
    }
}
