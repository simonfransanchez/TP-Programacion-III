# Uso de inteligencia artificial en el proyecto

### Contratos
- Deteccion de invariantes y algunas precondiciones que no habian sido tomadas en cuenta

### Misiones
- Interpretación de la consigna en algunas ambigüedades
- Correccion de errores en el patron Template Method
- Ayuda en el diseño de la clase Informe
- Ayuda para la decision sobre el diseño de la relacion entre las misiones y el asistente
- Propuesta de agregar el metodo estaDisponible() en la interfaz State
- Revision de errores de compilacion en la clase Asistente

### Pruebas
- Generación de algunos casos de prueba

### Vista de Control de la Nave
- Diseño de la sección con MVC y Observer: VistaControlNave, ControladorControlNave, PanelRecursos, PanelBitacora, PanelInformes y PanelTablero
- Deteccion de detalles que requeria el modelo para ser observable

### Hilos en la vista
- La misión se lanza en un SwingWorker para no congelar la ventana y se deshabilita el botón mientras corre
- El tablero se copia dentro de actualizar() y se dibuja con repaint(), para evitar carreras con Destino
- Los observadores se registran antes de iniciar cualquier misión, porque Observable no está sincronizado
- Los paneles actualizan con invokeLater

### Combate entre naves
- El combate usa Double Dispatch con ResultadoCombate y la tabla del enunciado, sin instanceof

### Asistente
- Métodos con contratos: combatirContra e interactuarConElementoActual
- Agregación de dos constructores debido a un bug

### Misiones
- Arreglo de malas decisiones de diseño junto a agregacion de atributos y metodos necesarios para el informe de cada mision