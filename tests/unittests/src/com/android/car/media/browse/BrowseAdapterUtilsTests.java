/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.car.media.browse;

import static junit.framework.Assert.assertEquals;

import static org.mockito.Mockito.*;

import android.view.View;
import android.widget.ProgressBar;

import androidx.media.utils.MediaConstants;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android.car.media.BaseMockitoTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;


@RunWith(AndroidJUnit4.class)
public class BrowseAdapterUtilsTests extends BaseMockitoTest {

    @Mock
    View mTestView;

    @Mock
    ProgressBar mTestProgressBar;

    @Captor
    ArgumentCaptor<Integer> mVisibilityCaptor;

    @Captor
    ArgumentCaptor<Integer> mProgressCaptor;

    @Test
    public void newIndicatorVisibility() {
        int status = MediaConstants.DESCRIPTION_EXTRAS_VALUE_COMPLETION_STATUS_NOT_PLAYED;
        BrowseAdapterUtils.handleNewMediaIndicator(status, mTestView);
        verify(mTestView, atLeastOnce()).setVisibility(mVisibilityCaptor.capture());
        assertEquals(View.VISIBLE, mVisibilityCaptor.getValue().intValue());

        status = MediaConstants.DESCRIPTION_EXTRAS_VALUE_COMPLETION_STATUS_PARTIALLY_PLAYED;
        BrowseAdapterUtils.handleNewMediaIndicator(status, mTestView);
        verify(mTestView, atLeastOnce()).setVisibility(mVisibilityCaptor.capture());
        assertEquals(View.GONE, mVisibilityCaptor.getValue().intValue());

        status = MediaConstants.DESCRIPTION_EXTRAS_VALUE_COMPLETION_STATUS_FULLY_PLAYED;
        BrowseAdapterUtils.handleNewMediaIndicator(status, mTestView);
        verify(mTestView, atLeastOnce()).setVisibility(mVisibilityCaptor.capture());
        assertEquals(View.GONE, mVisibilityCaptor.getValue().intValue());
    }

    @Test
    public void progressIndicatorVisibility() {
        double progress = -1;
        BrowseAdapterUtils.setPlaybackProgressIndicator(mTestProgressBar, progress);
        verify(mTestProgressBar, atLeastOnce()).setProgress(mProgressCaptor.capture());
        verify(mTestProgressBar, atLeastOnce()).setVisibility(mVisibilityCaptor.capture());
        assertEquals(-100, mProgressCaptor.getValue().intValue());
        assertEquals(View.GONE, mVisibilityCaptor.getValue().intValue());

        progress = .33;
        BrowseAdapterUtils.setPlaybackProgressIndicator(mTestProgressBar, progress);
        verify(mTestProgressBar, atLeastOnce()).setProgress(mProgressCaptor.capture());
        verify(mTestProgressBar, atLeastOnce()).setVisibility(mVisibilityCaptor.capture());
        assertEquals(33, mProgressCaptor.getValue().intValue());
        assertEquals(View.VISIBLE, mVisibilityCaptor.getValue().intValue());

        progress = 1.0;
        BrowseAdapterUtils.setPlaybackProgressIndicator(mTestProgressBar, progress);
        verify(mTestProgressBar, atLeastOnce()).setProgress(mProgressCaptor.capture());
        verify(mTestProgressBar, atLeastOnce()).setVisibility(mVisibilityCaptor.capture());
        assertEquals(100, mProgressCaptor.getValue().intValue());
        assertEquals(View.GONE, mVisibilityCaptor.getValue().intValue());
    }
}
