package com.anvipus.core.utils

class Constants {
    companion object {
        const val KEY_CARD = "CARD"
        const val KEY_EXTRA = "EXTRA"
        const val KEY_EMONEY = "EMONEY"
        const val KEY_NONE = "NONE"
        const val KEY_TEXT_PLAINT = "text/plain"
        const val KEY_INITIAL_PARAMS_MERCHANT_LABEL = "merchant_label"
        const val KEY_INITIAL_PARAMS_TOKEN = "token"
        const val KEY_INITIAL_PARAMS_DLK_CODE = "dlk_code"
        const val KEY_INITIAL_PARAMS_PHONE_ID = "phone_id"
        const val KEY_INITIAL_PARAMS_FK_USER_ID = "fk_user_id"
        const val KEY_INITIAL_PARAMS_FK_WALLET_ID = "fk_wallet_id"
        const val KEY_INITIAL_PARAMS_VERSION_CODE = "application_version_code"
        const val KEY_INITIAL_PARAMS_VERSION_NAME = "application_version_name"
        const val KEY_INITIAL_PARAMS_OS_TYPE = "device_os"
        const val KEY_INITIAL_PARAMS_SDK_VERSION = "sdk_version"
        const val KEY_AOS = "android"
        const val KEY_INITIAL_PARAMS_FINGERPRINT = "device_identification"
        const val KEY_INITIAL_PARAMS_DEVICE_ID = "device_id"
        const val KEY_INITIAL_PARAMS_USER_AGENT = "user_agent"
        const val KEY_INITIAL_PARAMS_PUBLIC_IP_ADDRESS = "public_ip_address"
        const val KEY_INITIAL_PARAMS_LOCATION = "location"
        const val KEY_INITIAL_PARAMS_MANUFACTURE = "manufacture"
        const val KEY_INITIAL_PARAMS_MODEL = "model"
        const val KEY_INITIAL_PARAMS_BRAND = "brand"
        const val KEY_WEBVIEW = "webview"
        const val KEY_QRIS_CHECK_STATUS = "qris_check_status"
        const val KEY_TIMESTAMP = "timestamp"
        const val KEY_TIMEZONE = "timezone"
        const val FORMAT_DATE_V1 = "yyyy-MM-dd HH:mm:ss"
        const val FORMAT_DATE_V2 = "dd-MMM-yyyy"
        const val FORMAT_DATE_V3 = "dd MMM yyyy, HH:mm:ss"
        const val KEY_IS_REFRESHED_HOME = "is_refreshed_home"
        const val KEY_LAST_IP_ADDRESS = "last_ip_address"
        const val KEY_USER_NOT_FOUND = "USER_NOT_FOUND"
        const val KEY_PREFIX_CODE_NOT_EXIST = "PREFIX_CODE_NOT_EXIST"
        const val KEY_BILLER_TIMEOUT = "BILLER_TIMEOUT"
        const val KEY_PAY_PENDING = "PAY_PENDING"
        const val KEY_BILLER_POSTPAID = "biller_postpaid"
        const val KEY_BILLER_PREPAID = "biller_prepaid"
        const val KEY_BILLER = "biller"
        const val KEY_DEEPLINK_PAYMENT = "deeplink_payment"
        const val KEY_AUTH_ID_EXPIRED = "AUTH_ID_EXPIRED"
        const val KEY_AUTH_ID_NOT_FOUND = "AUTH_ID_NOT_FOUND"
        const val KEY_PIN_AUTH_NOT_VALID = "PIN_AUTH_NOT_VALID"
        const val KEY_PUSH_TO_PAY = "push_to_pay"
        const val KEY_PUSH_TO_PAY_HOME = "push_to_pay_home"
        const val KEY_PUSH_TO_PAY_NOTIF = "push_to_pay_notif"

        //category value
        const val KEY_MNC_BILL_PAY = "MNC"
        const val KEY_LISTRIK_PULSA = "TOKEN_PLN"
        const val KEY_MOBILE_PULSA = "PULSA"
        const val KEY_MOBILE_DATA = "PAKET_DATA"
        const val KEY_GAME_VOUCHER = "game_voucher"
        const val KEY_BPJS = "bpjs"
        const val KEY_MNC_POINT = "point"
        const val KEY_PDAM = "PDAM"
        const val KEY_INDIHOME = "INDIHOME"
        const val KEY_TAGIHAN_LISTRIK = "TAGIHAN_PLN"
        const val KEY_TAGIHAN_LISTRIK_2 = "TAGIHANPLN"
        const val KEY_PREPAID = "PREPAID"
        const val KEY_POSTPAID = "POSTPAID"
        const val KEY_HTTP = "http"
        const val KEY_PLACEHOLDER = "_PLACEHOLDER"
        const val KEY_TOP_BANNER = "TOP_BANNER"
        const val KEY_BACKGROUND_BANNER = "CONFIG_BACKGROUND_BANNER"
        const val KEY_URL_LINK = "URL_LINK"
        const val KEY_TAGIHAN_PASCABAYAR = "PASKABAYAR"
        const val KEY_TELKOM = "telkom"
        const val KEY_KVISION = "KVISION"
        const val KEY_KVISION_2 = "K-VISION"
        const val KEY_LOYALTY_LIST = "loyalty_list"
        const val KEY_NOTIF_LIST = "notification_list"
        const val KEY_PARKING = "parking"

        //firebase crashlytics
        const val KEY_FIREBASE_PHONE = "PHONE"
        const val KEY_FIREBASE_NAME = "NAME"
        const val KEY_FIREBASE_EMAIL = "EMAIL"
        const val KEY_FIREBASE_BIRTHDAY = "BIRTHDAY"
        const val KEY_FIREBASE_ISVERIFIED = "IS_VERIFIED"
        const val KEY_MERCHANT_NAME = "merchantName"

        //firebase config
        const val KEY_FIREBASE_CONFIG_QRIS_LIMIT = "limit_qris_transaction"
        const val KEY_FIREBASE_CONFIG_COOLDOWN_HOME = "cooldown_refresh_home"
        const val KEY_FIREBASE_CONFIG_MENU_POINT_HOME = "android_menu_point"
        const val KEY_FIREBASE_CONFIG_MENU_POINT_HOME_SBY = "android_menu_point_sby"
        const val KEY_FIREBASE_CONFIG_MENU_POINT_HOME_STAGING = "android_menu_point_staging"
        const val KEY_FIREBASE_VERSION = "android_version"
        const val KEY_FIREBASE_FORCE_UPDATE = "android_patch_force_update"
        const val KEY_FIREBASE_CONFIG_WORDING_TELCO_POSTPAID = "android_wording_telco_postpaid"
        const val KEY_FIREBASE_CONFIG_FORCE_UPDATE_TITLE = "android_force_update_title"
        const val KEY_FIREBASE_CONFIG_FORCE_UPDATE_MESSAGE = "android_force_update_message"
        const val KEY_FIREBASE_CONFIG_VERSION_NOT_ALLOWED = "android_version_not_allowed"
        const val KEY_FIREBASE_CONFIG_PLAYSTORE_URL= "android_playstore_url"
        const val KEY_FIREBASE_CONFIG_FORCE_UPDATE_BUTTON = "android_force_update_button"
        const val KEY_FIREBASE_CONFIG_MAINTENACE_MODE_BUTTON = "android_maintenance_mode_button"
        const val KEY_FIREBASE_CONFIG_MAINTENACE_MODE_TITLE = "android_maintenance_mode_title"
        const val KEY_FIREBASE_CONFIG_MAINTENACE_MODE_MESSAGE = "android_maintenance_mode_message"
        const val KEY_FIREBASE_CONFIG_MAINTENACE_MODE_RELEASE = "android_maintenance_mode_release"
        const val KEY_FIREBASE_CONFIG_MAINTENACE_MODE_STAGING = "android_maintenance_mode_staging"
        const val KEY_FIREBASE_CONFIG_MAINTENACE_MODE_SCANPAY_RELEASE = "android_maintenance_mode_scanpay_release"
        const val KEY_FIREBASE_CONFIG_MAINTENACE_MODE_SCANPAY_STAGING = "android_maintenance_mode_scanpay_staging"
        const val KEY_FIREBASE_NO_TLP = "no_tlp_cs"
        const val KEY_FIREBASE_NO_WA = "no_whatsapp_cs"
        const val KEY_FIREBASE_ACCOUNT_EMAIL = "account_email"
        const val KEY_FIREBASE_ACCOUNT_INSTAGRAM = "account_instagram"
        const val KEY_FIREBASE_CONFIG_MOTIONPAY_PROGRAM_STAGING = "spectacularDrawDevStag"
        const val KEY_FIREBASE_CONFIG_MOTIONPAY_PROGRAM_RELEASE = "spectacularDrawProd"
        const val KEY_FIREBASE_CONFIG_MOTIONPAY_PROGRAM_URL_STAGING = "spectacularDrawUrlDevStag"
        const val KEY_FIREBASE_CONFIG_MOTIONPAY_PROGRAM_URL_RELEASE = "spectacularDrawUrlProd"
        const val KEY_FIREBASE_CONFIG_MOTIONPAY_PROGRAM_TITLE_STAGING = "spectacularWebViewNavTitleDevStag"
        const val KEY_FIREBASE_CONFIG_MOTIONPAY_PROGRAM_TITLE_RELEASE = "spectacularWebViewNavTitleProd"

        //auth
        const val KEY_CHECK_BALANCE_FORCE_LOGOUT = "CHECK_BALANCE_FORCE_LOGOUT"
        const val KEY_FAILED_CHECK_BALANCE = "FAILED_CHECK_BALANCE"

        const val KEY_FIRST_LOGIN = "first_login"
        const val KEY_MNC_LOGO = "key_mnc_logo"

        const val TYPE_PREPAID = "prepaid"
        const val TYPE_POSTPAID = "postpaid"
        const val BEARER = "Bearer "
        const val auth = "571255784526-k8nduau66bfqbu2195ccgamndsfanjsd.apps.googleusercontent.com"


    }
}