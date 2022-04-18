package com.example.exam5.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.exam5.utils.Converters
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Url


@Parcelize
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    @ColumnInfo
    val firstName: String?,
    val lastName: String?,
    val nationalCode: String?,
//    @TypeConverters(Converters::class)
//   val hobbies: List<String?>?
    val image: String? = null
) : Parcelable
