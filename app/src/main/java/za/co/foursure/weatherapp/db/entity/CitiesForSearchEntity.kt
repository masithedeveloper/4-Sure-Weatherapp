package za.co.foursure.weatherapp.db.entity

import android.os.Parcelable
import android.text.SpannableString
import androidx.room.*
import za.co.foursure.weatherapp.domain.model.HitsItem
import za.co.foursure.weatherapp.utils.extensions.bold
import za.co.foursure.weatherapp.utils.extensions.italic
import za.co.foursure.weatherapp.utils.extensions.plus
import za.co.foursure.weatherapp.utils.extensions.spannable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "CitiesForSearch")
data class CitiesForSearchEntity(
    @ColumnInfo(name = "administrative")
    val administrative: String?,
    @ColumnInfo(name = "Country")
    val country: String?,
    @Embedded
    val coord: CoordEntity?,
    @ColumnInfo(name = "fullName")
    val name: String?,
    @ColumnInfo(name = "county")
    val county: String?,
    @ColumnInfo(name = "importance")
    val importance: Int?,
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String
) : Parcelable {
    @Ignore
    constructor(hitsItem: HitsItem?) : this(
        country = hitsItem?.country,
        importance = hitsItem?.importance,
        administrative = hitsItem?.administrative?.first(),
        coord = CoordEntity(hitsItem?.geoloc),
        name = hitsItem?.localeNames?.first(),
        county = hitsItem?.county?.first(),
        id = hitsItem?.objectID.toString()
    )

    fun getFullName(): SpannableString {
        return spannable {
            bold(name ?: "").plus(", ") +
                bold(county ?: "").plus(", ") +
                italic(administrative ?: "").plus(", ") +
                italic(country ?: "")
        }
    }
}
