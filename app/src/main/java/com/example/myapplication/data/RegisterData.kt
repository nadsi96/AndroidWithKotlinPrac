package com.example.myapplication.data

import android.os.Parcel
import android.os.Parcelable

// 회원가입시 입력한 데이터 저장
data class RegisterData(
    val id: String?,
    val pw: String?,
    val name: String?,
    val gender: Int?,
    val birthYear: Int?,
    val birthMonth: Int?,
    val birthDate: Int?,
    val email: String?,
    val phoneNum: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id ?: "null")
        parcel.writeString(pw ?: "null")
        parcel.writeString(name ?: "null")
        parcel.writeInt(gender ?: -1)
        parcel.writeInt(birthYear ?: 0)
        parcel.writeInt(birthMonth ?: 0)
        parcel.writeInt(birthDate ?: 0)
        parcel.writeString(email ?: "null")
        parcel.writeString(phoneNum ?: "01000000000")
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegisterData> {
        override fun createFromParcel(parcel: Parcel): RegisterData {
            return RegisterData(parcel)
        }

        override fun newArray(size: Int): Array<RegisterData?> {
            return arrayOfNulls(size)
        }
    }

    fun getBirthdayToString() = "${String.format("%04d", birthYear ?: 0)}-${String.format("%02d", birthMonth ?: 0)}-${String.format("%02d", birthDate ?: 0)}"
    fun getGenderToString() =
        when (gender) {
            0 -> "남자"
            1 -> "여자"
            else -> "미지정"
        }
}
