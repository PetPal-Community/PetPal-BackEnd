-- Inserciones de datos

-- Inserción de Usuarios
INSERT INTO usuarios (nombre, apellido, email, telefono, contrasenia) VALUES
                                                                       ('Juan', 'Pérez', 'juan.perez@example.com', '123456789', 'password123'),
                                                                       ('María', 'González', 'maria.gonzalez@example.com', '987654321', 'password456'),
                                                                       ('Carlos', 'Sánchez', 'carlos.sanchez@example.com', '456123789', 'password789'),
                                                                       ('Ana', 'Lopez', 'ana.lopez@example.com', '321654987', 'password321');

-- Inserción de Cuidadores
INSERT INTO cuidadores (nombre, apellido, email, telefono, contrasenia) VALUES
                                                                           ('Laura', 'Martinez', 'laura.martinez@example.com', '654321789', 'cuidador123'),
                                                                           ('Pedro', 'Fernandez', 'pedro.fernandez@example.com', '789123456', 'cuidador456'),
                                                                           ('Sofía', 'Ramirez', 'sofia.ramirez@example.com', '159753486', 'cuidador789'),
                                                                           ('Javier', 'Torres', 'javier.torres@example.com', '753159456', 'cuidador321');

INSERT INTO mascotas (raza, nombre, edad, genero, especie, user_id) VALUES
                                                                        ('Labrador', 'Rex', 3, 'Masculino', 'Perro', 1),  -- Asumiendo que el usuario con ID 1 es el dueño
                                                                       ('Persa', 'Mimi', 2, 'Femenino', 'Gato', 2),     -- Asumiendo que el usuario con ID 2 es el dueño
                                                                        ('Bulldog', 'Bruno', 5, 'Masculino', 'Perro', 1), -- Asumiendo que el usuario con ID 1 es el dueño
                                                                        ('Siamés', 'Luna', 1, 'Femenino', 'Gato', 3);    -- Asumiendo que el usuario con ID 3 es el dueño


-- Inserción de Recordatorios
INSERT INTO recordatorios (tipo_recordatorio, descripcion, hora, mascota_id) VALUES
                                                                                ('Vacuna', 'Recordar la vacuna anual', '2024-10-01 09:00:00', 1),  -- Asumiendo que la mascota con ID 1 es 'Rex'('Cita Veterinario', 'Cita para chequeo general', '2024-10-15 10:30:00', 2),
                                                                                ('Desparacitación', 'Recordatorio para desparacitar', '2024-11-05 14:00:00', 3),
                                                                                ('Revisión de Salud', 'Revisar la salud general', '2024-11-20 11:00:00', 4);

-- Inserción de Visitas Médicas
INSERT INTO visitas_medicas (diagnostico, fecha_visita, pet_id) VALUES
                                                                  ('Chequeo general', '2024-09-10', 1),  -- Asumiendo que la mascota con ID 1 es 'Rex'
                                                                  ('Infección de oídos', '2024-09-15', 2), -- Asumiendo que la mascota con ID 2 es 'Mimi'
                                                                  ('Alergia alimentaria', '2024-09-20', 3), -- Asumiendo que la mascota con ID 3 es 'Bruno'
                                                                  ('Control de peso', '2024-09-25', 4); -- Asumiendo que la mascota con ID 4 es 'Luna'
-- Inserción de Documentos Médicos
INSERT INTO medic_documents (tipo_documento, descripcion, visita_medica_id) VALUES
                                                                             ('Vacuna antirrábica', 'Vacuna anual para prevenir la rabia', 1), -- Asumiendo ID de visita médica
                                                                             ('Desparasitante', 'Desparasitante administrado en la visita', 1),
                                                                             ('Certificado de salud', 'Certificado para viajes', 2),
                                                                             ('Vacuna contra parvovirus', 'Vacuna anual para parvovirus', 3);
