package ru.vk.project

import androidx.core.net.toUri

fun String.fromTelToUri() = "tel:$this".toUri()