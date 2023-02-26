package com.example.shopingdemo.auth.ui

import android.content.Context
import android.widget.ArrayAdapter

class HintAdapter<T> : ArrayAdapter<T> {

    constructor(context:Context, resource:Int):super(context, resource) {
    }

    constructor(context:Context, resource:Int, textViewResourceId:Int):super(context, resource, textViewResourceId) {
    }

    constructor(context:Context, resource:Int, textViewResourceId:Int, objects:Array<T>):super(context, resource, textViewResourceId, objects) {
    }

    constructor(context:Context, resource:Int, objects:Array<T>):super(context, resource, objects) {
    }

    constructor(context:Context, resource:Int, objects:ArrayList<T>):super(context, resource, objects) {
    }

    constructor(context:Context, resource:Int, textViewResourceId:Int, objects: List<T>):super(context, resource, textViewResourceId, objects) {
    }

    override fun getCount():Int {
        // don't display last item. It is used as hint.
        val count = super.getCount();
        return if(count > 0) count - 1 else count;
    }
}