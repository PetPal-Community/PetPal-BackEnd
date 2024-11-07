-- Inserción en la tabla roles
INSERT INTO roles (name) VALUES
                             ('ADMIN'),
                             ('CUSTOMER'),
                             ('CARER');

-- Inserción de UserGeneral (para 5 usuarios)
-- Corregido: falta de cierre de paréntesis en la primera línea
INSERT INTO personas (email, contrasena, rol_id) VALUES
                                                     ('juan.perez@example.com', '$2a$12$rat/AGCWIm3SjfHaxvpM2eHaXXgpUgfz8xsPrAkrQWIigFH7zEEQq', 2),  -- CUSTOMER - password123
                                                     ('maria.gonzalez@example.com', '$2a$12$p8w6uTICuyVWw4iOvD2xiuMM1w8KDBzNBsc3H2OhCFVe2wCVZvzsS', 2),  -- CUSTOMER -- password456
                                                     ('carlos.sanchez@example.com', '$2a$12$tfnaWmPayRa0NiILamOzPuKt/KDzV.z3aZx4qf8HH7OE8Aukaoyw6', 2),  -- CUSTOMER -- password789
                                                     ('ana.lopez@example.com', '$2a$12$BIKOOrtSkOxWOK6MDQ6r6eMZkjkcT1Sdjaq2W6cQHWccJlbztacHa', 2),  -- CUSTOMER --password321
                                                     ('jorge.martinez@example.com', '$2a$12$ZllQIlKsQUwBnMU8xOm3DukRIioWEw.5onJko1RnJbMmnVTMKpvOG', 2);  -- CUSTOMER - password111

-- Inserción de Carer (para 5 cuidadores)
INSERT INTO personas (email, contrasena, rol_id) VALUES
                                                     ('laura.martinez@example.com', '$2a$12$Z.i/VlHNqSmyYwm79PUJPuA35E6QiNL10YHL821t.WR4CG9XG5jYS', 3),  -- CARER -- cuidador123
                                                     ('pedro.fernandez@example.com', '$2a$12$vLZEoafjjPXkcqhDdngQ0ODhXJBf/v4t/hchVrXuNNiIwwwTN6C6G', 3),  -- CARER  - cuidador456
                                                     ('sofia.ramirez@example.com', '$2a$12$PubaPmv3C2oSdEkZCrVWpOVvH.hIIB15HH0HaTzt2WEiJic/I4STq', 3),  -- CARER -- cuidador789
                                                     ('javier.gomez@example.com', '$2a$12$eK0QjTGbnyM941/ONWMLTeELLi.R7ghcBeMCbNeB32c1Vd0Qp1MNG', 3),  -- CARER -- cuidador012
                                                     ('isabel.perez@example.com', '$2a$12$od9.bKH2woeGRHYVWw64xuaxSuxX0c6KRy4w6ftuAxx5TEDjBi2ui', 3),  -- CARER -- cuidador345
                                                     ('nmallma91@gmail.com', '$2a$12$1Hv5xugSYx35LdIKDSXwjO586TW1spbqd.p7A6kmiwW837jExwcyG',1);

-- Inserción de usuarios (relacionando UserGeneral con User)
INSERT INTO usuarios (nombre, apellido, telefono, user_id) VALUES
                                                               ('Juan', 'Pérez', '123456789', 1),
                                                               ('María', 'González', '987654321', 2),
                                                               ('Carlos', 'Sánchez', '456123789', 3),
                                                               ('Ana', 'Lopez', '321654987', 4),
                                                               ('Jorge', 'Martínez', '852963741', 5);

-- Inserción de cuidadores (relacionando UserGeneral con Carer)
INSERT INTO cuidadores (nombre, apellido, telefono, user_id) VALUES
                                                                 ('Laura', 'Martínez', '654321789', 6),
                                                                 ('Pedro', 'Fernández', '789123456', 7),
                                                                 ('Sofía', 'Ramírez', '159753486', 8),
                                                                 ('Javier', 'Gómez', '951357159', 9),
                                                                 ('Isabel', 'Pérez', '753159486', 10);

-- Inserción de mascotas (de 0 a 2 por cada usuario)
INSERT INTO mascotas (nombre, raza, edad, genero, especie, user_id) VALUES
-- Usuario 1 (Juan Pérez) - 2 mascotas
('Rex', 'Labrador', 3, 'M', 'Perro', 1),
('Mittens', 'Siames', 2, 'F', 'Gato', 1),

