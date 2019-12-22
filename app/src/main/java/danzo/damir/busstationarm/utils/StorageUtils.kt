package com.putinbyte.ambulance.utils

import android.content.Context
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs

class StorageUtils {
    private val TAG = this.javaClass.name

    companion object {
        const val APP_HOME_RUNNING = "app_home_running"
        const val LOGINS_STATE = "logins_state"

        const val MOBILE_PROFILE = "mobile_profile"

        const val CURRENT_SESSION = "current_session"
        const val CURRENT_USERID = "current_userid"
        const val CURRENT_BRIGADEMACHINESID = "current_brigademachinesid"
        const val CURRENT_USERNAME = "current_username"
        const val CURRENT_STATUS_ID = "current_status_id"

        const val NEW_CALL_JSON = "new_call_json"
        const val NEW_CALL_DELIVERED_TIME = "new_call_delivered_time"
        const val NEW_CALL_ARRIVAL_2_PATIENT_TIME = "new_call_arrival_2_patient_time"
        const val NEW_CALL_ARRIVAL_2_PATIENT_SENT = "new_call_arrival_2_patient_sent"
        const val NEW_CALL_ACCEPTED_TIME = "new_call_accepted_time"

        const val NEW_HOSPITALIZATION_JSON = "new_hospitalization_json"
        const val NEW_HOSPITALIZATION_STARTED_TIME = "new_hospitalization_started_time"
        const val NEW_HOSPITALIZATION_STATUS = "new_hospitalization_status"
        const val NEW_HOSPITALIZATION_INSERTED_ID = "new_hospitalization_inserted_id"

        const val ACTIVITY_STARTED = "activity_started"
        const val CreateCall4DoctorActivity = "CreateCall4DoctorActivity"
        const val CreateCall4DriverActivity = "CreateCall4DriverActivity"
        const val DetailCall4DoctorActivity = "DetailCall4DoctorActivity"
        const val HospitalizationActivity = "HospitalizationActivity"
        const val ResultCall4DoctorActivity = "ResultCall4DoctorActivity"

        const val ROTATE_MAP_BY_COMPASS_ENABLED = "rotate_map_by_compass_enabled"
    }

    private fun prefsBuilder(context: Context, packageName: String) {
        Prefs.Builder()
                .setContext(context)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()
    }

    fun init(context: Context, packageName: String) {
        try {
            if (Prefs.getPreferences() == null) {
                prefsBuilder(context, packageName)
            }
        } catch (e: Exception) {
            try {
                prefsBuilder(context, packageName)
            } catch (e: Exception) {
                LogUtils.error(TAG, e.message)
            }
        }
    }
}