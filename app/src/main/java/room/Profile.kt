package room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile (
    @PrimaryKey
    val name : String,
    val age : String,
    val image : String,
    val statusAvailabilty : Boolean,
    val status : String
    )