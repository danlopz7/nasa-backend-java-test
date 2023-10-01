
																	JAVA Nasa Technical Test ✍
										
Herramientas a instalar 
> Instalar Java versión 11.
 
> Instalar Postgresql como servidor de DB.

> Instalar PGADMIN o tu cliente DB favorito.
 
> Instalar POSTMAN como cliente API  o tu cliente favorito.

> Instalar Spring Tool Suit 4 (recomendado) o tu IDE favorito.
 
> Instalar GIT. 

Verificar que todas las herramientas anteriormente hayan sido instaladas de forma correcta. 

## Objetivos

> Desarrollar un aplicativo que ejecute una tarea automática cada minuto y permita el consumo de una API de la National Aeronautics and Space Administration - NASA para que los datos obtenidos se almacene en la DB 

> Desarrollar una API que pueda consumir los datos guardados en la DB y poder mostrarlo en un cliente POSTMAN. 


## Estructura proyecto

En este repositorio, en la rama Master, encontraras un proyecto utilizando el framework Spring Boot 2.7.7, que sigue la arquitectura hexagonal y DDD (Domain-driven design), el cuál busca la independecia de la logica de negocio y otras clases de uso rutinario o externas. 
Actualmente el proyecto cuenta con una seria de package creados los cuales son:
1. clienterest 
2. dto 
3. entites
4. repository 
5. restcontroller 
6. taskscheduler 

Se explica a continuación cada una de ellas:
> clienterest:
Incluir todo código que haga referencia al consumo del  servicio externo NASA. 

> dto: 
Incluir todas aquellas clases que seran los modelos. 

> entites:
Incluir todas aquellas clases que haran referencia a las tablas de DB.

> repository:
Incluir todo código que haga referencia a la persistencia de datos contra la DB.

> restcontroller:
Incluir todo código que haga referencia a la creación de controladores y APIS que serán consumidasm por POSTMAN

> taskscheduler:
Incluir todo código que haga referencia al cronometro que se disparara de forma asíncrona para ejecutar el consumo de servicio externo NASA

Nota: 
- Se aclara que usted puede crear otros packages, no utilizar algunos de los anteriores explicados si así lo desea; pero siempre y cuando se respete la arquitectura hexagonal. 
- A nivel del archivo POM.XML se han instalado las dependencias necesarias para el uso y desarrollo del proyecto, pero si usted considera conveniente instalar una dependencia extra es libre de hacerlo. 
- Se crean ciertas clases e interfaces a nivel del proyecto, esto como archivos pruebas, pueda hacer uso de ellos o no. 

## API NASA

