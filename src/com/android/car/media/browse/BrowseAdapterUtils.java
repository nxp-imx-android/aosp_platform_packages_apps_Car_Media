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

import android.view.View;
import android.widget.ProgressBar;

import androidx.media.utils.MediaConstants;

import com.android.car.apps.common.util.ViewUtils;

/**
 * Utility class for {@link BrowseViewHolder}
 */
public class BrowseAdapterUtils {

    /**
     * <p>
     * Handles hiding and showing new media indicator.
     * </p>
     *
     * <p>
     * {@link MediaConstants#DESCRIPTION_EXTRAS_VALUE_COMPLETION_STATUS_NOT_PLAYED}
     * completeIndicator visible
     * </p>
     * <p>
     * {@link MediaConstants#DESCRIPTION_EXTRAS_VALUE_COMPLETION_STATUS_PARTIALLY_PLAYED}
     * completeIndicator hidden
     * </p>
     * <p>
     * {@link MediaConstants#DESCRIPTION_EXTRAS_VALUE_COMPLETION_STATUS_FULLY_PLAYED}
     * completeIndicator hidden
     * </p>
     * Default: Hidden
     *
     * @param status            as defined {@link MediaConstants}
     *                          with key
     *                          {@link MediaConstants#DESCRIPTION_EXTRAS_KEY_COMPLETION_STATUS}
     * @param completeIndicator view for complete indicator
     */
    public static void handleNewMediaIndicator(int status, View completeIndicator) {
        if (completeIndicator != null) {
            ViewUtils.setVisible(completeIndicator,
                    status == MediaConstants.DESCRIPTION_EXTRAS_VALUE_COMPLETION_STATUS_NOT_PLAYED);
        }
    }

    /**
     * Sets progressbar progress.
     * Converts the progress param from 0.0 - 1.0 to 0 - 100
     * @param progressIndicator - Progressbar
     * @param progress - 0.0 - 1.0
     */
    public static void setPlaybackProgressIndicator(ProgressBar progressIndicator,
            double progress) {
        if (progressIndicator != null) {
            ViewUtils.setVisible(progressIndicator, progress > 0.0 && progress < 1.0);
            progressIndicator.setProgress((int) (progress * 100));
        }
    }
}
