package ru.vk.project

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.telephony.PhoneNumberUtils
import android.widget.Toast

class MainModel : MainActionHandler {
    override fun dispatchAction(action: MainAction) {
        when (action) {
            is MainAction.CallFriendAction -> {
                handleCallFriendAction(action)
            }

            is MainAction.OpenSecondActivityAction -> {
                handleOpenSecondActivityAction(action)
            }

            is MainAction.ShareTextAction -> {
                handleShareTextAction(action)
            }
        }
    }

    private fun handleCallFriendAction(intent: MainAction.CallFriendAction) {
        if (!PhoneNumberUtils.isGlobalPhoneNumber(intent.tel)) {
            Toast.makeText(
                intent.context, intent.context.getString(R.string.incorrect_phone_number),
                Toast.LENGTH_LONG
            ).show()
            return
        }
        Intent(Intent.ACTION_DIAL).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            setData(intent.tel.fromTelToUri())
            withActivityNotFoundCatching(intent.context) {
                intent.context.startActivity(this)
            }
        }
    }

    private fun handleOpenSecondActivityAction(intent: MainAction.OpenSecondActivityAction) {
        Intent(intent.context, SecondActivity::class.java).apply {
            type = "text/plain"
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(Intent.EXTRA_TEXT, intent.text)
            intent.context.startActivity(this)
        }
    }

    private fun handleShareTextAction(intent: MainAction.ShareTextAction) {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(Intent.EXTRA_TEXT, intent.text)
            withActivityNotFoundCatching(intent.context) {
                intent.context.startActivity(this)
            }
        }
    }

    private inline fun withActivityNotFoundCatching(
        context: Context,
        crossinline tryAction: () -> Unit
    ) {
        try {
            tryAction()
        } catch (_: ActivityNotFoundException) {
            Toast
                .makeText(
                    context,
                    context.getString(R.string.activity_not_found),
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }
}