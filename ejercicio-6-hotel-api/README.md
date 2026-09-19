# reservas-hotel-api

API REST para administrar reservas de un hotel (Laboratorio VI - Ejercicio 6).

## Ejecutar
```
mvn spring-boot:run
```
Puerto por defecto: 8083 (base: /api/reservas)

## Endpoints
- POST   /api/reservas
- GET    /api/reservas          (soporta ?estado=)
- GET    /api/reservas/{id}
- PUT    /api/reservas/{id}
- PATCH  /api/reservas/{id}/cancelar
