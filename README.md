## Instalación
### Requerimientos

- JDK 11+
- Maven  (Para generar el .jar)
- SCREEN

Este manual es para Ubuntu.

### Intalación
En la carpeta del proyecto ejecutar 
~~~
 mvn clean package
~~~
En la ruta **/target** se se encuentra el archivo **quasar-fire-apirest-0.0.1-SNAPSHOT.jar**
copiar el archivo al servidor donde se va a ejecutar.

En el servidor donde se va a publicar ejecutar los siguientes comandos:
~~~
 mvn clean package
~~~

![](tia.jpg)
