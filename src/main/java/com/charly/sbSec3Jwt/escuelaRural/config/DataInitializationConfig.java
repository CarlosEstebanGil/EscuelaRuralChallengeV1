package com.charly.sbSec3Jwt.escuelaRural.config;

import com.charly.sbSec3Jwt.entity.Role;
import com.charly.sbSec3Jwt.entity.User;
import com.charly.sbSec3Jwt.escuelaRural.alumno.Alumno;
import com.charly.sbSec3Jwt.escuelaRural.alumno.AlumnoRepository;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.Asistencia;
import com.charly.sbSec3Jwt.escuelaRural.asistencia.AsistenciaRepository;
import com.charly.sbSec3Jwt.escuelaRural.curso.Curso;
import com.charly.sbSec3Jwt.escuelaRural.curso.CursoRepository;
import com.charly.sbSec3Jwt.escuelaRural.docente.Docente;
import com.charly.sbSec3Jwt.escuelaRural.docente.DocenteRepository;
import com.charly.sbSec3Jwt.escuelaRural.fecha.Fecha;
import com.charly.sbSec3Jwt.escuelaRural.fecha.FechaRepository;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.Justificacion;
import com.charly.sbSec3Jwt.escuelaRural.justificacion.JustificacionRepository;
import com.charly.sbSec3Jwt.escuelaRural.miembro.Miembro;
import com.charly.sbSec3Jwt.escuelaRural.miembro.MiembroRepository;
import com.charly.sbSec3Jwt.escuelaRural.motivo.Motivo;
import com.charly.sbSec3Jwt.escuelaRural.motivo.MotivoRepository;
import com.charly.sbSec3Jwt.escuelaRural.nacionalidad.Nacionalidad;
import com.charly.sbSec3Jwt.escuelaRural.nacionalidad.NacionalidadRepository;
import com.charly.sbSec3Jwt.escuelaRural.preceptor.Preceptor;
import com.charly.sbSec3Jwt.escuelaRural.preceptor.PreceptorRepository;
import com.charly.sbSec3Jwt.escuelaRural.preceptor.PreceptorService;
import com.charly.sbSec3Jwt.escuelaRural.reporte.Reporte;
import com.charly.sbSec3Jwt.escuelaRural.reporte.ReporteRepository;
import com.charly.sbSec3Jwt.escuelaRural.tipoUsuario.TipoUsuario;
import com.charly.sbSec3Jwt.escuelaRural.tipoUsuario.TipoUsuarioRepository;
import com.charly.sbSec3Jwt.escuelaRural.utils.WeatherService;
import com.charly.sbSec3Jwt.escuelaRural.utils.WeatherServiceMock;
import com.charly.sbSec3Jwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Configuration
public class DataInitializationConfig {