Se utilizara una API expuesta por la NASA, la cuál consiste en realizar busquedas mediante nombre y esta nos retornara todos los registros que convergan. Para mas información y detalle, puede revisar el siguiente enlace [check out the NASA-API documentation here](https://images.nasa.gov/docs/images.nasa.gov_api_docs.pdf).

La API del tipo GET que consumiremos sera la siguiente
> https://images-api.nasa.gov/search?q={PARAMETRO_BUSQUEDA}

Podemos observar que la estructura de la API recibe un QueryParams {q}, el cuál debe capturar el valor por donde realizar la búsqueda, para tal caso el valor que se ocupara sera "apollo 2011", o si en dado caso desea utilizar otras palabras de busqueda podra hacerlo e indicarlas el día de la entrevista. 

Ejemplo de como se sería la API de busqueda con un paramentro indicado. 
> https://images-api.nasa.gov/search?q=apollo%2011

## Estructura API NASA 
En el documento compartido [check out the NASA-API documentation here](https://images.nasa.gov/docs/images.nasa.gov_api_docs.pdf). se ejemplica como es la estructura que se recibe por parte de la API NASA, a continuación se detalla una estructura ejemplo: 
```
{
    "collection": {
        "version": "1.0",
        "href": "http://images-api.nasa.gov/search?q=Apollo%2011%20Overview",
        "items": [
            {
                "href": "https://images-assets.nasa.gov/video/Apollo 11 Overview/collection.json",
                "data": [
                    {
                        "center": "HQ",
                        "title": "Apollo 11 Overview",
                        "keywords": [
                            "Apollo 11",
                            "Moon"
                        ],
                        "nasa_id": "Apollo 11 Overview",
                        "date_created": "2013-05-15T00:00:00Z",
                        "media_type": "video",
                        "description": "Video highlights from the historic first manned landing on the moon."
                    }
                ],
                "links": [
                    {
                        "href": "https://images-assets.nasa.gov/video/Apollo 11 Overview/Apollo 11 Overview~thumb.jpg",
                        "rel": "preview",
                        "render": "image"
                    },
                    {
                        "href": "https://images-assets.nasa.gov/video/Apollo 11 Overview/Apollo 11 Overview.srt",
                        "rel": "captions"
                    }
                ]
            }
        ],
        "metadata": {
            "total_hits": 1
        }
    }
}
```

El aplicativo a desarrollar se resumen en tres objetivos principales:
### Objetivo 1
Crear una tabla a nivel de DB la cual contenga los campos 
- href
- center
- title
- nasa_id

#### Importante
- A la vez se necesita que exista un campo de auditoría para guardar la fecha y hora de creación del registro, el campo puede ser llamado como usted desee. 
- No olvidar el campo ID y que sea autoincrementable. Queda a su elección de que manera hara que el campo ID sea autoincrementable. 

### Objetivo 2
Que se pueda consumir el servicio  explicada en el apartado API NASA; esta tarea debe realizarse de manera automática, ejecutarse cada 1 minuto y almacenar los valores obtenidos de la respuesta a nivel de DB. Los valores a tener que guardar serán los sguientes: 
 1. href : Ubicada al mismo nivel de la propiedad data (Se indica con una flecha)
 ```
  "items": [
            {
             --->   "href": "https://images-assets.nasa.gov/video/Apollo 11 Overview/collection.json",
                "data": [
                    {
 ```
 2. center: Ubicada dentro de la propiedad data (Se indica con una flecha)
 ```
  "data": [
                    {
             --->       "center": "HQ",
                        "title": "Apollo 11 Overview",
                        "keywords": [
                            "Apollo 11",
                            "Moon"
                        ],
                        "nasa_id": "Apollo 11 Overview",
                        "date_created": "2013-05-15T00:00:00Z",
                        "media_type": "video",
                        "description": "Video highlights from the historic first manned landing .."
                    }
                ]
 ```
 3. title: Ubicada dentro de la propiedad data (Se indica con una flecha) 
  ```
  "data": [
                    {
                        "center": "HQ",
            --->        "title": "Apollo 11 Overview",
                        "keywords": [
                            "Apollo 11",
                            "Moon"
                        ],
                        "nasa_id": "Apollo 11 Overview",
                        "date_created": "2013-05-15T00:00:00Z",
                        "media_type": "video",
                        "description": "Video highlights from the historic first manned landing .."
                    }
                ]
 ```
 4. nasa_id: Ubicada dentro de la propiedad data (Se indica con una flecha) 
  ```
  "data": [
                    {
                        "center": "HQ",
                        "title": "Apollo 11 Overview",
                        "keywords": [
                            "Apollo 11",
                            "Moon"
                        ],
              --->      "nasa_id": "Apollo 11 Overview",
                        "date_created": "2013-05-15T00:00:00Z",
                        "media_type": "video",
                        "description": "Video highlights from the historic first manned landing .."
                    }
                ]
 ```

#### Importante
- La propiedad "items" es un arreglo
- La propiedad "data" es un arreglo y se busca que solo se utilice la primera posición de dicha propiedad (extraer valores y guardar en DB)

 
### Objetivo 3
Crear una API del tipo GET con la cual podamos extraer los datos guardado en la DB y poder visualizarlos en POSTMAN en forma de listado, estos deben ser listado de manera descendente por ID. 

									
