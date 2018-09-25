# MutantDetectorAPI

## ¿Qué es?
MutantDetectorAPI es una una API REST desarrollada para el proyecto MutantDetector

## ¿Cómo funciona?
La API inlcuye dos servicios posibles, a saber:

  * **"/mutant/"**: Servicio HTTP POST de formato JSON, a través del cual se consulta si un humano es mutante o no.
  Es importante aclarar que este servicio a démas almacena el resultado junto con la secuencia de ADN para cada request recibido.
  
      ```
      {
         "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
      }
      ```
      
    En caso de verificar un mutante, un HTTP 200-OK, en caso contrario un 403-Forbidden
  
  
  * **"/stats"**: Servicio HTTP GET de formato JSON, a través del cual se consultan las estadísticas de las verificaciones de ADNs realizadas hasta el momento. 
      ```
      {"count_mutant_dna":40,"count_human_dna":100,"ratio":0.4}
      ```
      
## Ejecución

Ejecutar el comando *java -jar MutantDetectorAPI.jar* en una termina, estando en el directorio donde se encuentra el programa.

### Configuración

* El directorio de configuración *./config* contiene dos archivos necesarios para la ejecución de la aplicación, a saber:
    * mutantdetectorapi.cfg
    * log4j.properties