-- Usuario 2 (María González) - 1 mascota
('Fido', 'Bulldog', 4, 'M', 'Perro', 2),

-- Usuario 4 (Ana López) - 2 mascotas
('Whiskers', 'Persa', 1, 'F', 'Gato', 4),
('Buddy', 'Golden Retriever', 5, 'M', 'Perro', 4),

-- Usuario 5 (Jorge Martínez) - 1 mascota
('Goldie', 'Goldfish', 1, 'M', 'Pez', 5);




-- Inserción de Recordatorios
INSERT INTO Recordatorios (tipo_recordatorio, descripcion, hora, mascota_id) VALUES
-- Recordatorios para la mascota de Juan Pérez (Rex)
('Comida', 'Recordar dar de comer a Rex', '2024-11-06 08:00:00', 1),
('Visita al veterinario', 'Llevar a Rex al veterinario para chequeo', '2024-11-15 10:00:00', 1),

-- Recordatorios para la mascota de Juan Pérez (Mittens)
('Comida', 'Recordar dar de comer a Mittens', '2024-11-06 08:30:00', 2),
('Vacuna', 'Vacunar a Mittens', '2024-11-20 09:00:00', 2),

-- Recordatorios para la mascota de María González (Fido)
('Comida', 'Recordar dar de comer a Fido', '2024-11-06 09:00:00', 3),
('Paseo', 'Sacar a pasear a Fido', '2024-11-07 18:00:00', 3),

-- Recordatorios para la mascota de Ana López (Whiskers)
('Comida', 'Recordar dar de comer a Whiskers', '2024-11-06 08:45:00', 4),
('Visita al veterinario', 'Llevar a Whiskers al veterinario', '2024-11-18 11:00:00', 4),

-- Recordatorios para la mascota de Ana López (Buddy)
('Comida', 'Recordar dar de comer a Buddy', '2024-11-06 09:30:00', 5),
('Paseo', 'Sacar a pasear a Buddy', '2024-11-07 17:30:00', 5),

-- Recordatorios para la mascota de Jorge Martínez (Goldie)
('Comida', 'Recordar alimentar a Goldie', '2024-11-06 10:00:00', 6);

-- Inserción de Visitas Médicas
INSERT INTO visitas_medicas (diagnostico, fecha_visita, pet_id) VALUES
                                                                    ('Chequeo dental', '2024-10-01', 1),  -- Visita de Rex
                                                                    ('Consulta por tos', '2024-10-05', 2), -- Visita de Luna
                                                                    ('Revisión de piel', '2024-10-10', 3), -- Visita de Max
                                                                    ('Control de vacunación', '2024-10-12', 4), -- Visita de Bella
                                                                    ('Chequeo general', '2024-10-15', 5); -- Visita de Rocky

-- Inserción de Documentos Médicos
INSERT INTO medic_documents (tipo_documento, descripcion, visita_medica_id) VALUES
                                                                                ('Vacuna contra rabia', 'Vacuna administrada durante la visita', 1), -- Para Rex
                                                                                ('Certificado de salud', 'Certificado para viajar en avión', 2), -- Para Luna
                                                                                ('Informe de análisis', 'Resultados de pruebas de alergia', 3), -- Para Max
                                                                                ('Vacuna contra moquillo', 'Vacuna anual', 4), -- Para Bella
                                                                                ('Registro de desparasitante', 'Desparasitante administrado', 5); -- Para Rocky

-- Inserción de Tratamientos
INSERT INTO tratamientos (descripcion, medicamento, dosis, fecha_inico, fecha_fin, visita_medico_id) VALUES
                                                                                                         ('Tratamiento para gingivitis', 'Antibiótico', '250mg', '2024-10-01', '2024-10-15', 1), -- Tratamiento de Rex
                                                                                                         ('Medicamento para la tos', 'Jarabe expectorante', '15ml', '2024-10-05', '2024-10-20', 2), -- Tratamiento de Luna
                                                                                                         ('Tratamiento para dermatitis', 'Crema tópica', 'Aplicar 2x al día', '2024-10-10', '2024-10-25', 3), -- Tratamiento de Max
                                                                                                         ('Reforzador de inmunidad', 'Suplemento vitamínico', '1 tableta diaria', '2024-10-12', '2024-10-26', 4); -- Tratamiento de Bella

