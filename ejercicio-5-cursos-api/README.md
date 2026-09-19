# cursos-api

API REST para administrar cursos universitarios (Laboratorio VI - Ejercicio 5).

## Ejecutar
```
mvn spring-boot:run
```
Puerto por defecto: 8082 (base: /api/cursos)

## Endpoints
- POST   /api/cursos
- GET    /api/cursos            (soporta ?estado=)
- GET    /api/cursos/codigo/{codigo}
- PUT    /api/cursos/{id}
- DELETE /api/cursos/{id}
