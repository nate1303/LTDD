
package com.uth.baitaptuan4th2.Screen

import androidx.annotation.DrawableRes
import com.uth.baitaptuan4th2.R

// Lớp cha OnBoard (OOP)
open class OnBoardPage(
    val chude: String,
    val noidung: String,
    @DrawableRes val image: Int
)

// Kế thừa 3 lớp con
class PageFirst : OnBoardPage(
    chude = "Easy Time Management",
    noidung ="With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first",
    image = R.drawable.logo_first
)

class PageSecond : OnBoardPage(
    chude = "Increase Work Effectiveness",
    noidung = "Time management and the determination of more important tasks will give you job statics better and always improve",
    image = R.drawable.logo_two
)

class PageThird : OnBoardPage(
    chude = "Reminder Notification",
    noidung = "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set",
    image = R.drawable.logo_three
)
