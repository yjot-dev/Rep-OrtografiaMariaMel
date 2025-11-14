package com.yjotdev.ortografiamariamel.application.navigation

import androidx.annotation.StringRes
import com.yjotdev.ortografiamariamel.R

enum class ViewRoutes(@StringRes val title: Int){
    Start(title = R.string.start_view),
    Register(title = R.string.register_view),
    Menu(title = R.string.units_view),
    TopicUnit1(title = R.string.button_unit_1),
    ActivityUnit1(title = R.string.button_unit_1),
    TopicUnit2(title = R.string.button_unit_2),
    ActivityUnit2(title = R.string.button_unit_2),
    TopicUnit3(title = R.string.button_unit_3),
    ActivityUnit3(title = R.string.button_unit_3),
    TopicUnit4(title = R.string.button_unit_4),
    ActivityUnit4(title = R.string.button_unit_4),
    Cover(title = R.string.button_cover),
}