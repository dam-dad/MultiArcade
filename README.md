# MultiArcade
Juegos arcade retro

<p align="center"><img src="/githubImg/main.gif" width=""/></p>

## Instalador

 - [MultiArcade_v1.0](https://github.com/dam-dad/MultiArcade/releases)

## Miembros

 - [Enrique Luque Pérez](https://github.com/Engo660)
 - [Borja Díaz Ramos](https://github.com/Borja-29)
 - [Aarón Pérez Rodríguez](https://github.com/Skarnisk89)

## Introducción

Proyecto pensado por y para las generaciones más antiguas, recordando varios de los clásicos juegos de la época.
La aplicación contiene cuatro juegos diferentes, accesibles desde la interfaz principal por medio de un botón con sus respectivos nombres. 

# Interfaz

Interfaz.

<p align="center"><img src="/githubImg/interfazPrincipal.png" width=""/></p>

# Juegos

## Buscaminas
Juego diseñado en JavaFX.

<p align="center"><img src="/githubImg/buscaminas.png" width=""/></p>

### Guía de uso
#### Objetivo
El objetivo del juego, es desmarcar todas las casillas que no se traten de una mina.

#### Funcionamiento
Se generarán una cantidad fija de minas que se repartirán aleatoriamente entre las casillas. Utiliza el click izquierdo sobre una casilla para comprobar si el contenido de esta es una mina o no, o el click derecho una vez para marcar la casilla con una bandera, dos veces para marcarla con signo de interrogación y tres veces para remover la marca. No se podrá mostrar el contenido de una casilla marcada con una bandera o una interrogación.

En el caso de que la casilla se trate de una mina, la partida actual finalizará y mostrará una alerta para informar al usuario sobre su derrota. Esta alerta contendrá un pequeño menú con dos opciones con las que se podrá empezar una nueva partida o cerrar el juego. 

En el caso de que la casilla seleccionada no se trate de una mina, mostrará un número con la cantidad de minas adyacentes que servirá como guía para seleccionar correctamente la siguiente casilla.

 ## Pong

El conocido como primer juego de la historia, recreado en JavaFX.

<p align="center"><img src="/githubImg/pong.png" width=""/></p>

### Guía de uso
#### Objetivo
Anotar una mayor cantidad de puntos que el rival. Para anotar un punto, la bola de color blanco deberá abandonar la escena por el lado contrario al anotador.
#### Funcionamiento
Se Conrolará una barra de color verde que se sitúa en la parte derecha de la ventana. Esta barra se podrá desplazar hacia arriba utilizando las teclas "W" o "flecha UP", o hacia abajo utilizando las teclas "S" o "flecha DOWN". 

Cuando el juego inicie, una bola blanca aparecerá desde el centro de la superficie del juego. Para que la bola cambie de dirección y se dirija hacia el campo contrario, deberá colisionar con cualquiera de las barras laterales. Esta bola, incrementará en velocidad por cada rebote producido al golpear una barra.

Los puntos anotados se mostrarán en los números de colores que se muestran en cada uno de los campos.

Para iniciar una nueva partida pulse la tecla "N".

 ## Snake

El clásico juego de móvil, diseñado en JavaFX.

<p align="center"><img src="/githubImg/snake.png" width=""/></p>

 ### Guía de uso
#### Objetivo
Conseguir el mayor número de puntos posible. Los puntos se obtienen al pasar por encima de los círculos de colores con el cuadrado rojo.
#### Funcionamiento
El usuario controlará un cuadrado de color rojo con el que podrá desplazarse en cualquier dirección utilizando las teclas "W" o "flecha UP" para ir hacia arriba, "S" o "flecha DOWN" para ir hacia abajo, "A" o "flecha LEFT" para ir hacia la izquierda y "D" o "flecha RIGHT" para ir a la derecha.

El usuario deberá dirigirse hacia la posición de los círculos de colores, los cuales aparecerán en un lugar aleatorio, y lograr pasar por encima para poder sumar puntos. Por cada círculo obtenido, aparecerá un cuadrado de color verde que seguirá al cuadrado rojo y además se incrementará la velocidad de este.

Si el cuadrado controlado se sale de la superficie designada o choca con un cuadrado verde, la partida finalizará y se mostrará un mensaje de derrota: "Game Over". Una vez terminada la partida, es posible guardar la puntuación obtenida haciendo click en el botón "Export".
 
Para iniciar una nueva partida pulse la tecla "N".

  ## Memoria

Otro clásico, no de las maquinas recreativas, sino de la vida. Recreado en JavaFX utilizando imágenes como cartas

<p align="center"><img src="/githubImg/memoria.png" width=""/></p>

 ### Guía de uso
#### Objetivo
Realizar todas las parejas del tablero de juego.
#### Funcionamiento
Se generarán una serie de parejas de figuras que se mezclarán entre todas las casillas. Para poder descubrir una pareja, se tendrán que seleccionar dos casillas siendo la segunda casilla la que se crea que es la pareja de la primera. Si las dos seleccoinadas muestran la misma figura, se mantendrán reveladas, en caso contrario, ambas se volverán a ocultar.

Para revelar el contenido de una casilla, se utilizará el click izquierdo del ratón sobre un cuadrado azul. 

Para iniciar una nueva partida pulse la tecla "N".