-- Inserción de Comunidades
INSERT INTO comunidades (nombre, descripcion, creador_id) VALUES
                                                              ('Amantes de los perros', 'Una comunidad para compartir experiencias y consejos sobre el cuidado de los perros.', 1), -- Comunidad creada por el usuario con ID 1
                                                              ('Cuidadores de mascotas', 'Comunidad para cuidadores que buscan interactuar y compartir buenas prácticas.', 2), -- Comunidad creada por el usuario con ID 2
                                                              ('Adopción de animales', 'Espacio dedicado a la adopción responsable de animales en busca de un hogar.', 3); -- Comunidad creada por el usuario con ID 3

-- Insertar miembros de las comunidades
INSERT INTO comunidad_usuario (usuario_id, comunidad_id, fecha_que_se_unio) VALUES
                                                                                (1, 1, '2024-09-01 10:30:00'),  -- Usuario 1 en la comunidad "Amantes de los perros" (creador)
                                                                                (2, 1, '2024-09-02 11:00:00'),  -- Usuario 2 en la comunidad "Amantes de los perros"
                                                                                (3, 1, '2024-09-03 09:15:00'),  -- Usuario 3 en la comunidad "Amantes de los perros"
                                                                                (2, 2, '2024-09-04 14:45:00'),  -- Usuario 2 en la comunidad "Cuidadores de mascotas" (creador)
                                                                                (4, 2, '2024-09-05 10:00:00'),  -- Usuario 4 en la comunidad "Cuidadores de mascotas"
                                                                                (5, 2, '2024-09-06 16:30:00'),  -- Usuario 5 en la comunidad "Cuidadores de mascotas"
                                                                                (1, 3, '2024-09-07 12:00:00'),  -- Usuario 1 en la comunidad "Adopción de animales"
                                                                                (2, 3, '2024-09-08 13:45:00'),  -- Usuario 2 en la comunidad "Adopción de animales"
                                                                                (3, 3, '2024-09-09 14:30:00');  -- Usuario 3 en la comunidad "Adopción de animales" (creador)
-- Insertar publicaciones
INSERT INTO publicaciones (contenido, fecha_publicacion, fecha_actualización, usuario_id, comunidad_id) VALUES
                                                                                                            ('¡Bienvenidos a la comunidad de Amantes de los perros!', '2024-09-01 08:00:00', '2024-09-01 08:00:00', 1, 1),  -- Usuario 1, en comunidad "Amantes de los perros"
                                                                                                            ('¡Vamos a hablar sobre cómo cuidar a los cachorros!', '2024-09-02 09:15:00', '2024-09-02 09:15:00', 2, 1),  -- Usuario 2, en comunidad "Amantes de los perros"
                                                                                                            ('Consejos para mejorar la dieta de tus mascotas.', '2024-09-03 10:30:00', NULL, 3, NULL),  -- Usuario 3, publicación personal (sin fecha de actualización)
                                                                                                            ('Recomendaciones para nuevos dueños de mascotas', '2024-09-04 14:00:00', '2024-09-04 14:00:00', 4, 2),  -- Usuario 4, en comunidad "Cuidadores de mascotas"
                                                                                                            ('¿Alguien tiene experiencia con animales exóticos?', '2024-09-05 11:45:00', NULL, 5, 2),  -- Usuario 5, en comunidad "Cuidadores de mascotas" (sin fecha de actualización)
                                                                                                            ('Adopción de mascotas: Requisitos y consejos', '2024-09-06 12:00:00', '2024-09-06 12:00:00', 1, NULL),  -- Usuario 1, publicación personal
                                                                                                            ('¿Alguien sabe de alguna clínica veterinaria en la zona?', '2024-09-07 13:00:00', NULL, 2, NULL),  -- Usuario 2, publicación personal (sin fecha de actualización)
                                                                                                            ('La importancia de las vacunas en las mascotas', '2024-09-08 15:00:00', '2024-09-08 15:00:00', 3, NULL),  -- Usuario 3, publicación personal
                                                                                                            ('El mejor alimento para perros pequeños', '2024-09-09 16:30:00', '2024-09-09 16:30:00', 2, 1),  -- Usuario 4, en comunidad "Amantes de los perros"
                                                                                                            ('¿Qué opinan de la adopción de gatos en refugios?', '2024-09-10 17:45:00', NULL, 3, 3);  -- Usuario 5, en comunidad "Adopción de animales" (sin fecha de actualización)

