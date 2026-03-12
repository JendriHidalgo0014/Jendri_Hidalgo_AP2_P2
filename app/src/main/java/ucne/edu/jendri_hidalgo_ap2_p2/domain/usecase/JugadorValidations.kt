package ucne.edu.jendri_hidalgo_ap2_p2.domain.usecase

private const val CAMPO_OBLIGATORIO = "Este campo es obligatorio"

data class JugadorValidationResult(
    val isValid: Boolean,
    val error: String? = null
)

fun validateNombres(value: String): JugadorValidationResult {
    if (value.isBlank()) return JugadorValidationResult(false, CAMPO_OBLIGATORIO)
    return JugadorValidationResult(true)
}

fun validateEmail(value: String): JugadorValidationResult {
    if (value.isBlank()) return JugadorValidationResult(false, CAMPO_OBLIGATORIO)
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches())
        return JugadorValidationResult(false, "Email no válido")
    return JugadorValidationResult(true)
}