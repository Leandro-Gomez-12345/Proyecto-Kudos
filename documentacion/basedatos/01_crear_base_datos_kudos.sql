-- =====================================================================
--  UCO Kudos — Script 1 de 2: creación de la base de datos y sus tablas
--  Motor: MySQL 8.0.16 o superior 
-- =====================================================================

SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `uco_kudos`
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE `uco_kudos`;

-- ---------------------------------------------------------------------
-- Rol (catálogo)
-- ---------------------------------------------------------------------
CREATE TABLE `Rol` (
  `id` CHAR(36) NOT NULL,
  `nombre` VARCHAR(30) NOT NULL,
  CONSTRAINT `PK_Rol` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_rol_nombre` UNIQUE (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- EstadoCuenta (catálogo)
-- ---------------------------------------------------------------------
CREATE TABLE `EstadoCuenta` (
  `id` CHAR(36) NOT NULL,
  `nombre` VARCHAR(30) NOT NULL,
  CONSTRAINT `PK_EstadoCuenta` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_estadoCuenta_nombre` UNIQUE (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- EstadoContenido (catálogo)
-- ---------------------------------------------------------------------
CREATE TABLE `EstadoContenido` (
  `id` CHAR(36) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  CONSTRAINT `PK_EstadoContenido` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_estadoContenido_nombre` UNIQUE (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- EstadoRevision (catálogo)
-- ---------------------------------------------------------------------
CREATE TABLE `EstadoRevision` (
  `id` CHAR(36) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  CONSTRAINT `PK_EstadoRevision` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_estadoRevision_nombre` UNIQUE (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- Motivo (catálogo)
-- ---------------------------------------------------------------------
CREATE TABLE `Motivo` (
  `id` CHAR(36) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  CONSTRAINT `PK_Motivo` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_motivo_nombre` UNIQUE (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- Categoria (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `Categoria` (
  `id` CHAR(36) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(300) NOT NULL,
  `activa` BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT `PK_Categoria` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_categoria_nombre` UNIQUE (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- Facultad (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `Facultad` (
  `id` CHAR(36) NOT NULL,
  `nombre` VARCHAR(70) NOT NULL,
  CONSTRAINT `PK_Facultad` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_facultad_nombre` UNIQUE (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- Usuario (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `Usuario` (
  `id` CHAR(36) NOT NULL,
  `rol` CHAR(36) NOT NULL,
  `estadoCuenta` CHAR(36) NOT NULL,
  `nombre` VARCHAR(60) NOT NULL,
  `apellido` VARCHAR(60) NOT NULL,
  `correoInstitucional` VARCHAR(100) NOT NULL,
  `celular` VARCHAR(10) NOT NULL,
  `correoVerificado` BOOLEAN NOT NULL DEFAULT FALSE,
  `celularVerificado` BOOLEAN NOT NULL DEFAULT FALSE,
  `fechaRegistro` DATETIME NOT NULL,
  CONSTRAINT `PK_Usuario` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_usuario_correoInstitucional` UNIQUE (`correoInstitucional`),
  CONSTRAINT `UQ_usuario_celular` UNIQUE (`celular`),
  KEY `IX_usuario_rol` (`rol`),
  KEY `IX_usuario_estadoCuenta` (`estadoCuenta`),
  CONSTRAINT `FK_usuario_rol` FOREIGN KEY (`rol`) REFERENCES `Rol` (`id`),
  CONSTRAINT `FK_usuario_estadoCuenta` FOREIGN KEY (`estadoCuenta`) REFERENCES `EstadoCuenta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- ProgramaAcademico (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `ProgramaAcademico` (
  `id` CHAR(36) NOT NULL,
  `facultad` CHAR(36) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  CONSTRAINT `PK_ProgramaAcademico` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_programaAcademico_nombre` UNIQUE (`nombre`),
  KEY `IX_programaAcademico_facultad` (`facultad`),
  CONSTRAINT `FK_programaAcademico_facultad` FOREIGN KEY (`facultad`) REFERENCES `Facultad` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- Auditoria (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `Auditoria` (
  `id` CHAR(36) NOT NULL,
  `usuario` CHAR(36) NOT NULL,
  `accion` VARCHAR(100) NOT NULL,
  `objetoAfectado` VARCHAR(30) NOT NULL,
  `registroAfectado` CHAR(36) NOT NULL,
  `detalle` VARCHAR(200) NOT NULL,
  `fechaHora` DATETIME NOT NULL,
  CONSTRAINT `PK_Auditoria` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_auditoria_usuario_accion_registroAfectado_fechaHora` UNIQUE (`usuario`, `accion`, `registroAfectado`, `fechaHora`),
  CONSTRAINT `FK_auditoria_usuario` FOREIGN KEY (`usuario`) REFERENCES `Usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- CoordinacionFacultad (entidad asociativa)
-- ---------------------------------------------------------------------
CREATE TABLE `CoordinacionFacultad` (
  `id` CHAR(36) NOT NULL,
  `usuario` CHAR(36) NOT NULL,
  `facultad` CHAR(36) NOT NULL,
  `fechaInicio` DATE NOT NULL,
  `fechaFin` DATE NULL,
  CONSTRAINT `PK_CoordinacionFacultad` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_coordinacionFacultad_usuario_facultad_fechaInicio` UNIQUE (`usuario`, `facultad`, `fechaInicio`),
  KEY `IX_coordinacionFacultad_facultad` (`facultad`),
  CONSTRAINT `FK_coordinacionFacultad_usuario` FOREIGN KEY (`usuario`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_coordinacionFacultad_facultad` FOREIGN KEY (`facultad`) REFERENCES `Facultad` (`id`),
  CONSTRAINT `CHK_coordinacionFacultad_fechaInicio` CHECK (`fechaInicio` >= '1982-01-01'),
  CONSTRAINT `CHK_coordinacionFacultad_fechaFin` CHECK (`fechaFin` IS NULL OR `fechaFin` >= `fechaInicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- CoordinacionPrograma (entidad asociativa)
-- ---------------------------------------------------------------------
CREATE TABLE `CoordinacionPrograma` (
  `id` CHAR(36) NOT NULL,
  `usuario` CHAR(36) NOT NULL,
  `programaAcademico` CHAR(36) NOT NULL,
  `fechaInicio` DATE NOT NULL,
  `fechaFin` DATE NULL,
  CONSTRAINT `PK_CoordinacionPrograma` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_coordinacionPrograma_usuario_programaAcademico_fechaInicio` UNIQUE (`usuario`, `programaAcademico`, `fechaInicio`),
  KEY `IX_coordinacionPrograma_programaAcademico` (`programaAcademico`),
  CONSTRAINT `FK_coordinacionPrograma_usuario` FOREIGN KEY (`usuario`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_coordinacionPrograma_programaAcademico` FOREIGN KEY (`programaAcademico`) REFERENCES `ProgramaAcademico` (`id`),
  CONSTRAINT `CHK_coordinacionPrograma_fechaInicio` CHECK (`fechaInicio` >= '1982-01-01'),
  CONSTRAINT `CHK_coordinacionPrograma_fechaFin` CHECK (`fechaFin` IS NULL OR `fechaFin` >= `fechaInicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- VinculacionAcademica (entidad asociativa)
-- ---------------------------------------------------------------------
-- Nota: la restricción UQ_vinculacionAcademica_usuario_programaAcademico_fechaVinculacion se llama UQ_vinculacionAcademica_usuario_programaAcademico_fecha porque el nombre de la documentación tiene 66 caracteres y MySQL admite máximo 64.
CREATE TABLE `VinculacionAcademica` (
  `id` CHAR(36) NOT NULL,
  `usuario` CHAR(36) NOT NULL,
  `programaAcademico` CHAR(36) NOT NULL,
  `fechaVinculacion` DATE NOT NULL,
  `fechaDesvinculacion` DATE NULL,
  CONSTRAINT `PK_VinculacionAcademica` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_vinculacionAcademica_usuario_programaAcademico_fecha` UNIQUE (`usuario`, `programaAcademico`, `fechaVinculacion`),
  KEY `IX_vinculacionAcademica_programaAcademico` (`programaAcademico`),
  CONSTRAINT `FK_vinculacionAcademica_usuario` FOREIGN KEY (`usuario`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_vinculacionAcademica_programaAcademico` FOREIGN KEY (`programaAcademico`) REFERENCES `ProgramaAcademico` (`id`),
  CONSTRAINT `CHK_vinculacionAcademica_fechaVinculacion` CHECK (`fechaVinculacion` >= '1982-01-01'),
  CONSTRAINT `CHK_vinculacionAcademica_fechaDesvinculacion` CHECK (`fechaDesvinculacion` IS NULL OR `fechaDesvinculacion` >= `fechaVinculacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- Kudo (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `Kudo` (
  `id` CHAR(36) NOT NULL,
  `autor` CHAR(36) NOT NULL,
  `destinatario` CHAR(36) NOT NULL,
  `categoria` CHAR(36) NOT NULL,
  `estadoContenido` CHAR(36) NOT NULL,
  `nombreCategoria` VARCHAR(50) NOT NULL,
  `contenido` TEXT NOT NULL,
  `fechaCreacion` DATETIME NOT NULL,
  CONSTRAINT `PK_Kudo` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_kudo_autor_fechaCreacion` UNIQUE (`autor`, `fechaCreacion`),
  KEY `IX_kudo_destinatario` (`destinatario`),
  KEY `IX_kudo_categoria` (`categoria`),
  KEY `IX_kudo_estadoContenido` (`estadoContenido`),
  CONSTRAINT `FK_kudo_autor` FOREIGN KEY (`autor`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_kudo_destinatario` FOREIGN KEY (`destinatario`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_kudo_categoria` FOREIGN KEY (`categoria`) REFERENCES `Categoria` (`id`),
  CONSTRAINT `FK_kudo_estadoContenido` FOREIGN KEY (`estadoContenido`) REFERENCES `EstadoContenido` (`id`),
  CONSTRAINT `CHK_kudo_destinatario` CHECK (`destinatario` <> `autor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- Replica (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `Replica` (
  `id` CHAR(36) NOT NULL,
  `kudo` CHAR(36) NOT NULL,
  `replicaPadre` CHAR(36) NULL,
  `autor` CHAR(36) NOT NULL,
  `estadoContenido` CHAR(36) NOT NULL,
  `contenido` TEXT NOT NULL,
  `fechaCreacion` DATETIME NOT NULL,
  CONSTRAINT `PK_Replica` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_replica_autor_fechaCreacion` UNIQUE (`autor`, `fechaCreacion`),
  KEY `IX_replica_kudo` (`kudo`),
  KEY `IX_replica_replicaPadre` (`replicaPadre`),
  KEY `IX_replica_estadoContenido` (`estadoContenido`),
  CONSTRAINT `FK_replica_kudo` FOREIGN KEY (`kudo`) REFERENCES `Kudo` (`id`),
  CONSTRAINT `FK_replica_replicaPadre` FOREIGN KEY (`replicaPadre`) REFERENCES `Replica` (`id`),
  CONSTRAINT `FK_replica_autor` FOREIGN KEY (`autor`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_replica_estadoContenido` FOREIGN KEY (`estadoContenido`) REFERENCES `EstadoContenido` (`id`),
  CONSTRAINT `CHK_replica_replicaPadre` CHECK (`replicaPadre` IS NULL OR `replicaPadre` <> `id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- CalificacionKudo (entidad asociativa)
-- ---------------------------------------------------------------------
CREATE TABLE `CalificacionKudo` (
  `id` CHAR(36) NOT NULL,
  `kudo` CHAR(36) NOT NULL,
  `usuario` CHAR(36) NOT NULL,
  `estrellas` INT NOT NULL,
  `fechaCalificacion` DATETIME NOT NULL,
  CONSTRAINT `PK_CalificacionKudo` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_calificacionKudo_kudo_usuario` UNIQUE (`kudo`, `usuario`),
  KEY `IX_calificacionKudo_usuario` (`usuario`),
  CONSTRAINT `FK_calificacionKudo_kudo` FOREIGN KEY (`kudo`) REFERENCES `Kudo` (`id`),
  CONSTRAINT `FK_calificacionKudo_usuario` FOREIGN KEY (`usuario`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `CHK_calificacionKudo_estrellas` CHECK (`estrellas` BETWEEN 1 AND 5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- RevisionKudo (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `RevisionKudo` (
  `id` CHAR(36) NOT NULL,
  `kudo` CHAR(36) NOT NULL,
  `moderador` CHAR(36) NOT NULL,
  `estadoRevision` CHAR(36) NOT NULL,
  `justificacion` VARCHAR(1000) NULL,
  `fechaInicio` DATETIME NOT NULL,
  `fechaCierre` DATETIME NULL,
  CONSTRAINT `PK_RevisionKudo` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_revisionKudo_kudo_fechaInicio` UNIQUE (`kudo`, `fechaInicio`),
  KEY `IX_revisionKudo_moderador` (`moderador`),
  KEY `IX_revisionKudo_estadoRevision` (`estadoRevision`),
  CONSTRAINT `FK_revisionKudo_kudo` FOREIGN KEY (`kudo`) REFERENCES `Kudo` (`id`),
  CONSTRAINT `FK_revisionKudo_moderador` FOREIGN KEY (`moderador`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_revisionKudo_estadoRevision` FOREIGN KEY (`estadoRevision`) REFERENCES `EstadoRevision` (`id`),
  CONSTRAINT `CHK_revisionKudo_fechaCierre` CHECK (`fechaCierre` IS NULL OR `fechaCierre` >= `fechaInicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- CalificacionReplica (entidad asociativa)
-- ---------------------------------------------------------------------
CREATE TABLE `CalificacionReplica` (
  `id` CHAR(36) NOT NULL,
  `replica` CHAR(36) NOT NULL,
  `usuario` CHAR(36) NOT NULL,
  `estrellas` INT NOT NULL,
  `fechaCalificacion` DATETIME NOT NULL,
  CONSTRAINT `PK_CalificacionReplica` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_calificacionReplica_replica_usuario` UNIQUE (`replica`, `usuario`),
  KEY `IX_calificacionReplica_usuario` (`usuario`),
  CONSTRAINT `FK_calificacionReplica_replica` FOREIGN KEY (`replica`) REFERENCES `Replica` (`id`),
  CONSTRAINT `FK_calificacionReplica_usuario` FOREIGN KEY (`usuario`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `CHK_calificacionReplica_estrellas` CHECK (`estrellas` BETWEEN 1 AND 5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- RevisionReplica (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `RevisionReplica` (
  `id` CHAR(36) NOT NULL,
  `replica` CHAR(36) NOT NULL,
  `moderador` CHAR(36) NOT NULL,
  `estadoRevision` CHAR(36) NOT NULL,
  `justificacion` VARCHAR(1000) NULL,
  `fechaInicio` DATETIME NOT NULL,
  `fechaCierre` DATETIME NULL,
  CONSTRAINT `PK_RevisionReplica` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_revisionReplica_replica_fechaInicio` UNIQUE (`replica`, `fechaInicio`),
  KEY `IX_revisionReplica_moderador` (`moderador`),
  KEY `IX_revisionReplica_estadoRevision` (`estadoRevision`),
  CONSTRAINT `FK_revisionReplica_replica` FOREIGN KEY (`replica`) REFERENCES `Replica` (`id`),
  CONSTRAINT `FK_revisionReplica_moderador` FOREIGN KEY (`moderador`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_revisionReplica_estadoRevision` FOREIGN KEY (`estadoRevision`) REFERENCES `EstadoRevision` (`id`),
  CONSTRAINT `CHK_revisionReplica_fechaCierre` CHECK (`fechaCierre` IS NULL OR `fechaCierre` >= `fechaInicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- ReporteKudo (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `ReporteKudo` (
  `id` CHAR(36) NOT NULL,
  `kudo` CHAR(36) NOT NULL,
  `usuario` CHAR(36) NOT NULL,
  `motivo` CHAR(36) NOT NULL,
  `revisionKudo` CHAR(36) NULL,
  `descripcion` VARCHAR(500) NULL,
  `fechaReporte` DATETIME NOT NULL,
  CONSTRAINT `PK_ReporteKudo` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_reporteKudo_usuario_kudo` UNIQUE (`usuario`, `kudo`),
  KEY `IX_reporteKudo_kudo` (`kudo`),
  KEY `IX_reporteKudo_motivo` (`motivo`),
  KEY `IX_reporteKudo_revisionKudo` (`revisionKudo`),
  CONSTRAINT `FK_reporteKudo_kudo` FOREIGN KEY (`kudo`) REFERENCES `Kudo` (`id`),
  CONSTRAINT `FK_reporteKudo_usuario` FOREIGN KEY (`usuario`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_reporteKudo_motivo` FOREIGN KEY (`motivo`) REFERENCES `Motivo` (`id`),
  CONSTRAINT `FK_reporteKudo_revisionKudo` FOREIGN KEY (`revisionKudo`) REFERENCES `RevisionKudo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- ReporteReplica (entidad)
-- ---------------------------------------------------------------------
CREATE TABLE `ReporteReplica` (
  `id` CHAR(36) NOT NULL,
  `replica` CHAR(36) NOT NULL,
  `usuario` CHAR(36) NOT NULL,
  `motivo` CHAR(36) NOT NULL,
  `revisionReplica` CHAR(36) NULL,
  `descripcion` VARCHAR(500) NULL,
  `fechaReporte` DATETIME NOT NULL,
  CONSTRAINT `PK_ReporteReplica` PRIMARY KEY (`id`),
  CONSTRAINT `UQ_reporteReplica_usuario_replica` UNIQUE (`usuario`, `replica`),
  KEY `IX_reporteReplica_replica` (`replica`),
  KEY `IX_reporteReplica_motivo` (`motivo`),
  KEY `IX_reporteReplica_revisionReplica` (`revisionReplica`),
  CONSTRAINT `FK_reporteReplica_replica` FOREIGN KEY (`replica`) REFERENCES `Replica` (`id`),
  CONSTRAINT `FK_reporteReplica_usuario` FOREIGN KEY (`usuario`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_reporteReplica_motivo` FOREIGN KEY (`motivo`) REFERENCES `Motivo` (`id`),
  CONSTRAINT `FK_reporteReplica_revisionReplica` FOREIGN KEY (`revisionReplica`) REFERENCES `RevisionReplica` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================================
--  VISTAS: datos calculados (no se guardan como columnas)
-- =====================================================================

CREATE OR REPLACE VIEW `vw_CoordinacionFacultad` AS
SELECT
  t.`id`,
  t.`usuario`,
  t.`facultad`,
  t.`fechaInicio`,
  t.`fechaFin`,
  (t.`fechaFin` IS NULL) AS `vigente`
FROM `CoordinacionFacultad` t;

CREATE OR REPLACE VIEW `vw_CoordinacionPrograma` AS
SELECT
  t.`id`,
  t.`usuario`,
  t.`programaAcademico`,
  t.`fechaInicio`,
  t.`fechaFin`,
  (t.`fechaFin` IS NULL) AS `vigente`
FROM `CoordinacionPrograma` t;

CREATE OR REPLACE VIEW `vw_VinculacionAcademica` AS
SELECT
  t.`id`,
  t.`usuario`,
  t.`programaAcademico`,
  t.`fechaVinculacion`,
  t.`fechaDesvinculacion`,
  (t.`fechaDesvinculacion` IS NULL) AS `vigente`
FROM `VinculacionAcademica` t;

CREATE OR REPLACE VIEW `vw_Kudo` AS
SELECT
  t.`id`,
  t.`autor`,
  t.`destinatario`,
  t.`categoria`,
  t.`estadoContenido`,
  t.`nombreCategoria`,
  t.`contenido`,
  t.`fechaCreacion`,
  CAST(COALESCE(c.`promedioEstrellas`, 0) AS DECIMAL(3,2)) AS `promedioEstrellas`,
  CAST(COALESCE(c.`totalCalificaciones`, 0) AS UNSIGNED) AS `totalCalificaciones`
FROM `Kudo` t
LEFT JOIN (
  SELECT `kudo`, AVG(`estrellas`) AS `promedioEstrellas`, COUNT(*) AS `totalCalificaciones`
  FROM `CalificacionKudo` GROUP BY `kudo`
) c ON c.`kudo` = t.`id`;

CREATE OR REPLACE VIEW `vw_Replica` AS
SELECT
  t.`id`,
  t.`kudo`,
  t.`replicaPadre`,
  t.`autor`,
  t.`estadoContenido`,
  t.`contenido`,
  t.`fechaCreacion`,
  CAST(COALESCE(c.`promedioEstrellas`, 0) AS DECIMAL(3,2)) AS `promedioEstrellas`,
  CAST(COALESCE(c.`totalCalificaciones`, 0) AS UNSIGNED) AS `totalCalificaciones`
FROM `Replica` t
LEFT JOIN (
  SELECT `replica`, AVG(`estrellas`) AS `promedioEstrellas`, COUNT(*) AS `totalCalificaciones`
  FROM `CalificacionReplica` GROUP BY `replica`
) c ON c.`replica` = t.`id`;

CREATE OR REPLACE VIEW `vw_ReporteKudo` AS
SELECT
  t.`id`,
  t.`kudo`,
  t.`usuario`,
  t.`motivo`,
  t.`revisionKudo`,
  t.`descripcion`,
  t.`fechaReporte`,
  CASE
    WHEN t.`revisionKudo` IS NULL THEN 'Pendiente'
    WHEN er.`nombre` = 'En curso' THEN 'En revisión'
    WHEN er.`nombre` = 'Contenido vetado' THEN 'Aceptado'
    ELSE 'Rechazado'
  END AS `estadoReporte`
FROM `ReporteKudo` t
LEFT JOIN `RevisionKudo` rv ON rv.`id` = t.`revisionKudo`
LEFT JOIN `EstadoRevision` er ON er.`id` = rv.`estadoRevision`;

CREATE OR REPLACE VIEW `vw_ReporteReplica` AS
SELECT
  t.`id`,
  t.`replica`,
  t.`usuario`,
  t.`motivo`,
  t.`revisionReplica`,
  t.`descripcion`,
  t.`fechaReporte`,
  CASE
    WHEN t.`revisionReplica` IS NULL THEN 'Pendiente'
    WHEN er.`nombre` = 'En curso' THEN 'En revisión'
    WHEN er.`nombre` = 'Contenido vetado' THEN 'Aceptado'
    ELSE 'Rechazado'
  END AS `estadoReporte`
FROM `ReporteReplica` t
LEFT JOIN `RevisionReplica` rv ON rv.`id` = t.`revisionReplica`
LEFT JOIN `EstadoRevision` er ON er.`id` = rv.`estadoRevision`;

SET FOREIGN_KEY_CHECKS = 1;

-- Fin del script 1. Continúe con 02_datos_iniciales_kudos.sql