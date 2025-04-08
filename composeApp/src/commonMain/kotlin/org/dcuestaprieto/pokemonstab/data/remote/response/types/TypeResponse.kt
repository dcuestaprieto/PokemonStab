package org.dcuestaprieto.pokemonstab.data.remote.response.types

import kotlinx.serialization.Serializable
import org.dcuestaprieto.pokemonstab.domain.model.TypeModel

@Serializable
data class TypeResponse(
    /*
    si tenemos en nuestro modelo de datos un nombre de parametro diferente al del backend
    podemos ponerle el decorator @SerialName("nombre") donde nombre es el nombre del parametro
    que llega de la response, por ejemplo si del backend nos llega un parametro id pero nosotros lo queremos
    tener en la data class como paramId deberíamos hacer lo siguiente:
    @SerialName("id") val paramName:String
    */
    val name: String
) {
    fun toDomain(): TypeModel {
        return TypeModel(
            name = name,
            //TODO: change empty string
            imageUrl = ""
        )
    }
}