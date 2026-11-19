-- =====================================================================
--  UCO Kudos — Script 2 de 2: datos iniciales
--  Ejecutar DESPUÉS de 01_crear_base_datos_kudos.sql, una sola vez sobre la base vacía.
--  Los valores salen del muestreo del modelo de dominio (hojas M-).
--  Los identificadores se generan con UUID(), igual que lo haría la aplicación.
-- =====================================================================

SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `uco_kudos`;

START TRANSACTION;

-- 1. Roles institucionales (mutuamente excluyentes)
INSERT INTO `Rol` (`id`, `nombre`) VALUES
  (UUID(), 'Administrador'),
  (UUID(), 'Rector'),
  (UUID(), 'Coordinador Académico'),
  (UUID(), 'Coordinador de Facultad'),
  (UUID(), 'Coordinador de Programa'),
  (UUID(), 'Docente'),
  (UUID(), 'Estudiante');

-- 2. Estados de la cuenta de un usuario
INSERT INTO `EstadoCuenta` (`id`, `nombre`) VALUES
  (UUID(), 'Pendiente de aprobación'),
  (UUID(), 'Aprobada'),
  (UUID(), 'Activa'),
  (UUID(), 'Suspendida'),
  (UUID(), 'Desactivada'),
  (UUID(), 'Rechazada');

-- 3. Estados del ciclo de vida de un Kudo o una réplica
INSERT INTO `EstadoContenido` (`id`, `nombre`) VALUES
  (UUID(), 'Creado'),
  (UUID(), 'Visible'),
  (UUID(), 'Reportado'),
  (UUID(), 'En revisión'),
  (UUID(), 'Vetado');

-- 4. Estados de una revisión de moderación
INSERT INTO `EstadoRevision` (`id`, `nombre`) VALUES
  (UUID(), 'En curso'),
  (UUID(), 'Contenido vetado'),
  (UUID(), 'Contenido liberado');

-- 5. Motivos de reporte (la descripción del reporte es obligatoria cuando el motivo es "Otro")
INSERT INTO `Motivo` (`id`, `nombre`) VALUES
  (UUID(), 'Lenguaje ofensivo'),
  (UUID(), 'Acoso'),
  (UUID(), 'Discriminación'),
  (UUID(), 'Spam'),
  (UUID(), 'Información falsa'),
  (UUID(), 'Otro');

-- 6. Categorías de Kudos (una categoría inactiva se conserva solo para los Kudos históricos)
INSERT INTO `Categoria` (`id`, `nombre`, `descripcion`, `activa`) VALUES
  (UUID(), 'Liderazgo institucional', 'Reconoce a quien guía procesos o equipos que aportan al proyecto educativo de la UCO.', TRUE),
  (UUID(), 'Excelencia académica', 'Reconoce logros académicos sobresalientes en cursos, semilleros o investigaciones.', TRUE),
  (UUID(), 'Trabajo en equipo', 'Reconoce la colaboración, el apoyo a los compañeros y la construcción colectiva.', TRUE),
  (UUID(), 'Servicio a la comunidad', 'Reconoce aportes al bienestar de la comunidad universitaria y del Oriente antioqueño.', TRUE),
  (UUID(), 'Innovación', 'Reconoce ideas o proyectos nuevos que mejoran procesos de la universidad.', FALSE);

-- 7. Facultades
INSERT INTO `Facultad` (`id`, `nombre`) VALUES
  (UUID(), 'Facultad de Ingeniería'),
  (UUID(), 'Facultad de Ciencias de la Salud'),
  (UUID(), 'Facultad de Ciencias Sociales'),
  (UUID(), 'Facultad de Ciencias Económicas y Administrativas'),
  (UUID(), 'Facultad de Ciencias Agropecuarias');

-- 8. Programas académicos (cada uno pertenece a exactamente una facultad)
INSERT INTO `ProgramaAcademico` (`id`, `facultad`, `nombre`)
SELECT UUID(), f.`id`, v.`programa`
FROM (
  SELECT 'Facultad de Ingeniería' AS `facultad`, 'Ingeniería de Sistemas' AS `programa`
  UNION ALL SELECT 'Facultad de Ingeniería' AS `facultad`, 'Ingeniería Electrónica' AS `programa`
  UNION ALL SELECT 'Facultad de Ciencias de la Salud' AS `facultad`, 'Enfermería' AS `programa`
  UNION ALL SELECT 'Facultad de Ciencias Sociales' AS `facultad`, 'Psicología' AS `programa`
  UNION ALL SELECT 'Facultad de Ciencias Económicas y Administrativas' AS `facultad`, 'Administración de Empresas' AS `programa`
) v
JOIN `Facultad` f ON f.`nombre` = v.`facultad`;

-- 9. Administrador inicial
--    Solo un Administrador puede registrar Rector y otros Administradores (RN-094, RN-095),
--    así que la plataforma necesita uno desde el arranque. Son los datos del muestreo del dominio:
--    REEMPLÁCELOS por los de la persona real antes de usar la base fuera de pruebas.
INSERT INTO `Usuario` (`id`, `rol`, `estadoCuenta`, `nombre`, `apellido`, `correoInstitucional`, `celular`,
                       `correoVerificado`, `celularVerificado`, `fechaRegistro`)
SELECT UUID(), r.`id`, e.`id`, 'Sofía', 'Restrepo Álvarez', 'sofia.restrepo@uco.edu.co', '3104567821', TRUE, TRUE, '2026-01-05 08:00:00'
FROM `Rol` r
JOIN `EstadoCuenta` e ON e.`nombre` = 'Activa'
WHERE r.`nombre` = 'Administrador';

COMMIT;

-- Comprobación rápida: cantidad de filas por tabla sembrada
SELECT 'Rol' AS tabla, COUNT(*) AS filas FROM `Rol`
UNION ALL SELECT 'EstadoCuenta', COUNT(*) FROM `EstadoCuenta`
UNION ALL SELECT 'EstadoContenido', COUNT(*) FROM `EstadoContenido`
UNION ALL SELECT 'EstadoRevision', COUNT(*) FROM `EstadoRevision`
UNION ALL SELECT 'Motivo', COUNT(*) FROM `Motivo`
UNION ALL SELECT 'Categoria', COUNT(*) FROM `Categoria`
UNION ALL SELECT 'Facultad', COUNT(*) FROM `Facultad`
UNION ALL SELECT 'ProgramaAcademico', COUNT(*) FROM `ProgramaAcademico`
UNION ALL SELECT 'Usuario', COUNT(*) FROM `Usuario`;
