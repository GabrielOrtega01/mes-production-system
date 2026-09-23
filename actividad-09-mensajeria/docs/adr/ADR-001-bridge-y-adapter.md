# ADR-001: Separar prioridades de canales con Bridge y envolver los proveedores con Adapter

- **Estado:** aceptada
- **Fecha:** 23 de septiembre de 2026
- **Autores:** Gabriel Augusto Ortega Martínez, Jhonathan David Rojas Molina
- **Contexto académico:** Patrones de Software (UTS), Actividad 9 — Plataforma de mensajería con canales y prioridades

## Contexto

La plataforma envía notificaciones con cuatro prioridades (crítica, alta, normal y baja) por cuatro canales (email, SMS, push y webhook). La prioridad decide *cuántos* canales se usan y si hay reintentos; el canal decide *cómo* se entrega el mensaje. Son dos cosas que cambian por razones distintas y a ritmos distintos: mañana puede aparecer una prioridad nueva por una decisión comercial, o un canal nuevo porque se cambió de proveedor.

Las dos dimensiones se multiplican entre sí. Si se modelaran con herencia, cuatro prioridades por cuatro canales darían dieciséis clases, y cada canal o prioridad adicional dispararía ese número.

Además, los cuatro proveedores tienen APIs incompatibles: SendGrid recibe asunto y cuerpo HTML, Twilio recibe texto plano y responde con un código HTTP, FCM trabaja con mapas de datos y el webhook genérico espera un JSON. No podemos modificar esas librerías.

## Decisión

Aplicar dos patrones a la vez, cada uno en el eje que le corresponde:

1. **Bridge** entre prioridades y canales. `EstrategiaEnvio` es la abstracción y mantiene una referencia a una lista de `CanalMensajeria`; `EnvioCritico`, `EnvioAlto`, `EnvioNormal` y `EnvioDiferido` son abstracciones refinadas. Las dos jerarquías se extienden por separado.
2. **Adapter** en cada canal. `AdaptadorSendGrid`, `AdaptadorTwilio`, `AdaptadorFcm` y `AdaptadorWebhook` implementan `CanalMensajeria` y traducen el mensaje —y también los errores— al formato de cada proveedor.

La interfaz `CanalMensajeria` es el punto donde se encuentran ambos patrones: es el *implementador* del Bridge y, al mismo tiempo, la *interfaz objetivo* del Adapter.

## Alternativas consideradas

| Alternativa | Por qué se descartó |
|---|---|
| Herencia combinada (`EnvioCriticoEmail`, `EnvioCriticoSms`, …) | Produce 16 clases hoy y crece de forma multiplicativa. Cada canal nuevo obliga a crear una clase por prioridad. |
| Solo Adapter, con la prioridad resuelta con condicionales | Los `if` por prioridad quedarían dentro del código de envío, de modo que agregar una prioridad obligaría a tocar los canales. Viola las restricciones 1 y 3. |
| Solo Bridge, llamando directamente a las APIs de los proveedores | No resuelve la incompatibilidad de las APIs: la abstracción tendría que conocer la firma de cada proveedor. Viola las restricciones 1 y 5. |
| Strategy en lugar de Bridge | Strategy intercambia un algoritmo dentro de un mismo contexto. Aquí no hay un contexto único: hay dos jerarquías que deben crecer en paralelo, que es justamente el caso del Bridge. |

## Consecuencias

**A favor**

- Agregar una prioridad es crear una subclase de `EstrategiaEnvio`; no se toca ningún canal (restricción 3).
- Agregar un canal es crear un adaptador; no se toca ninguna prioridad (restricción 4). Se verificó agregando Telegram al final del ejercicio.
- Las estrategias solo dependen de la interfaz `CanalMensajeria` (restricción 1) y los canales no mencionan la prioridad (restricción 2). Ambas cosas se comprueban por reflexión en `PruebaExtensibilidad`.
- Los errores de cada proveedor se traducen a un único tipo, `EnvioFallidoException`, así que la política de reintentos vive en un solo lugar.
- Cambiar de proveedor en un canal no afecta la lógica de prioridad: basta con otro adaptador.

**En contra**

- Hay más clases pequeñas que en una solución directa, y para entender un envío hay que seguir dos saltos: prioridad y canal.
- Los adaptadores agregan una capa de traducción que debe mantenerse cuando el proveedor cambie su API.
- La lista de canales se inyecta en la estrategia, de modo que quien arma la aplicación decide el orden en que se recorren los canales.

## Referencia

Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (2003). *Patrones de diseño: Elementos de software orientado a objetos reutilizable* (pp. 131, 140-141). Pearson Educación.
