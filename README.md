# Reto semana uno
- Repositorio para hacer seguimiento del reto de la semana uno en el apartado backend <b>Leer antes de calificar</b>

# Enlace para acceder a la rama con los errores solucionados 
- [Enlace de la rama](https://github.com/seb4stian69/RetoBackendSemanaUno/tree/refinacion)

## Modelado DDD
[Link del modelo](https://drive.google.com/file/d/1_VptWwTI1YwK6NqEzjqvSOe6rDog6Tc4/view?usp=sharing)

![Imagen modelado](https://github.com/seb4stian69/RetoBackendSemanaUno/blob/main/Photos/DiagramaDDDRetoSemanaUno-Modelado%20-%20%5BTienda%5D.png)

## Test unitarios finalizados con 100% coverage
![Test completados](https://github.com/seb4stian69/RetoBackendSemanaUno/blob/main/Photos/ImagenPruebaTestCompletados100%25.jpeg)

## Resumen de lo realizado y alcanzado hasta 9pm del viernes 20 de enero
- Se realizo un modelado de dominio con sus respectivos objetos de valor, entidades, comandos, eventos y agregados se realizaron los test basandose en este modelo y de los cuales se consiguieron 5 de 5 pruebas necesarias, intente aplicar esta infraestructura a la capa de dominio y no lo alcanze a implemenar todo a tiempo debido a falta de conocimientos para realizarlo de manera mas rapida, las validaciones y procedimientos se encuentran en los casos de uso y modelado de dominio pero al no haber una correcta integracion no pudo ser aplicado sin embargo para tratar de conseguir algo parecido a MVP use los conocimientos adquiridos de springboot y project reactor hasta el momento

## Actualizacion de entrega [Completado] - Realizada pasada las 23:59 viernes 20 de enero 2023
- Se completo satisfactoriamente el reto con las validaciones realizadas falta acoplar swagger a los endpoints para las pruebas pero es algo que se realizara luego de haber terminado el reto del frontend, el problema que tenia era que los constructores de los casos de uso quedaban vacios por la propiedad @NoArgsConstructor de lombok al quitarlos se pudo aplicar el caso de uso y por ende se logro tener el guardado de los eventos en la base de datos, al poder usar mis casos de uso pude usar todo el modelado de dominio y por ende las validaciones fueron realizadas gracias a que la logica implementada en el usecase activaba la logica que se implementaba en el ShopChange y esto solucionaba mi problema de integracion presentado el viernes.
- [Nota]: Las 3 capas de la aplicacion estan completamente integradas [Domain, Bussines, Application] se puede confirmar trayendo los cambios de la rama refinacion

### Deudas tecnicas hasta las 9pm 01/20/2023
1. No se registraron las validaciones realizadas en el dominio y caso de uso a la api
2. No se descuenta el producto al realizar compra

## Pasos para ejecutar el proyecto
1. Tener instalado docker y wsl.2
2. En la commandLine de ubuntu on windows correr el siguiente comando docker pull rabbitmq
3. Abrir el proyecto en intellij y actualizar las dependencias de maven para luego ejecutarlo
4. Usar los endpoints que se encuentran en el apartado siguiente
[Nota]: Swagger esta implementado pero solo funciona con algunos endpoints hasta el momento, para probar se recomiendo hacer uso de postman en el proyecto se encuentra el archivo con la coleccion necesario para realizar las pruebas

## Endpoints

<code>

    | -> [ /users/list/{userID} <- GET                          | -> Complete - work
    | -> [ /shop/${shopID} <- GET                               | -> Complete - work
    | -> [ /shop/create <- POST                                 | -> Complete - work [products void]
    | -> [ /products <- GET                                     | -> Complete - work
    | -> [ /Products/get?page={}&size={} <- GET Paginacion      | -> Complete - work
    | -> [ /products/{productID} <- GET By ID                   | -> Complete - work
    | -> [ /products/create <- POST                             | -> Complete - work
    | -> [ /Products/update/{productid} <- PUT                  | -> Complete - work
    | -> [ /products/delete/{shopID}/{productID} <- DELETE      | -> Complete - work
    | -> [ /buy/get <- GET                                      | -> Complete - work
    | -> [ /buy/get/{userid} <- GET By UserID                   | -> Complete - work
    | -> [ /buy/make <- POST                                    | -> se registra la compra pero [No se descuentan los productos]

</code>
