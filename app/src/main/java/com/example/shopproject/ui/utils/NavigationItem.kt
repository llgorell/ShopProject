package com.example.shopproject.ui.utils

import com.example.shopproject.R

open class NavigationItem(var route : String,var icon : Int,var title : String) {

    object home : NavigationItem("home", R.drawable.ic_baseline_home_24,"Home")
    object search : NavigationItem("home", R.drawable.ic_baseline_search_24,"Home")
    object profile : NavigationItem("home", R.drawable.ic_baseline_person_outline_24,"Home")
}