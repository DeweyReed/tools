@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package xyz.aprildown.tools.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.ShareCompat
import androidx.core.net.toUri

/**
 * Created on 2018/8/22.
 */

object IntentHelper {
    fun intent(str: String) = Intent(Intent.ACTION_VIEW, str.toUri())

    fun webPage(url: String) = intent(url)

    fun playStoreAppPage(context: Context, name: String = context.packageName): Intent {
        val intent = intent("market://details?id=%s".format(name))
        return if (intent.resolveActivity(context.packageManager) != null) {
            intent
        } else {
            intent("https://play.google.com/store/apps/details?id=%s".format(name))
        }
    }

    fun playStoreAppsList(context: Context, name: String): Intent {
        val intent = intent("market://search?q=pub:%s".format(name))
        return if (intent.resolveActivity(context.packageManager) != null) {
            intent
        } else {
            intent("https://play.google.com/store/search?q=%s".format(name))
        }
    }

    fun email(email: String, subject: String = "", message: String = ""): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)
        return intent
    }

    fun share(activity: Activity, message: String = ""): Intent {
        return ShareCompat.IntentBuilder.from(activity)
            .setType("text/plain")
            .setText(message)
            .createChooserIntent()
    }
}

/**
 * Use this intent whenever you launch an intent to somewhere outside the app.
 */
fun Intent.createChooserIntentIfDead(context: Context): Intent {
    return if (resolveActivity(context.packageManager) != null) {
        this
    } else {
        Intent.createChooser(this, null)
    }
}