    @Bean
    CommandLineRunner loadData(NacionalidadRepository nacionalidadRepository,
                               TipoUsuarioRepository tipoUsuarioRepository,
                               MiembroRepository miembroRepository,
                               AlumnoRepository alumnoRepository,
                               DocenteRepository docenteRepository,
                               PreceptorRepository preceptorRepository,
                               FechaRepository fechaRepository,
                               JustificacionRepository justificacionRepository,
                               MotivoRepository motivoRepository,
                               AsistenciaRepository asistenciaRepository,
                               ReporteRepository reporteRepository,
                               CursoRepository cursoRepository,
                               PasswordEncoder passwordEncoder,
                               PreceptorService preceptorService,
                               UserRepository userRepository,
                               WeatherService weatherService,
                               WeatherServiceMock weatherServiceMock) {
        return args -> {
            // Limpio las tablas
            asistenciaRepository.deleteAll();
            reporteRepository.deleteAll();
            alumnoRepository.deleteAll();
            docenteRepository.deleteAll();
            preceptorRepository.deleteAll();
            miembroRepository.deleteAll();
            nacionalidadRepository.deleteAll();
            tipoUsuarioRepository.deleteAll();
            fechaRepository.deleteAll();
            justificacionRepository.deleteAll();
            motivoRepository.deleteAll();
            cursoRepository.deleteAll();

            //Creo usuarios / roles ( ejemplos de un user por role ) 
            createUserIfNotExists("lionel@gmail.com", "lionel", "messi", Role.PRECEPTOR);
            createUserIfNotExists("dibu@alvarez.com", "dibu", "martinez", Role.ADMIN);
            createUserIfNotExists("julian@gmail.com", "julian", "alvarez", Role.DOCENTE);
            createUserIfNotExists("cuty@gmail.com", "cuty", "romero", Role.ALUMNO);
            createUserIfNotExists("fideo@gmail.com", "fideo", "dimaria", Role.USER);
            
            // Inserto curso
            Curso cursoUnico = new Curso(null, "Curso Unico");
            cursoRepository.save(cursoUnico);

            // Inserto nacionalidades
            Nacionalidad argentina = nacionalidadRepository.save(new Nacionalidad(null, "Argentina"));
            Nacionalidad brasil = nacionalidadRepository.save(new Nacionalidad(null, "Brasil"));
            Nacionalidad chile = nacionalidadRepository.save(new Nacionalidad(null, "Chile"));

            // Inserto tipos de usuario
            TipoUsuario tipoAlumno = tipoUsuarioRepository.save(new TipoUsuario(null, "ALUMNO"));
            TipoUsuario tipoDocente = tipoUsuarioRepository.save(new TipoUsuario(null, "DOCENTE"));
            TipoUsuario tipoPreceptor = tipoUsuarioRepository.save(new TipoUsuario(null, "PRECEPTOR"));
            TipoUsuario tipoAdmin = tipoUsuarioRepository.save(new TipoUsuario(null, "ADMIN"));

            // Inserto miembros
            Miembro juan = miembroRepository.save(new Miembro(null, "Juan", "Perez", "12345678", passwordEncoder.encode("password"), argentina, tipoAlumno, "juan.perez@yahoo.com"));
            Miembro maria = miembroRepository.save(new Miembro(null, "Maria", "Gomez", "87654321", passwordEncoder.encode("password"), brasil, tipoDocente, "maria.gomez@hoymail.com"));
            Miembro carlos = miembroRepository.save(new Miembro(null, "Carlos", "Rodriguez", "11223344", passwordEncoder.encode("password"), chile, tipoPreceptor, "carlos.Rodrigez@gmail.com"));

            // inserto Más miembros que serán alumnos
            Miembro charly = miembroRepository.save(new Miembro(null, "Charly", "San", "21213344", passwordEncoder.encode("password"), argentina, tipoAlumno, "csan@gmail.com"));
            Miembro mery = miembroRepository.save(new Miembro(null, "Mery", "Berton", "21123344", passwordEncoder.encode("password"), argentina, tipoAlumno, "mb.berton@gmail.com"));
            Miembro noe = miembroRepository.save(new Miembro(null, "Noe", "Gerosa", "12223344", passwordEncoder.encode("password"), argentina, tipoAlumno, "noeger@gmail.com"));
            Miembro guille = miembroRepository.save(new Miembro(null, "Guillermo", "Bald", "22223344", passwordEncoder.encode("password"), chile, tipoAlumno, "gbald@gmail.com"));
            Miembro euge = miembroRepository.save(new Miembro(null, "Eugenia", "Mar", "31223344", passwordEncoder.encode("password"), argentina, tipoAlumno, "mariaeugeniamar@gmail.com"));
            Miembro luis = miembroRepository.save(new Miembro(null, "Luis", "Friend", "41223344", passwordEncoder.encode("password"), argentina, tipoAlumno, "luisfriend@gmail.com"));
            Miembro sergio = miembroRepository.save(new Miembro(null, "Sergio", "Friend", "34223344", passwordEncoder.encode("password"), brasil, tipoAlumno, "sergiofriend@gmail.com"));
            Miembro jose = miembroRepository.save(new Miembro(null, "Jose", "Lopez", "44223344", passwordEncoder.encode("password"), argentina, tipoAlumno, "jose.lopez@gmail.com"));

            // inserto Más miembros que serán docentes
            Miembro silvia = miembroRepository.save(new Miembro(null, "Silvia", "Ger", "97654321", passwordEncoder.encode("password"), argentina, tipoDocente, "silvia.ger@gmail.com"));
            Miembro ale = miembroRepository.save(new Miembro(null, "Alejandro", "Gil", "66654321", passwordEncoder.encode("password"), argentina, tipoDocente, "ale.gil@gmail.com"));
            Miembro nat = miembroRepository.save(new Miembro(null, "Natalia", "Gil", "33654321", passwordEncoder.encode("password"), argentina, tipoDocente, "nat.gil@gmail.com"));

            // inserto Más miembros que serán preceptores
            Miembro veronica = miembroRepository.save(new Miembro(null, "Veronica", "Fernandez", "55523344", passwordEncoder.encode("password"), argentina, tipoPreceptor, "verofer@gmail.com"));
            Miembro pilli = miembroRepository.save(new Miembro(null, "Pilar", "Nadeo", "67623344", passwordEncoder.encode("password"), argentina, tipoPreceptor, "pilar.nadeo@gmail.com"));

            // Inserto alumnos
            Alumno alumnoJuan = alumnoRepository.save(new Alumno(juan, cursoUnico));
            Alumno alumnoCharly = alumnoRepository.save(new Alumno(charly, cursoUnico));
            Alumno alumnoMery = alumnoRepository.save(new Alumno(mery, cursoUnico));
            Alumno alumnoNoe = alumnoRepository.save(new Alumno(noe, cursoUnico));
            Alumno alumnoGuille = alumnoRepository.save(new Alumno(guille, cursoUnico));
            Alumno alumnoEuge = alumnoRepository.save(new Alumno(euge, cursoUnico));
            Alumno alumnoLuis = alumnoRepository.save(new Alumno(luis, cursoUnico));
            Alumno alumnoSergio = alumnoRepository.save(new Alumno(sergio, cursoUnico));
            Alumno alumnoJose = alumnoRepository.save(new Alumno(jose, cursoUnico));

            // Inserto docentes
            Docente docenteMaria = docenteRepository.save(new Docente(maria, cursoUnico));
            Docente docenteSilvia = docenteRepository.save(new Docente(silvia, cursoUnico));
            Docente docenteAlejandro = docenteRepository.save(new Docente(ale, cursoUnico));
            Docente docenteNatalia = docenteRepository.save(new Docente(nat, cursoUnico));

            // inserto  preceptores
            Preceptor preceptorCarlos = preceptorRepository.save(new Preceptor(carlos, cursoUnico));
            Preceptor preceptorVeronica = preceptorRepository.save(new Preceptor(veronica, cursoUnico));
            Preceptor preceptorPilar = preceptorRepository.save(new Preceptor(pilli, cursoUnico));

            // inserto  fechas
            Fecha fecha1 = fechaRepository.save(new Fecha(LocalDateTime.of(2024, 8, 1, 0, 0), false, false));
            Fecha fecha2 = fechaRepository.save(new Fecha(LocalDateTime.of(2024, 8, 2, 0, 0), true, false));

            // inserto  motivos
            Motivo motivoEnfermedad = motivoRepository.save(new Motivo(null, "Enfermedad"));
            Motivo motivoTramite = motivoRepository.save(new Motivo(null, "Trámite personal"));
            Motivo motivoMedica = motivoRepository.save(new Motivo(null, "Consulta médica"));

            // inserto  justificaciones
            Justificacion justificacionEnfermedad = justificacionRepository.save(new Justificacion(motivoEnfermedad, "tiene fiebre"));
            Justificacion justificacionTramite = justificacionRepository.save(new Justificacion(motivoTramite, "trámite en oficina"));
            Justificacion justificacionMedica = justificacionRepository.save(new Justificacion(motivoMedica, "consulta con dentista"));

            // inserto  asistencias ( simil a caso de uso tomar lista de alumnos del dia y registrarla) 
            asistenciaRepository.save(new Asistencia(null, alumnoJuan, fecha1, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoCharly, fecha1, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoMery, fecha1, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoNoe, fecha1, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoGuille, fecha1, false, justificacionTramite));
            asistenciaRepository.save(new Asistencia(null, alumnoEuge, fecha1, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoLuis, fecha1, false, justificacionMedica));
            asistenciaRepository.save(new Asistencia(null, alumnoSergio, fecha1, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoJose, fecha1, true, null));

            asistenciaRepository.save(new Asistencia(null, alumnoJuan, fecha2, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoCharly, fecha2, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoMery, fecha2, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoNoe, fecha2, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoGuille, fecha2, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoEuge, fecha2, true, null));
            asistenciaRepository.save(new Asistencia(null, alumnoLuis, fecha2, false, justificacionMedica));
            asistenciaRepository.save(new Asistencia(null, alumnoSergio, fecha2, false, null));
            asistenciaRepository.save(new Asistencia(null, alumnoJose, fecha2, true, null));

            // inserto 2 reportes

            // A) data ejemplo toma de lista fecha 1
            reporteRepository.save(new Reporte(null, fecha1.getFecha(), fecha1, 9, 7, 2, 2, 0));

            // B) data ejemplo toma de lista fecha 2
            reporteRepository.save(new Reporte(null, fecha2.getFecha(), fecha2, 9, 7, 2, 1, 1));

            // Simulación del día a día en la escuela Rural: (Pruebas a nivel service, usados por los controllers casos de uso por role)

            // 1) tomar-lista (ejemplo con preceptor. el docente también tiene permitido e implementado el tomar lista)

            long alumnoJuanId = alumnoJuan.getId();
            long alumnoCharlyId = alumnoCharly.getId();
            long alumnoMeryId = alumnoMery.getId();
            long alumnoNoeId = alumnoNoe.getId();
            long alumnoGuilleId = alumnoGuille.getId();
            long alumnoEugeId = alumnoEuge.getId();
            long alumnoLuisId = alumnoLuis.getId();
            long alumnoSergioId = alumnoSergio.getId();
            long alumnoJoseId = alumnoJose.getId();

            boolean presente = true;
            boolean ausente = false;

            boolean isLluvioso; 
            
            		
                    try {
                    	isLluvioso = weatherService.isLluvioso();
                    	System.out.println("real weather service used");
                    
                    }catch (Exception ex) {
                    	System.out.println("mock weather services used"); //TODO reintentos , otras alternaticas reales (ver documento de la app (seccion mejoras).
                    	isLluvioso = weatherServiceMock.isLluvioso();
            		}
                    
            Fecha fechaActual = fechaRepository.save(Fecha.builder().fecha(LocalDateTime.now()).feriado(false).lluvioso(isLluvioso).build());

            
            Asistencia asistencia1 = preceptorService.tomarLista(alumnoJuanId, presente, isLluvioso, fechaActual, null);
            Asistencia asistencia2 = preceptorService.tomarLista(alumnoCharlyId, presente,isLluvioso, fechaActual, null);
            Asistencia asistencia3 = preceptorService.tomarLista(alumnoMeryId, presente, isLluvioso,fechaActual, null);
            Asistencia asistencia4 = preceptorService.tomarLista(alumnoNoeId, ausente, isLluvioso,fechaActual, justificacionEnfermedad);
            Asistencia asistencia5 = preceptorService.tomarLista(alumnoGuilleId, presente, isLluvioso,fechaActual, null);
            Asistencia asistencia6 = preceptorService.tomarLista(alumnoEugeId, presente, isLluvioso,fechaActual, null);
            Asistencia asistencia7 = preceptorService.tomarLista(alumnoLuisId, presente, isLluvioso,fechaActual, null);
            Asistencia asistencia8 = preceptorService.tomarLista(alumnoSergioId, presente, isLluvioso,fechaActual, null);
            Asistencia asistencia9 = preceptorService.tomarLista(alumnoJoseId, presente, isLluvioso,fechaActual, null);

            // Genero del reporte
            Reporte reporte = preceptorService.generarReporte(fechaActual);

            // Obtengo la lista detallada de alumnos presentes
            List<Asistencia> presentes = preceptorService.obtenerAsistenciasPorFechaYEstado(fechaActual, true);

            // Obtengo la lista detallada de alumnos ausentes
            List<Asistencia> ausentes = preceptorService.obtenerAsistenciasPorFechaYEstado(fechaActual, false);

            // Salida por pantalla de resultados
            System.out.println("******************* SALIDAS TOMA DE LISTA (INICIO DEL DÍA)*********************** \n");
            System.out.println("Reporte del día: " + reporte.toString());

            System.out.println("\nAlumnos Presentes:");
            for (Asistencia asistencia : presentes) {
                Alumno alumno = asistencia.getAlumno();
                System.out.println("Alumno: " + alumno.getMiembro().getNombre() + ", Justificación: " + (asistencia.getJustificacion() != null ? asistencia.getJustificacion().getMotivo().getDescripcion() : "No") + ", Motivo: " + (asistencia.getJustificacion() != null ? asistencia.getJustificacion().getDescripcion() : "N/A"));
            }

            System.out.println("\nAlumnos Ausentes:");
            for (Asistencia asistencia : ausentes) {
                Alumno alumno = asistencia.getAlumno();
                System.out.println("Alumno: " + alumno.getMiembro().getNombre() + ", Justificación: " + (asistencia.getJustificacion() != null ? asistencia.getJustificacion().getMotivo().getDescripcion() : "No") + ", Motivo: " + (asistencia.getJustificacion() != null ? asistencia.getJustificacion().getDescripcion() : "N/A"));
            }
            
         // Limpiar tablas de Asistencias y Reporte para que luego las genere el test unit de preceptor
           
            /*reporteRepository.deleteAll();
            asistenciaRepository.deleteAll();*/
            
        };
        
    }
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    private void createUserIfNotExists(String email, String firstName, String lastName, Role role) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            User user = User.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(passwordEncoder.encode("1234"))  // ejemplo basico 
                    .role(role)
                    .build();
            userRepository.save(user);
        }
    }
}
