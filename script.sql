DROP DATABASE IF EXISTS potioncraft;
CREATE DATABASE potioncraft;

USE potioncraft;

CREATE TABLE Ingrediente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM ('HIERBA','SETA','MINERAL'),
    nombre VARCHAR(255),
    descripcion TEXT,
    precioCompra DOUBLE(6,2),
    efectoPositivo VARCHAR(255),
    efectoNegativo VARCHAR(255),
    nivelToxicidad INT,
    dureza DOUBLE(4,2)
);

CREATE TABLE Comerciante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo ENUM('HERBORISTA','RECOLECTOR_SETAS','MERCADER','MINERO') NOT NULL
);

CREATE TABLE ComercianteIngrediente (
    comerciante_id INT NOT NULL,
    ingrediente_id INT NOT NULL,
    PRIMARY KEY (comerciante_id, ingrediente_id),
    FOREIGN KEY (comerciante_id) REFERENCES Comerciante(id) ON DELETE CASCADE,
    FOREIGN KEY (ingrediente_id) REFERENCES Ingrediente(id) ON DELETE CASCADE
);

CREATE TABLE Pocion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Ingredientes_Pociones (
    pocion_id INT NOT NULL,
    ingrediente_id INT NOT NULL,
    PRIMARY KEY (pocion_id, ingrediente_id),
    FOREIGN KEY (pocion_id) REFERENCES Pocion(id) ON DELETE CASCADE,
    FOREIGN KEY (ingrediente_id) REFERENCES Ingrediente(id) ON DELETE CASCADE
);

-- Tabla del inventario del jugador para ingredientes
CREATE TABLE InventarioIngrediente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ingrediente_id INT NOT NULL UNIQUE,
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    FOREIGN KEY (ingrediente_id) REFERENCES Ingrediente(id) ON DELETE CASCADE
);

-- Tabla del inventario del jugador para pociones
CREATE TABLE InventarioPocion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pocion_id INT NOT NULL UNIQUE,
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    FOREIGN KEY (pocion_id) REFERENCES Pocion(id) ON DELETE CASCADE
);

CREATE TABLE InfoPartida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    oro DOUBLE NOT NULL DEFAULT 500, -- Dinero actual del jugador
    reputacion INT NOT NULL DEFAULT 0 -- Reputación del jugador
);




INSERT INTO Comerciante (tipo, nombre) VALUES
    ('HERBORISTA', 'Thalassa Hierbasueños'),
    ('RECOLECTOR_SETAS', 'Fynn Bosquefungi'),
    ('MERCADER', 'Darion Mercaluz'),
    ('HERBORISTA', 'Aldric Hojaverde'),
    ('MINERO', 'Drogan Rocaviva'),
    ('MERCADER', 'Luciano Diamante');