-- Insertar likes a las publicaciones
INSERT INTO likes_publicacion (usuario_id, publicacion_id) VALUES
                                                               (2, 1),  -- Usuario 2 le da like a la publicación 1
                                                               (1, 2),  -- Usuario 1 le da like a la publicación 2
                                                               (3, 2),  -- Usuario 3 le da like a la publicación 2
                                                               (1, 3),  -- Usuario 1 le da like a la publicación 3
                                                               (4, 3),  -- Usuario 4 le da like a la publicación 3
                                                               (5, 3),  -- Usuario 5 le da like a la publicación 3
                                                               (5, 4),  -- Usuario 5 le da like a la publicación 4
                                                               (2, 5),  -- Usuario 2 le da like a la publicación 5
                                                               (2, 6),  -- Usuario 2 le da like a la publicación 6
                                                               (3, 6),  -- Usuario 3 le da like a la publicación 6
                                                               (4, 7),  -- Usuario 4 le da like a la publicación 7
                                                               (1, 7),  -- Usuario 1 le da like a la publicación 7
                                                               (5, 8),  -- Usuario 5 le da like a la publicación 8
                                                               (1, 9),  -- Usuario 1 le da like a la publicación 9
                                                               (2, 10); -- Usuario 2 le da like a la publicación 10

-- Insertar comentarios en las publicaciones
INSERT INTO comentarios (contenido, fechacreacion, fechaupdate, usuario_id, publicacion_id) VALUES
                                                                                                ('¡Me encanta esta comunidad!', '2024-09-01 08:30:00', NULL, 2, 1),  -- Usuario 2 comenta en la publicación 1
                                                                                                ('¡Excelente información sobre los cachorros!', '2024-09-02 09:45:00', '2024-09-02 09:45:00', 1, 2),  -- Usuario 1 comenta en la publicación 2
                                                                                                ('¡Muy útil! Gracias por los consejos', '2024-09-02 09:50:00', NULL, 3, 2),  -- Usuario 3 comenta en la publicación 2
                                                                                                ('¡Que gran información sobre adopción!', '2024-09-05 12:00:00', '2024-09-05 12:00:00', 5, 4),  -- Usuario 5 comenta en la publicación 4
                                                                                                ('Interesante perspectiva, lo probaré.', '2024-09-06 12:30:00', NULL, 2, 6),  -- Usuario 2 comenta en la publicación 6
                                                                                                ('¡Totalmente de acuerdo con este enfoque!', '2024-09-06 12:40:00', NULL, 3, 6);  -- Usuario 3 comenta en la publicación 6

-- Insertar servicios para los cuidadores
INSERT INTO services (tipo_servicio, descripcion, precio, cuidador_id) VALUES
                                                                           ('Paseo de perros', 'Servicio de paseo para perros durante 1 hora', 15.00, 1),  -- Laura Martínez ofrece un servicio de paseo
                                                                           ('Cuidado de mascotas a domicilio', 'Cuidado de mascotas en su hogar por 4 horas', 30.00, 1),  -- Laura Martínez ofrece otro servicio
                                                                           ('Adiestramiento canino', 'Clases de adiestramiento básico para perros', 25.00, 2),  -- Pedro Fernández ofrece adiestramiento
                                                                           ('Paseo de perros', 'Paseo de perros en grupos pequeños', 20.00, 3),  -- Sofía Ramírez ofrece paseo para perros
                                                                           ('Cuidado de mascotas a domicilio', 'Cuidado de mascotas en su hogar', 35.00, 4),  -- Javier Gómez ofrece cuidado a domicilio
                                                                           ('Baño y arreglo para mascotas', 'Servicio de baño y corte de pelo para perros', 40.00, 5);  -- Isabel Pérez ofrece baño y arreglo



