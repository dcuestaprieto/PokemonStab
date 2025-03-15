package org.diego.pokemonstab.data.remote.response

import kotlinx.serialization.Serializable
import org.diego.pokemonstab.domain.model.TypeModel

@Serializable
data class TypeResponse(
    /*
    si tenemos en nuestro modelo de datos un nombre de parametro diferente al del backend
    podemos ponerle el decorator @SerialName("nombre") donde nombre es el nombre del parametro
    que llega de la response, por ejemplo si del backend nos llega un parametro id pero nosotros lo queremos
    tener en la data class como paramId deberíamos hacer lo siguiente:
    @SerialName("id") val paramName:String

     */
    val id: String,
    val name: String
) {
    fun toDomain(): TypeModel {
        return TypeModel(
            id = id,
            name = name
        )
    }
}