-- Inserción de Tratamientos
INSERT INTO tratamientos (descripcion, medicamento, dosis, fecha_inico, fecha_fin, visita_medico_id) VALUES
                                                                                                      ('Tratamiento para infección de oídos', 'Antibiótico', '500mg', '2024-09-15', '2024-09-30', 2), -- ID de visita médica
                                                                                                      ('Control de alergia alimentaria', 'Antihistamínico', '10mg', '2024-09-20', '2024-10-05', 3),
                                                                                                      ('Control de peso', 'Dieta especial', 'N/A', '2024-09-25', '2024-11-01', 4);
-- Inserción de Servicios
INSERT INTO services (tipo_servicio, descripcion, precio, cuidador_id) VALUES
                                                                           ('Paseo de perros', 'Servicio de paseo para perros de hasta 30 kg.', 25.00, 1), -- ID del cuidador
                                                                           ('Cuidado de mascotas', 'Cuidado a domicilio de mascotas durante vacaciones.', 50.00, 1),
                                                                           ('Baño y cepillado', 'Baño y cepillado para perros y gatos.', 30.00, 2),
                                                                           ('Adiestramiento básico', 'Entrenamiento básico para perros de todas las edades.', 100.00, 2);

INSERT INTO contrataciones (fecha_contratacion, estado, detalles, servicio_id, usuario_id, cuidador_id) VALUES
                                                                                                           ('2024-01-15', 'Completado', 'Paseo de perros por una hora.', 1, 1, 1),  -- ID de servicio, usuario y cuidador
                                                                                                           ('2024-02-20', 'En progreso', 'Cuidado a domicilio por 5 días.', 2, 2, 1),
                                                                                                           ('2024-03-10', 'Completado', 'Baño y cepillado de un gato.', 3, 1, 2),
                                                                                                           ('2024-04-05', 'Cancelado', 'Adiestramiento básico para un perro.', 4, 3, 2);

-- Inserción de Pagos
INSERT INTO pagos (metodo_pago, estado_pago, fecha_pago, monto, contratacion_id) VALUES
                                                                                  ('Tarjeta de crédito', 'Completado', '2024-01-16T10:00:00', 50.00, 1),  -- ID de contrato
                                                                                  ('PayPal', 'Completado', '2024-02-21T15:30:00', 200.00, 2),
                                                                                  ('Efectivo', 'Pendiente', '2024-03-11T09:00:00', 30.00, 3),
                                                                                  ('Transferencia', 'Cancelado', '2024-04-06T14:00:00', 75.00, 4);

-- Inserción de Mensajes
INSERT INTO mensajes (contenido, fecha_envio, fecha_update, usuario_id, cuidador_id) VALUES
                                                                                       ('Hola, tengo una consulta sobre el servicio.', '2024-05-01T09:00:00', '2024-05-01T09:05:00', 1, 1),  -- ID de usuario y cuidador
                                                                                       ('¿Pueden ofrecer cuidados especiales para gatos?', '2024-05-02T10:30:00', '2024-05-02T10:35:00', 2, 1),
                                                                                       ('Me gustaría saber más sobre sus tarifas.', '2024-05-03T14:00:00', '2024-05-03T14:05:00', 3, 2),
                                                                                       ('Gracias por su ayuda, ¡me he decidido!', '2024-05-04T16:15:00', '2024-05-04T16:20:00', 4, 2);

-- Inserción de Reseñas
INSERT INTO reseñas (calificacion, comentario, fecha_creacion, usuario_id, cuidador_id) VALUES
                                                                                           (4.5, 'Excelente servicio, muy profesional.', '2024-05-01T12:00:00', 1, 1),  -- ID de usuario y cuidador
                                                                                           (5.0, 'Mis gatos están muy felices, gracias.', '2024-05-02T15:30:00', 2, 1),
                                                                                           (3.0, 'El servicio fue bueno, pero podría mejorar la comunicación.', '2024-05-03T14:00:00', 3, 2),
                                                                                           (4.0, 'Buena atención y cuidado, pero el precio es un poco alto.', '2024-05-04T11:45:00', 4, 2);
