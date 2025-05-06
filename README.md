# PotionCraft

PotionCraft es un juego de simulación de alquimia en el que los jugadores gestionan un
negocio de creación de pociones. Como alquimista, combinarás ingredientes, administrarás
un inventario, interactuarás con comerciantes y gestionarás tu reputación basada en las
decisiones que tomes. El proyecto incluye funcionalidades para comprar y vender
ingredientes, calcular ganancias, y persistir datos utilizando una base de datos relacional
y ficheros binarios.

## Características

- **Creación de pociones:** Fabrica pociones combinando ingredientes y calcula su costo.
- **Inventario:** Administra ingredientes y pociones, actualizando las cantidades
  automáticamente.
- **Comerciantes:** Compra ingredientes de comerciantes, seleccionando aleatoriamente las
  unidades a adquirir.
- **Reputación:** Tu reputación como alquimista cambia según los efectos de las pociones.
  Se guarda en un fichero binario.
- **Economía:** Calcula el gasto al comprar ingredientes y las ganancias al vender
  pociones.
- **Persistencia de datos:** La base de datos guarda inventarios y JPA/Hibernate maneja la
  persistencia. La reputación se guarda en un fichero binario.

## Tecnologías utilizadas

- **Backend:** Java, JPA/Hibernate, RandomAccessFile.
- **Base de datos:** MySQL.
- **Patrones de diseño:** Singleton, DAO/Repository, Service Layer.

## Funcionamiento general

### Inicio del juego
El sistema te da la opción de iniciar una nueva partida o cargar una partida existente. La
reputación se carga desde un fichero binario si ya existe.

### Menú principal
Acceso a distintas funcionalidades, como crear pociones, comprar ingredientes, vender
pociones, y consultar estadísticas.

### Fabricación de pociones
Seleccionas una poción a fabricar dependiendo de los ingredientes disponibles. Los
ingredientes se descuentan del inventario al fabricar.

### Compra de ingredientes
Puedes comprar ingredientes de comerciantes seleccionados aleatoriamente. El costo total
se calcula automáticamente y se descuenta del oro disponible.

### Venta de pociones
Vende todas las pociones disponibles en tu inventario y obtén ganancias.

### Gestión de reputación
Cada acción (crear pociones) afecta tu reputación, que se actualiza dinámicamente y se
guarda para la próxima sesión.

## Instalacion
1. Clona este repositorio:
```bash 
git clone https://github.com/Quenuo/PotionCraft.git
```
En una terminal MySQL ejecutar el [script](script.sql).

Después en un IDE como IntelIJ o Eclipse donde se halla hecho el git clone ejecutar el
main y ya estara en funcionamiento la aplicación

## Licencia

Este proyecto está bajo una licencia propietaria. Consulte el archivo [LICENSE.txt](LICENSE.txt)
para más detalles.
