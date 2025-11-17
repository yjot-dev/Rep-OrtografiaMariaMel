package com.yjotdev.ortografiamariamel.application.navigation

import androidx.annotation.StringRes
import com.yjotdev.ortografiamariamel.R

enum class ViewRoutes(@StringRes val title: Int){
    Start(title = 0),
    Register(title = R.string.register_view),
    Menu(title = R.string.units_view),
    TopicUnit1(title = R.string.button_unit_1),
    ActivityUnit1(title = 0),
    TopicUnit2(title = R.string.button_unit_2),
    ActivityUnit2(title = 0),
    TopicUnit3(title = R.string.button_unit_3),
    ActivityUnit3(title = 0),
    TopicUnit4(title = R.string.button_unit_4),
    ActivityUnit4(title = 0),
    Cover(title = R.string.button_cover),
}