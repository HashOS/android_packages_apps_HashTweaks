/*
 *  Copyright (C) 2018 HashOS
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
*/

package org.hash.hashtweaks;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import android.support.v14.preference.SwitchPreference;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;

import com.android.internal.util.hash.DeviceUtils;

public class ButtonSettings extends SettingsPreferenceFragment {

    private static final String CATEGORY_OTHER = "button_other";
    private static final String CATEGORY_POWER = "button_power";
    private static final String SYSTEM_PROXI_CHECK_ENABLED = "system_proxi_check_enabled";

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.HASH_SETTINGS;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.button_settings);

        final PreferenceScreen prefScreen = getPreferenceScreen();
        final PreferenceCategory otherCategory =
                (PreferenceCategory) prefScreen.findPreference(CATEGORY_OTHER);
        final PreferenceCategory powerCategory =
                (PreferenceCategory) prefScreen.findPreference(CATEGORY_POWER);

        boolean supportPowerButtonProxyCheck = getResources().getBoolean(com.android.internal.R.bool.config_proxiSensorWakupCheck);
        SwitchPreference proxyCheckPreference = (SwitchPreference) findPreference(SYSTEM_PROXI_CHECK_ENABLED);
        if (!DeviceUtils.deviceSupportsProximitySensor(getActivity()) || !supportPowerButtonProxyCheck) {
            powerCategory.removePreference(proxyCheckPreference);
        }
    }
}
