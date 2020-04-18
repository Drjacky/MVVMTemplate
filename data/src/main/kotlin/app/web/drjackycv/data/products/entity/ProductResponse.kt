package app.web.drjackycv.data.products.entity


import app.web.drjackycv.data.base.ResponseObject
import app.web.drjackycv.domain.products.entity.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: String,
    @SerializedName("title") val title: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("allergyInformation") val allergyInformation: String
) : ResponseObject<Product> {

    override fun toDomain(): Product = Product(
        id = id,
        price = price,
        title = title,
        imageUrl = imageUrl,
        description = description,
        allergyInformation = allergyInformation
    )

}