-- Inserción de Seguidores
INSERT INTO seguidores (usuarioseguidor_id, usuarioseguido_id, fecha_empezo_seguir) VALUES
                                                                                (1, 2, '2024-05-01T09:00:00'),  -- Usuario 1 sigue al Usuario 2
                                                                                (1, 3, '2024-05-02T10:00:00'),  -- Usuario 1 sigue al Usuario 3
                                                                                (2, 3, '2024-05-03T11:00:00'),  -- Usuario 2 sigue al Usuario 3
                                                                                (3, 1, '2024-05-04T12:00:00');  -- Usuario 3 sigue al Usuario 1

-- Inserción de Comunidades
INSERT INTO comunidades (nombre, descripcion, creador_id) VALUES
                                                              ('Comunidad de Mascotas', 'Una comunidad para amantes de los animales.', 1),  -- Creador es el Usuario 1
                                                              ('Adoptantes Responsables', 'Espacio para quienes buscan adoptar mascotas.', 2),  -- Creador es el Usuario 2
                                                              ('Veterinarios en Línea', 'Comunidad de profesionales veterinarios.', 3);  -- Creador es el Usuario 3


-- Inserción de ComunidadUsuario
INSERT INTO comunidad_usuario (usuario_id, comunidad_id, fecha_que_se_unio) VALUES
                                                                      (1, 1, '2024-01-01 10:00:00'),  -- Usuario 1 se une a la Comunidad 1
                                                                      (2, 1, '2024-01-02 12:30:00'),  -- Usuario 2 se une a la Comunidad 1
                                                                      (1, 2, '2024-01-03 09:15:00'),  -- Usuario 1 se une a la Comunidad 2
                                                                      (3, 2, '2024-01-04 11:45:00'),  -- Usuario 3 se une a la Comunidad 2
                                                                      (2, 3, '2024-01-05 08:30:00');  -- Usuario 2 se une a la Comunidad 3

-- Inserción de Publicaciones
INSERT INTO publicaciones (contenido, fecha_publicacion, fecha_actualización, usuario_id, comunidad_id) VALUES
                                                                                                          ('Esta es la primera publicación del usuario 1.', '2024-01-01 10:00:00', NULL, 1, NULL),  -- Publicación del usuario 1
                                                                                                          ('Bienvenidos a la comunidad 1!', '2024-01-02 12:30:00', NULL, 2, 1),  -- Publicación en la comunidad 1 por el usuario 2
                                                                                                          ('Actualización de la mascota: está mucho mejor.', '2024-01-03 09:15:00', NULL, 2, NULL),  -- Publicación del usuario 2
                                                                                                          ('¡Mira esta foto de mi mascota!', '2024-01-04 11:45:00', NULL, 1, NULL),  -- Publicación del usuario 1
                                                                                                          ('Interesante debate sobre cuidado de mascotas.', '2024-01-05 08:30:00', NULL, 1, 2);  -- Publicación en la comunidad 2 por el usuario 1
-- Inserción de Likes
INSERT INTO likes_publicaciones (usuario_id, publicacion_id, fecha) VALUES
                                                                 (1, 1, '2024-01-02 10:00:00'),  -- Usuario 1 le da like a la publicación 1
                                                                 (2, 1, '2024-01-03 11:30:00'),  -- Usuario 2 le da like a la publicación 2
                                                                 (1, 3, '2024-01-04 12:15:00'),  -- Usuario 1 le da like a la publicación 3
                                                                 (2, 4, '2024-01-05 09:45:00');  -- Usuario 2 le da like a la publicación 4


-- Inserción de Comentarios
INSERT INTO comentarios (contenido, fechacreacion, usuario_id, publicacion_id) VALUES
                                                                                   ('¡Me encanta esta publicación!', '2024-01-02 14:00:00', 1, 1),  -- Comentario de Usuario 1 en Publicación 1
                                                                                   ('Muy interesante, gracias por compartir.', '2024-01-03 15:30:00', 2, 2),  -- Comentario de Usuario 2 en Publicación 2
                                                                                   ('Totalmente de acuerdo con esto.', '2024-01-04 16:15:00', 1, 3),  -- Comentario de Usuario 1 en Publicación 3
                                                                                   ('No puedo esperar a leer más sobre esto.', '2024-01-05 17:45:00', 2, 4);  -- Comentario de Usuario 2 en Publicación 4