-- Insertar datos en la tabla Ingrediente
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (1,'HIERBA','Campanígnea','Una rara flor que solo florece en el amanecer, conocida por sus propiedades curativas.',16.4,'INMUNIDAD',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (2,'HIERBA','Flor vendaval','Una flor mística que crece en las regiones más frías, utilizada para preparar pociones de resistencia.',19.2,'FORTALECIMIENTO',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (3,'HIERBA','Flor acuática','Una flor que prospera en los lagos y ríos tranquilos, conocida por su capacidad para mejorar la concentración.',16.0,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (4,'HIERBA','Terraria','Una planta resistente que crece en los desiertos áridos, usada para aumentar la resistencia física.',13.6,'SUERTE',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (5,'HIERBA','Hierba Vital','Una hierba que emana una luz cálida y revitalizante, utilizada para restaurar energía vital.',12.0,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (6,'HIERBA','Algas','Una mezcla de algas marinas con propiedades purificadoras y curativas.',26.0,'SUERTE',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (7,'HIERBA','Espina dorada','Una planta espinosa que florece en las montañas, conocida por sus propiedades regenerativas.',38.8,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (8,'HIERBA','Buena baya','Una baya dulce y jugosa que crece en los bosques, utilizada para potenciar la resistencia mágica.',18.4,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (9,'HIERBA','Plátano peludo','Un fruto exótico cubierto de una piel suave y velluda, conocido por su capacidad para mejorar la concentración.',31.2,'SUERTE',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (10,'HIERBA','Fruta helada','Una fruta que crece en las cumbres nevadas de las montañas, utilizada para enfriar las pociones.',23.2,'SUERTE',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (11,'HIERBA','Cardo tormentoso','Una planta resistente que crece en los páramos ventosos, utilizada para potenciar la fuerza física.',34.8,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (12,'HIERBA','Espina sangrienta','Una planta venenosa con espinas afiladas, conocida por sus propiedades estimulantes.',32.4,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (13,'HIERBA','Remolacha onírica','Una remolacha que solo crece en los sueños más profundos, utilizada para inducir sueños lúcidos.',36.8,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (14,'HIERBA','Romero druídico','Una planta sagrada que solo puede ser recolectada por los druidas más experimentados, utilizada para purificar el espíritu.',22.6,'SUERTE',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (15,'HIERBA','Flor plumífera','Esta flor exótica tiene delicadas plumas que le dan una apariencia etérea.',83.2,'SUERTE',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (16,'HIERBA','Raíz de lava','Una raíz de color rojo intenso que se encuentra en las regiones volcánicas.',57.6,'SUERTE',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (17,'HIERBA','Hojafría','Una hoja helada que conserva su frescura incluso en los climas más cálidos.',77.6,'SUERTE',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (18,'HIERBA','Flameweed','Una planta que arde con un fuego mágico y produce llamas azules.',70.4,'INMUNIDAD',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (19,'HIERBA','Raíz estranguladora','Una raíz retorcida que parece tener una vida propia.',75.6,'FORTALECIMIENTO',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (20,'HIERBA','Palo espinoso','Un palo cubierto de espinas afiladas que se encuentra en los bosques densos.',72.8,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (21,'HIERBA','Hierba enroscada','Una hierba que crece en espirales y se retuerce al tacto.',104.1,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (22,'HIERBA','Flor explosiva','Una flor que estalla en un destello de luz y color al ser tocada.',113.2,'SUERTE',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (23,'HIERBA','Pimienta de dragón','Una pimienta extremadamente picante que proviene de las montañas de fuego.',112.8,'INMUNIDAD',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (24,'HIERBA','Flor esponjosa','Una flor ligera y esponjosa que flota en el aire como un copo de nieve.',99.6,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (25,'HIERBA','Healer\'s Heather','Una hierba que se dice que tiene propiedades curativas y calmantes.',77.2,'FORTALECIMIENTO',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (26,'HIERBA','Flor mágica','Una flor que emite una luz mágica y brillante, asociada con la magia poderosa.',132.0,'SANACIÓN',NULL,NULL,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (27,'SETA','Seta marrón','Una seta comestible con un sabor terroso y suave.',86.4,NULL,'SUEÑO',5,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (28,'SETA','Dryad\'s Saddle','Un hongo de gran tamaño con forma de silla, que crece en los troncos de los árboles.',33.6,NULL,'SUEÑO',7,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (29,'SETA','Goblin Shroom','Un hongo pequeño y venenoso que se encuentra en los pantanos.',42.0,NULL,'INTOXICACIÓN',6,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (30,'SETA','Green Mushroom','Un hongo verde brillante que crece en zonas húmedas.',30.0,NULL,'CONGELACIÓN',3,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (31,'SETA','Marshroom','Un hongo con forma de sombrero de copa que prospera en áreas pantanosas.',52.4,NULL,'INTOXICACIÓN',5,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (32,'SETA','Seta loca','Una seta psicodélica que causa alucinaciones cuando se consume.',36.4,NULL,'FURIA',4,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (33,'SETA','Shadow Chanterelle','Un hongo oscuro con propiedades místicas, que se encuentra en áreas sombrías.',83.2,NULL,'CONGELACIÓN',3,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (34,'SETA','Sulphur Shelf','Un hongo brillante de color naranja que crece en troncos de árboles muertos.',46.8,NULL,'SUEÑO',1,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (35,'SETA','Weirdshroom','Un hongo extraño con propiedades desconocidas, que crece en lugares remotos.',46.0,NULL,'CONGELACIÓN',1,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (36,'SETA','Witch Mushroom','Un hongo asociado con la magia y la brujería, que crece en bosques encantados.',42.4,NULL,'SUEÑO',3,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (37,'SETA','Grave Truffle','Una trufa rara que se encuentra en cementerios y áreas de enterramiento.',126.0,NULL,'FURIA',7,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (38,'SETA','Seta mohosa','Una seta con una capa de musgo que la hace parecer vieja y desgastada.',52.4,NULL,'INTOXICACIÓN',3,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (39,'SETA','Parasol brumoso','Un hongo grande con forma de sombrilla, que se encuentra en áreas nebulosas.',88.8,NULL,'FURIA',4,NULL);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (40,'MINERAL','Cristal nublado','Cristal translúcido con una neblina interna, se forma en condiciones atmosféricas específicas.',220.0,NULL,NULL,NULL,7.85);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (41,'MINERAL','Earth Pyrite','Mineral terroso con tonos dorados, conocido por su brillo metálico.',152.0,NULL,NULL,NULL,9.68);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (42,'MINERAL','Frost Sapphire','Gema azul pálida con patrones de escarcha, se encuentra en regiones frías y montañosas.',184.0,NULL,NULL,NULL,4.86);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (43,'MINERAL','Fire Citrine','Cuarzo de tonos cálidos que emite un brillo similar al fuego, encontrado en áreas volcánicas.',188.0,NULL,NULL,NULL,5.25);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (44,'MINERAL','Blood Ruby','Rubí de tonos rojos intensos, se cree que se forma en áreas con alta actividad geotérmica.',200.0,NULL,NULL,NULL,1.68);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (45,'MINERAL','Cristal arcano','Cristal iridiscente con propiedades místicas, se encuentra en lugares de gran concentración de energía.',236.0,NULL,NULL,NULL,2.67);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (46,'MINERAL','Cristal vital','Cristal transparente con un resplandor interno, asociado con la vitalidad y la curación.',136.0,NULL,NULL,NULL,8.29);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (47,'MINERAL','Estibina apestada','Mineral plateado con un olor desagradable, se forma en entornos contaminados.',168.0,NULL,NULL,NULL,3.44);
INSERT INTO Ingrediente (`id`,`tipo`,`nombre`,`descripcion`,`precioCompra`,`efectoPositivo`,`efectoNegativo`,`nivelToxicidad`,`dureza`) VALUES (48,'MINERAL','Bismuto fantástico','Metal cristalino con reflejos de colores vivos, se encuentra en depósitos de minerales raros.',720.0,NULL,NULL,NULL,2.35);

-- Insertar datos en ComercianteIngrediente según las reglas de asociación
INSERT INTO ComercianteIngrediente (comerciante_id, ingrediente_id)
SELECT c.id, i.id
FROM Comerciante c
JOIN Ingrediente i
ON (
    (c.tipo = 'HERBORISTA' AND i.tipo = 'HIERBA') OR
    (c.tipo = 'RECOLECTOR_SETAS' AND i.tipo = 'SETA') OR
    (c.tipo = 'MINERO' AND i.tipo = 'MINERAL') OR
    (c.tipo = 'MERCADER')
);


INSERT INTO Pocion (nombre) VALUES
('Ácido'),
('Adhesivo'),
('Agrandar'),
('Aislante'),
('Antimagia'),
('Congelación'),
('Crecimiento rápido'),
('Destreza'),
('Encoger'),
('Explosivo'),
('Fragancia'),
('Fuerza'),
('Furia'),
('Ignífugo'),
('Inspiración'),
('Intoxicación'),
('Invisibilidad'),
('Lubricidad'),
('Luz');

INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (1, 25);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (1, 44);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (2, 38);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (2, 46);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (3, 32);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (3, 44);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (4, 10);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (4, 12);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (5, 15);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (5, 29);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (6, 23);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (6, 30);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (7, 9);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (7, 13);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (7, 26);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (7, 46);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (8, 12);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (9, 20);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (10, 5);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (10, 26);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (10, 33);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (10, 45);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (11, 10);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (11, 16);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (11, 26);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (11, 46);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (12, 3);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (12, 27);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (12, 41);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (12, 42);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (13, 2);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (13, 3);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (13, 4);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (13, 45);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (14, 29);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (14, 38);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (15, 4);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (15, 38);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (16, 11);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (16, 36);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (17, 7);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (17, 16);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (17, 32);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (18, 17);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (18, 45);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (18, 46);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (19, 14);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (19, 25);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (19, 34);
INSERT INTO Ingredientes_Pociones (`pocion_id`, `ingrediente_id`) VALUES (19, 38);



