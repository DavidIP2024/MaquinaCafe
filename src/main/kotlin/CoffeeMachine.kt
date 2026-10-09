

package org.example

/**
 * Singleton que controla los estados de la maquina de cafe.
 */
object CoffeeMachine {

    // Estado inicial de la maquina.
    var currentState: CoffeeMachineState = CoffeeMachineState.Inicio

    /**
     * Gestiona los cambios entre los estados de la maquina.
     */
    fun gestorEstados() {
        println("Estado actual: $currentState")

        when (val estado = currentState) {

            CoffeeMachineState.Inicio -> {
                println("Iniciando la maquina...")
                currentState = CoffeeMachineState.Idle()
            }

            is CoffeeMachineState.Idle -> {
                println("Maquina encendida. Selecciona un cafe.")
                currentState = CoffeeMachineState.SelectCoffee("Nescafe")
            }

            is CoffeeMachineState.SelectCoffee -> {
                println("Cafe seleccionado: ${estado.type}")
                currentState = CoffeeMachineState.PaymentCoffee(estado.type)
            }

            is CoffeeMachineState.PaymentCoffee -> {
                println("Procesando el pago del cafe ${estado.type}...")
                currentState = CoffeeMachineState.MakingCoffee(estado.type)
            }

            is CoffeeMachineState.MakingCoffee -> {
                println("Preparando cafe ${estado.type}...")
                Thread.sleep(2000)
                currentState = CoffeeMachineState.ServingCoffee(estado.type)
                println("Cafe listo: ${estado.type}")
            }

            is CoffeeMachineState.ServingCoffee -> {
                println("Sirviendo cafe ${estado.type}.")
                currentState = CoffeeMachineState.Idle()
            }

            is CoffeeMachineState.Apagado -> {
                println("La maquina esta apagada.")
                currentState = CoffeeMachineState.Idle()
            }

            is CoffeeMachineState.Error -> {
                println("Error en la maquina: ${estado.type}")
                currentState = CoffeeMachineState.Idle()
            }
        }

        println("Nuevo estado: $currentState")
    }
}