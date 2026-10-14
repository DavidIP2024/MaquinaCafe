package org.example

/**
 * Singleton que controla los estados de la maquina de cafe.
 */
object CoffeeMachine {

    private var currentState: CoffeeMachineState = CoffeeMachineState.Inicio

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
                println("Selecciona un cafe:")
                println("1. Nescafe")
                println("2. Cappuccino")
                println("3. Espresso")
                print("Elige una opcion: ")

                val opcion = readln()

                val cafe = when (opcion) {
                    "1" -> TipoCafe.NESCAFE
                    "2" -> TipoCafe.CAPPUCCINO
                    "3" -> TipoCafe.ESPRESSO
                    else -> null
                }

                if (cafe != null) {
                    currentState = CoffeeMachineState.SelectCoffee(cafe)
                } else {
                    println("Opcion no valida.")
                }
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
                println("Disfruta de tu cafe!")
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
        println()
    }
}