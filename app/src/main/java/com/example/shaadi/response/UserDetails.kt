package com.example.shaadi.response

import java.util.*

data class UserDetails(val results : List<UserDetail>, val info :InfoDetail)

data class UserDetail(val gender : String, val name: NameDetail, val location: LocationDetail,val email: String,
                      val  login: LoginCredentails, val dob : BirthTime, val registered : RegisteredDetail,
                      val phone : String, val cell : String, val id : IdDetails, val picture : PictureDetail, val nat : String)

data class NameDetail(val title : String, val first : String, val last : String)
data class LocationDetail(val street : StreetDetail, val city : String, val state : String, val counry : String, val postCode : String,
                          val coordinates : Coordinates, val timeZone: TimeZoneDetail)
 data class StreetDetail(val number : Int, val name: String)
 data class Coordinates(val latitude : String, val longitude : String)
 data class TimeZoneDetail(val offset : String, val description : String)
 data class LoginCredentails(val uuid : String, val username : String, val password : String, val salt : String, val md5 : String,
                             val sha1 : String, val sha256 : String)
 data class BirthTime(val date : String, val age : Int)
 data class RegisteredDetail(val  date: String, val age: Int)
 data class IdDetails(val name: String, val value: String)
 data class PictureDetail(val large : String, val medium : String, val thumbnail : String)
data class InfoDetail(val  seed : String, val  results : Int, val page : Int, val version : String)