@file:Suppress("unused", "MemberVisibilityCanBePrivate", "QueryPermissionsNeeded")

package xyz.aprildown.tools.helper

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

    fun appStorePage(context: Context, name: String = context.packageName): Intent {
        val intent = intent("market://details?id=%s".format(name))
        return if (intent.resolveActivity(context.packageManager) != null) {
            intent
        } else {
            intent("https://play.google.com/store/apps/details?id=%s".format(name))
        }
    }

    fun appStoreDeveloperPage(context: Context, name: String): Intent {
        val intent = intent("market://search?q=pub:%s".format(name))
        return if (intent.resolveActivity(context.packageManager) != null) {
            intent
        } else {
            intent("https://play.google.com/store/search?q=%s".format(name))
        }
    }

    fun email(email: String, subject: String? = null, message: String? = null): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        if (subject != null) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (message != null) {
            intent.putExtra(Intent.EXTRA_TEXT, message)
        }
        return intent
    }

    fun share(context: Context, message: String): Intent {
        return ShareCompat.IntentBuilder(context)
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
