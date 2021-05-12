package com.arny.tester.data.models

import android.os.Parcel
import android.os.Parcelable

data class OutData(val id: Long = 0) : Parcelable {

    fun readFromParcel(parcel: Parcel){
        OutData(parcel.readLong())
    }

    constructor(parcel: Parcel) : this(parcel.readLong()) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OutData> {
        override fun createFromParcel(parcel: Parcel): OutData {
            return OutData(parcel)
        }

        override fun newArray(size: Int): Array<OutData?> {
            return arrayOfNulls(size)
        }
    }

}

