package com.brunoaybar.demotests

import com.brunoaybar.demotests.model.AppConstants

class WiremockConstants : AppConstants{
    override val baseUrl: String get() = "http://10.0.2.2:8080/"
}

fun createWireMockConstant() : AppConstants = WiremockConstants()