-- Insertar contratos en la tabla 'contrataciones'
INSERT INTO contrataciones (duracion_contrato, estado, detalles, servicio_id, usuario_id, cuidador_id) VALUES
                                                                                                           ('1 mes', 'Activo', 'Contrato de paseo de perros por un mes.', 1, 1, 1),  -- Usuario 1 contrata a Laura Martínez (Servicio 1)
                                                                                                           ('2 meses', 'Activo', 'Contrato de cuidado de mascotas a domicilio durante 2 meses.', 2, 2, 1),  -- Usuario 2 contrata a Pedro Fernández (Servicio 2)
                                                                                                           ('1 mes', 'Activo', 'Contrato de adiestramiento canino por un mes.', 3, 3, 2),  -- Usuario 3 contrata a Sofía Ramírez (Servicio 3)
                                                                                                           ('3 meses', 'Activo', 'Contrato de paseo de perros durante 3 meses.', 4, 4, 3),  -- Usuario 4 contrata a Javier Gómez (Servicio 4)
                                                                                                           ('1 mes', 'Activo', 'Contrato de baño y arreglo para mascotas.', 5, 5, 5);  -- Usuario 5 contrata a Isabel Pérez (Servicio 5)


-- Insertar pagos en la tabla 'pagos'
INSERT INTO pagos (metodo_pago, estado_pago, fecha_pago_pagado, fecha_pago_creado, valor_pago, contratacion_id) VALUES
                                                                                                                    ('PayPal', 'COMPLETED', '2024-09-10 10:00:00', '2024-09-01 10:00:00', 100.00, 1),  -- Pago completado para contrato 1
                                                                                                                    ('PayPal', 'PENDING', '2024-09-10 12:30:00', '2024-09-05 12:30:00', 150.00, 2),  -- Pago pendiente para contrato 2
                                                                                                                    ('PayPal', 'COMPLETED', '2024-09-10 14:00:00', '2024-09-03 14:00:00', 200.00, 3),  -- Pago completado para contrato 3
                                                                                                                    ('PayPal', 'PENDING', '2024-09-10 16:15:00', '2024-09-04 16:15:00', 120.00, 4),  -- Pago pendiente para contrato 4
                                                                                                                    ('PayPal', 'COMPLETED', '2024-09-10 18:00:00', '2024-09-06 18:00:00', 80.00, 5);  -- Pago completado para contrato 5

INSERT INTO reseñas (calificacion, comentario, fecha_creacion, usuario_id, cuidador_id) VALUES
                                                                                            (4.5, 'Excelente servicio, muy atento y profesional.', '2024-09-10 10:00:00', 1, 1),  -- Usuario 1 reseña al Cuidador 6
                                                                                            (5.0, 'Muy buena experiencia, mi mascota estuvo bien cuidada.', '2024-09-12 10:30:00', 2, 1),  -- Usuario 2 reseña al Cuidador 6
                                                                                            (4.0, 'Buen trabajo, pero creo que la comunicación podría mejorar.', '2024-09-15 11:00:00', 3, 2),  -- Usuario 3 reseña al Cuidador 7
                                                                                            (5.0, 'Muy recomendable, excelente trato con mi mascota.', '2024-09-16 15:00:00', 4, 2),  -- Usuario 4 reseña al Cuidador 7
                                                                                            (3.5, 'El servicio fue adecuado, aunque hubo algunos retrasos.', '2024-09-18 17:00:00', 5, 3),  -- Usuario 5 reseña al Cuidador 8
                                                                                            (4.0, 'Bien, pero se puede mejorar en algunos aspectos.', '2024-09-20 18:00:00', 1, 3);  -- Usuario 6 reseña al Cuidador 8


-- Insertar mensajes de ejemplo
INSERT INTO mensajes (contenido, fecha_envio, fecha_update, usuario_id, cuidador_id) VALUES
                                                                                         ('Hola, ¿cómo estás? ¿Puedes actualizarme sobre el estado de mi mascota?', '2024-09-10 10:00:00', null, 1, 1),  -- Usuario 1 envía mensaje a Cuidador 6
                                                                                         ('Hola, ¿todo bien con mi mascota?', '2024-09-10 10:30:00', null, 2, 1),  -- Usuario 2 envía mensaje a Cuidador 6
                                                                                         ('Gracias por el excelente servicio, ¡todo perfecto!', '2024-09-12 11:00:00', null, 3, 2),  -- Usuario 3 envía mensaje a Cuidador 7
                                                                                         ('¿Cuándo podrías darme más detalles sobre el cuidado de mi mascota?', '2024-09-12 11:30:00', null, 4, 2),  -- Usuario 4 envía mensaje a Cuidador 7
                                                                                         ('¡Hola! Solo quería preguntar sobre el progreso de mi mascota.', '2024-09-13 12:00:00', null, 5, 3);  -- Usuario 5 envía mensaje a Cuidador 8