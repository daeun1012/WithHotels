package io.github.daeun1012.withhotels.data.local

//@Entity(tableName = "hotels")
data class Hotel(
//    @PrimaryKey @ColumnInfo(name = "id")
    val id: String,
    val name: String,
    val imageUrl: String = ""
)