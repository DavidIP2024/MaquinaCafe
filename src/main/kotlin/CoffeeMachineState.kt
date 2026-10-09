
package org.example

/**
 * Representa los estados posibles de la maquina de cafe.
 */
sealed class CoffeeMachineState {

    object Inicio : CoffeeMachineState()

    data class Idle(
        val timestamp: Long = System.currentTimeMillis()
    ) : CoffeeMachineState()

    data class MakingCoffee(val type: String) : CoffeeMachineState()

    data class SelectCoffee(val type: String) : CoffeeMachineState()

    data class PaymentCoffee(val type: String) : CoffeeMachineState()

    data class ServingCoffee(val type: String) : CoffeeMachineState()

    data class Apagado(val type: String) : CoffeeMachineState()

    data class Error(val type: String) : CoffeeMachineState()
}