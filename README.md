# Reto app empresarial
- Repositorio para hacer seguimiento del reto de la semana uno en el apartado backend

## Modelado DDD
[Link del modelo](https://drive.google.com/file/d/1_VptWwTI1YwK6NqEzjqvSOe6rDog6Tc4/view?usp=sharing)

![Imagen modelado](https://github.com/seb4stian69/RetoBackendSemanaUno/blob/main/Photos/DiagramaDDDRetoSemanaUno-Modelado%20-%20%5BTienda%5D.png)

## Test unitarios finalizados con 100% coverage
![Test completados](https://github.com/seb4stian69/RetoBackendSemanaUno/blob/main/Photos/ImagenPruebaTestCompletados100%25.jpeg)

## Actualizacion de entrega [Completado] - Realizada pasada las 23:59 viernes 20 de enero 2023
- Se completo satisfactoriamente el reto con las validaciones realizadas falta acoplar swagger a los endpoints para las pruebas pero es algo que se realizara luego de haber terminado el reto del frontend, el problema que tenia era que los constructores de los casos de uso quedaban vacios por la propiedad @NoArgsConstructor de lombok al quitarlos se pudo aplicar el caso de uso y por ende se logro tener el guardado de los eventos en la base de datos, al poder usar mis casos de uso pude usar todo el modelado de dominio y por ende las validaciones fueron realizadas gracias a que la logica implementada en el usecase activaba la logica que se implementaba en el ShopChange y esto solucionaba mi problema de integracion presentado el viernes.
- [Nota]: <b>Las 3 capas de la aplicacion estan completamente integradas [Domain, Bussines, Application]<b>

## Pasos para ejecutar el proyecto
1. Tener instalado docker y wsl.2
2. En la commandLine de ubuntu on windows correr el siguiente comando <code>docker pull rabbitmq</code>
3. Correr la imagen de docker y habilitar el puerto 5672 en las opciones
4. Abrir el proyecto en intellij y actualizar las dependencias de maven para luego ejecutarlo
5. Usar los endpoints que se encuentran en el apartado siguiente
- [Nota]: Swagger esta implementado pero solo funciona con algunos endpoints hasta el momento, para probar se recomienda hacer uso de postman en el proyecto se encuentra el archivo con la coleccion necesario para realizar las pruebas <b>Dentro de cada request esta el body necesario para realizarse</b>

## Endpoints

<code>

    | -> [ /users/list/{userID} <- GET                          | -> Complete - work

    | -> [ /shop/${shopID} <- GET                               | -> Complete - work
    | -> [ /shop/create <- POST                                 | -> Complete - work

    | -> [ /products/{shopID} <- GET                            | -> Complete - work
    | -> [ /Products/get?page={}&size={} <- GET Paginacion      | -> Complete - work
    | -> [ /products/{shopID}/{productID} <- GET By ID          | -> Complete - work
    | -> [ /products/create <- POST                             | -> Complete - work
    | -> [ /Products/update <- PUT                              | -> Complete - work
    | -> [ /products/delete <- DELETE                           | -> Complete - work

    | -> [ /buy/get <- GET                                      | -> Complete - work
    | -> [ /buy/get/{userid} <- GET By UserID                   | -> Complete - work
    | -> [ /buy/make <- POST                                    | -> Complete - work
 
</code>

    
