package com.proyecto;

import com.proyecto.domain.Alumno;
import com.proyecto.domain.Docente;
import com.proyecto.domain.Horario;
import com.proyecto.domain.Curso;
import com.proyecto.repository.CursoRepository;
import com.proyecto.repository.AlumnoRepository;
import com.proyecto.repository.DocenteRepository;
import com.proyecto.repository.HorarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;



@SpringBootApplication
public class AcademiaApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		
		SpringApplication.run(AcademiaApplication.class, args);		
	}
	
	@Bean
	CommandLineRunner runner(CursoRepository cursoRepository, DocenteRepository docenteRepository,
							 HorarioRepository horarioRepository, AlumnoRepository alumnoRepository){
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {

				Curso c1 = new Curso("Java", "Curso de Java");
				SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
				Date d1 = format1.parse("01/05/2016");
				c1.setFechainicio(d1);
				c1.setCupo(20);
				c1.setCosto(1000);
				
				SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
				Date d2 = format2.parse("05/08/2016");
				c1.setFechafin(d2);

				Curso c2 = new Curso("HTML", "Curso de HTML");
				Curso c3 = new Curso("CSS", "Curso de CSS");
				Curso c4 = new Curso("JavaScript", "Curso de JavaScript");


				List<Horario.Dia> l1 = new ArrayList<Horario.Dia>();
				l1.add(Horario.Dia.LUNES);
				l1.add(Horario.Dia.MIERCOLES);

				System.out.println(l1.contains(Horario.Dia.LUNES));

				LocalTime t1 = LocalTime.of(14,00);
				LocalTime t2= LocalTime.of(18,00);
				Horario h1 = new Horario(l1, t1, t2);

				horarioRepository.save(h1);

				System.out.println(t1);
				System.out.println(t2);

				c1.setHorario(h1);
				
				cursoRepository.save(c1);
				cursoRepository.save(c2);
				cursoRepository.save(c3);
				cursoRepository.save(c4);

				System.out.println(c1.getNombre());
				System.out.println(c1.getHorario());
				System.out.println(c2.getNombre());
				System.out.println(c3.getDescripcion());

				Docente doc1 = new Docente("Don", "Ramon");
				doc1.setCorreo("donramon@gmail.com");
				doc1.setDireccion("Vecindad del Chavo casa 1");
				doc1.setDni("32313231");
				Date d4 = format2.parse("05/08/1930");
				doc1.setFechaNacimiento(d4);

				Docente doc2 = new Docente("Doña", "Florinda");
				doc2.setCorreo("donaflorinda@gmail.com");
				doc2.setDireccion("Vecindad del Chavo casa 2");
				doc2.setDni("54332114");
				Date d3 = format2.parse("05/08/1950");
				doc2.setFechaNacimiento(d3);

				Docente doc3 = new Docente("Profesor", "Jirafales");
				doc3.setCorreo("jirafales@gmail.com");
				doc3.setDireccion("Vecindad del Chavo casa 4");
				doc3.setDni("5456778");
				Date d5 = format2.parse("01/10/1920");
				doc3.setFechaNacimiento(d5);

				docenteRepository.save(doc1);
				docenteRepository.save(doc2);
				docenteRepository.save(doc3);

				c1.setDocente(doc2);
				c2.setDocente(doc2);
				c3.setDocente(doc1);
				c4.setDocente(doc1);


				cursoRepository.save(c1);
				cursoRepository.save(c2);
				cursoRepository.save(c3);
				cursoRepository.save(c4);

				SimpleDateFormat formata1 = new SimpleDateFormat("dd/MM/yyyy");
				Date da1 = formata1.parse("05/08/1980");
				Alumno a1 = new Alumno("34344667", "Maria", "Gomez", da1, "Calle 34", "351667889", "mariagomez@hotmail.com" );
				alumnoRepository.save(a1);

				SimpleDateFormat formata2 = new SimpleDateFormat("dd/MM/yyyy");
				Date da2 = formata1.parse("12/07/1990");
				Alumno a2 = new Alumno("30567880", "Juan", "Perez", da2, "Av. Colon", "351445667", "juanperez@gmail.com" );
				alumnoRepository.save(a2);

				List li = new ArrayList<Alumno>();
				li.add(a1);
				li.add(a2);

				List lc = new ArrayList<Curso>();
				lc.add(c1);
				lc.add(c2);

				a1.setCursos(lc);
				alumnoRepository.save(a1);

				a2.setCursos(lc);
				alumnoRepository.save(a2);

				c1.setAlumnos(li);
				cursoRepository.save(c1);

				c2.setAlumnos(li);
				cursoRepository.save(c2);

			}
		};
		
	}
	
		
		
		@Bean
		public DataSource dataSource() {
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			return builder.setType(EmbeddedDatabaseType.HSQL).build();
			
		}

		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("hibernate.hbm2ddl.auto", "create-drop");

			HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
			vendorAdapter.setDatabase(Database.HSQL);
			vendorAdapter.setGenerateDdl(true);

			LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
			emf.setJpaVendorAdapter(vendorAdapter);
			emf.setPackagesToScan(getClass().getPackage().getName());
			emf.setDataSource(dataSource());
			emf.setJpaPropertyMap(properties);
			emf.setPackagesToScan("com.proyecto");

			return emf;
		}

		@Bean
		public JpaTransactionManager transactionManager() {
			JpaTransactionManager txnMgr = new JpaTransactionManager();
			txnMgr.setEntityManagerFactory(entityManagerFactory().getObject());
			return txnMgr;
		}

		
		@Override
		public void addResourceHandlers(final ResourceHandlerRegistry registry) {
			super.addResourceHandlers(registry);
			registry.addResourceHandler("/images/**").addResourceLocations("/images/");
			registry.addResourceHandler("/src/main/web/css/**").addResourceLocations("/src/main/web/css/");
			registry.addResourceHandler("/js/**").addResourceLocations("/js/");
			
		
		}
		

	
}